import java.util.Queue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solver {
    public static Counter ITERS = new Counter();

    private static char EMPTY = '.';
    private static char HOLE = 'H';

    private int[][] DIRECTIONS = new int[][] {
            { 1, 0 },
            { -1, 0 },
            { 0, 1 },
            { 0, -1 }
    };

    private char[][] field;

    private Queue<Integer> ready;
    private Queue<Integer> waiting;
    private boolean[][] reached;

    private Map<Integer, Integer> nodeMap;
    private List<List<Integer>> nodeSuccessors;

    public Solver(char[][] field) {
        this.field = field;

        this.nodeMap = new HashMap<>();
        this.nodeSuccessors = new ArrayList<>();
    }

    private void reset() {
        this.ready = new LinkedList<>();
        this.waiting = new LinkedList<>();
        this.reached = new boolean[field.length][field[0].length];
    }

    public int solve(int sX, int sY) {
        reset();

        reached[sY][sX] = true;
        ready.add(encode(sX, sY));

        int height = 1;

        while (!ready.isEmpty()) {
            while (!ready.isEmpty()) {
                int pos = ready.remove();

                if (tracePaths(pos))
                    return height;
            }

            Queue<Integer> swap = waiting;
            waiting = ready;
            ready = swap;

            height++;
        }

        return -1;
    }

    private boolean tracePaths(int pos) {
        Integer node = nodeMap.get(pos);

        if (node != null) {
            for (int endPos : nodeSuccessors.get(node)) {
                ITERS.add();
                int x = getX(endPos);
                int y = getY(endPos);

                if (isHole(x, y))
                    return true;

                if (!reached[y][x]) {
                    waiting.add(endPos);
                    reached[y][x] = true;
                }
            }

            return false;
        }

        for (int[] dir : DIRECTIONS)
            if (tracePath(pos, dir[0], dir[1]))
                return true;

        return false;
    }

    private boolean tracePath(int pos, int dx, int dy) {
        int x = getX(pos);
        int y = getY(pos);

        while (isInBounds(x + dx, y + dy) && isEmpty(x + dx, y + dy)) {
            ITERS.add();
            reached[y][x] = true;
            x += dx;
            y += dy;
        }

        if (!isInBounds(x + dx, y + dy))
            return false;

        if (isHole(x + dx, y + dy)) {
            addEdge(pos, encode(x + dx, y + dy));
            return true;
        }

        int endPos = encode(x, y);

        if (pos == endPos)
            return false;

        addEdge(pos, endPos);

        if (!reached[y][x]) {
            waiting.add(endPos);
            reached[y][x] = true;
        }

        return false;
    }

    // Graph methods

    private void addEdge(int pos1, int pos2) {
        nodeSuccessors.get(getNode(pos1)).add(pos2);
    }

    private int getNode(int pos) {
        if (!nodeMap.containsKey(pos)) {
            nodeMap.put(pos, nodeMap.size());
            nodeSuccessors.add(new LinkedList<>());
        }

        return nodeMap.get(pos);
    }

    // Field inspection

    private boolean isInBounds(int x, int y) {
        return 0 <= x && x < field[0].length
                && 0 <= y && y < field.length;
    }

    private boolean isEmpty(int x, int y) {
        return field[y][x] == EMPTY;
    }

    private boolean isHole(int x, int y) {
        return field[y][x] == HOLE;
    }

    // Node encoding and decoding

    private int encode(int x, int y) {
        return 1 + x + y * 1000;
    }

    private int getX(int pos) {
        return (pos - 1) % 1000;
    }

    private int getY(int pos) {
        return (pos - 1) / 1000;
    }
}
