import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

/**
 * @author Miguel Valido 60477
 * @author Pedro Fernandes 60694
 */
public class Solver {
    public static final char EMPTY = '.';
    public static final char HOLE = 'H';

    public static final int STUCK = -1;

    private char[][] field;
    private List<Integer>[][] skipGraph;

    private Queue<Integer> ready;
    private Queue<Integer> waiting;
    private boolean[][] reached;

    @SuppressWarnings("unchecked")
    public Solver(char[][] field) {
        this.field = field;
        this.skipGraph = new List[field.length][field[0].length];
    }

    private void reset() {
        ready = new LinkedList<>();
        waiting = new LinkedList<>();
        reached = new boolean[field.length][field[0].length];
    }

    public int solve(int x, int y) {
        int height = 1;
        reset();

        reached[x][y] = true;
        ready.add(encode(x, y));

        while (!ready.isEmpty()) {
            while (!ready.isEmpty()) {
                int next = ready.remove();

                if (processNode(next))
                    return height;
            }

            Queue<Integer> temp = ready;
            ready = waiting;
            waiting = temp;

            height++;
        }

        return STUCK;
    }

    private boolean processNode(int node) {
        List<Integer> successors = skipGraph[getX(node)][getY(node)];

        if (successors == null)
            return tracePaths(node);

        for (int successor : successors) {
            int row = getX(successor);
            int col = getY(successor);

            if (isHole(row, col))
                return true;

            if (!reached[row][col]) {
                waiting.add(successor);
                reached[row][col] = true;
            }
        }

        return false;
    }

    private boolean tracePaths(int node) {
        return tracePath(node, -1, 0) || tracePath(node, 1, 0)
                || tracePath(node, 0, -1) || tracePath(node, 0, 1);

    }

    private boolean tracePath(int start, int dx, int dy) {
        int x = getX(start);
        int y = getY(start);

        while (isInBounds(x, y) && isEmpty(x, y)) {
            x += dx;
            y += dy;
        }

        if (!isInBounds(x, y))
            return false;

        if (isHole(x, y)) {
            addSkip(start, encode(x, y));
            return true;
        }

        x -= dx;
        y -= dy;

        addSkip(start, encode(x, y));

        if (!reached[x][y]) {
            waiting.add(encode(x, y));
            reached[x][y] = true;
        }

        return false;
    }

    private void addSkip(int start, int end) {
        int x = getX(start);
        int y = getY(start);

        if (skipGraph[x][y] == null)
            skipGraph[x][y] = new LinkedList<>();

        skipGraph[x][y].add(end);
    }

    // Field checking

    private boolean isInBounds(int x, int y) {
        return 0 <= x && x < field.length && 0 <= y && y < field[0].length;
    }

    private boolean isEmpty(int x, int y) {
        return field[x][y] == EMPTY;
    }

    private boolean isHole(int x, int y) {
        return field[x][y] == HOLE;
    }

    // Node encoding and decoding

    private int encode(int x, int y) {
        return x + y * field.length;
    }

    private int getX(int encoded) {
        return encoded % field.length;
    }

    private int getY(int encoded) {
        return encoded / field.length;
    }
}