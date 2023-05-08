import java.util.Queue;
import java.util.LinkedList;

public class Solver {
    private static class Node {
        public int x;
        public int y;
        public boolean dir;

        public Node(int x, int y, boolean dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    private static char EMPTY = '.';
    private static char HOLE = 'H';

    private char[][] field;

    private Queue<Node> ready;
    private Queue<Node> waiting;

    public Solver(char[][] field) {
        this.field = field;
    }

    public int solve(int sX, int sY) {
        ready = new LinkedList<>();
        waiting = new LinkedList<>();

        int height = 1;
        boolean[][] traveled = new boolean[field.length][field[0].length];

        ready.add(new Node(sX, sY, false));
        ready.add(new Node(sX, sY, true));

        while (!ready.isEmpty()) {
            while (!ready.isEmpty()) {
                Node pos = ready.remove();

                if (traveled[pos.y][pos.x] && height > 1)
                    continue;

                traveled[pos.y][pos.x] = true;

                if (trace(pos))
                    return height;
            }

            Queue<Node> swap = waiting;
            waiting = ready;
            ready = swap;

            height++;
        }

        return -1;
    }

    private boolean trace(Node pos) {
        for (int i = -1; i <= 1; i += 2)
            if (trace(pos, i))
                return true;

        return false;
    }

    private boolean trace(Node pos, int sign) {
        int x = pos.x;
        int y = pos.y;
        int dx = pos.dir ? sign : 0;
        int dy = pos.dir ? 0 : sign;

        while (isInBounds(x, y) && isEmpty(x, y)) {
            x += dx;
            y += dy;
        }

        if (!isInBounds(x, y))
            return false;

        if (isHole(x, y))
            return true;

        waiting.add(new Node(x - dx, y - dy, !pos.dir));

        return false;
    }

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
}
