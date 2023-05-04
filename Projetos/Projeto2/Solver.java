import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solver {
    private static char EMPTY = '.';
    private static char HOLE = 'H';

    private static int MAX_SIDE = 900;

    private static int OUT_OF_BOUNDS = -1;
    private static int IN_HOLE = -2;

    private char[][] field;

    public Solver(char[][] field) {
        this.field = field;
    }

    /**
     * Solves this problem for a given starting position
     * on the provided field
     * 
     * @param sX starting X coordinate
     * @param sY starting Y coordinate
     * @return the minimum number of moves to reach the hole
     *         or -1 if not possible
     */
    public int solve(int sX, int sY) {
        Queue<int[]> waiting, waiting2;

        if (isHole(sX, sY))
            return 0;

        Set<Integer> traveled = new HashSet<>(field.length * field[0].length);

        int result = 1;

        waiting = new LinkedList<>();

        waiting.add(new int[] { sX, sY, 0, 1 });
        waiting.add(new int[] { sX, sY, 1, 0 });

        while (!waiting.isEmpty()) {
            waiting2 = new LinkedList<>();

            while (!waiting.isEmpty()) {
                int[] pos = waiting.remove();

                if (result > 1 && !traveled.add(encode(pos[0], pos[1])))
                    continue;

                if (trace(pos, waiting2))
                    return result;
            }

            Queue<int[]> swap = waiting2;
            waiting2 = waiting;
            waiting = swap;

            result++;
        }

        return -1;
    }

    private boolean trace(int[] pos, Queue<int[]> waiting) {
        for (int i = 0; i <= 1; i++) {
            int sign = i * 2 - 1;
            int[] newPos = tracePath(pos[0], pos[1], pos[2] * sign, pos[3] * sign);

            if (newPos[0] == OUT_OF_BOUNDS)
                continue;

            if (newPos[0] == IN_HOLE)
                return true;

            waiting.add(newPos);
        }

        return false;
    }

    private int[] tracePath(int x, int y, int dx, int dy) {
        while (isInBounds(x, y) && isEmpty(x, y)) {
            x += dx;
            y += dy;
        }

        if (!isInBounds(x, y))
            return new int[] { OUT_OF_BOUNDS };

        if (isHole(x, y))
            return new int[] { IN_HOLE };

        return new int[] { x - dx, y - dy, dy, dx };
    }

    private int encode(int c, int r) {
        return c + r * MAX_SIDE;
    }

    private boolean isInBounds(int c, int r) {
        return 0 <= c && c < field[0].length
                && 0 <= r && r < field.length;
    }

    private boolean isEmpty(int c, int r) {
        return field[r][c] == EMPTY;
    }

    private boolean isHole(int c, int r) {
        return field[r][c] == HOLE;
    }
}
