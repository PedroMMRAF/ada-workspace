import java.util.Queue;
import java.util.LinkedList;

public class Solver {
    private static char EMPTY = '.';
    private static char OBSTACLE = 'O';
    private static char HOLE = 'H';

    private static int X = 0;
    private static int Y = 1;
    private static int DX = 2;
    private static int DY = 3;
    private static int HEIGHT = 4;

    private char[][] field;
    private boolean[][] visited;
    private Queue<int[]> toTraverse;

    public Solver(char[][] field) {
        this.field = field;
        this.visited = new boolean[field.length][field[0].length];
        this.toTraverse = new LinkedList<>();
    }

    public int solve(int startX, int startY) {
        if (isHole(startX, startY))
            return 0;

        toTraverse.add(new int[] { startX, startY, 1, 0, 0 });
        toTraverse.add(new int[] { startX, startY, -1, 0, 0 });
        toTraverse.add(new int[] { startX, startY, 0, 1, 0 });
        toTraverse.add(new int[] { startX, startY, 0, -1, 0 });

        while (!toTraverse.isEmpty()) {
            int[][] traced = trace(toTraverse.remove());

            if (!isInBounds(traced[0]) || visited(traced[0]))
                continue;

            if (isHole(traced[0]))
                return traced[0][HEIGHT];

            setVisited(traced[0]);

            toTraverse.add(traced[0]);
            toTraverse.add(traced[1]);
        }

        return -1;
    }

    public int[][] trace(int[] node) {
        int x = node[X];
        int y = node[Y];
        int dX = node[DX];
        int dY = node[DY];
        int height = node[HEIGHT];

        while (isEmpty(x, y)) {
            x += dX;
            y += dY;
        }

        if (isObstacle(x, y)) {
            x -= dX;
            y -= dY;
        }

        return new int[][] {
                { x, y, dY, dX, height + 1 },
                { x, y, -dY, -dX, height + 1 }
        };
    }

    public boolean isInBounds(int x, int y) {
        return 0 <= x && x < field[0].length
                && 0 <= y && y < field.length;
    }

    public boolean isEmpty(int x, int y) {
        return isInBounds(x, y) && field[y][x] == EMPTY;
    }

    public boolean isObstacle(int x, int y) {
        return isInBounds(x, y) && field[y][x] == OBSTACLE;
    }

    public boolean isHole(int x, int y) {
        return isInBounds(x, y) && field[y][x] == HOLE;
    }

    public boolean isInBounds(int[] node) {
        return isInBounds(node[X], node[Y]);
    }

    public boolean isEmpty(int[] node) {
        return isEmpty(node[X], node[Y]);
    }

    public boolean isObstacle(int[] node) {
        return isObstacle(node[X], node[Y]);
    }

    public boolean isHole(int[] node) {
        return isHole(node[X], node[Y]);
    }

    private boolean visited(int[] node) {
        return visited[node[X]][node[Y]];
    }

    private void setVisited(int[] node) {
        visited[node[X]][node[Y]] = true;
    }
}
