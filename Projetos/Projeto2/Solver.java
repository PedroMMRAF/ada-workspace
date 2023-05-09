import java.util.Queue;
import java.util.LinkedList;

public class Solver {
    private static char EMPTY = '.';
    private static char HOLE = 'H';

    private static int ENCODE = 1000;

    private char[][] field;

    private Queue<Integer> ready;
    private Queue<Integer> waiting;
    private boolean[][] reached;

    public Solver(char[][] field) {
        this.field = field;
    }

    private void reset() {
        this.ready = new LinkedList<>();
        this.waiting = new LinkedList<>();
        this.reached = new boolean[field.length][field[0].length];
    }

    public int solve(int sX, int sY) {
        reset();

        reached[sY][sX] = true;
        ready.add(encode(sX, sY, false));
        ready.add(encode(sX, sY, true));

        int height = 1;

        while (!ready.isEmpty()) {
            while (!ready.isEmpty()) {
                int pos = ready.remove();

                if (tracePath(pos, -1) || tracePath(pos, 1))
                    return height;
            }

            Queue<Integer> swap = waiting;
            waiting = ready;
            ready = swap;

            height++;
        }

        return -1;
    }

    private boolean tracePath(int pos, int sign) {
        int x = getX(pos);
        int y = getY(pos);
        boolean horz = isHorz(pos);

        int dx = horz ? sign : 0;
        int dy = horz ? 0 : sign;

        while (isInBounds(x, y) && isEmpty(x, y)) {
            x += dx;
            y += dy;
        }

        if (!isInBounds(x, y))
            return false;

        if (isHole(x, y))
            return true;

        x -= dx;
        y -= dy;

        if (!reached[y][x]) {
            waiting.add(encode(x, y, !horz));
            reached[y][x] = true;
        }

        return false;
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

    private int encode(int x, int y, boolean horz) {
        int pos = 1 + x + y * ENCODE;
        int sign = horz ? 1 : -1;

        return sign * pos;
    }

    private int getX(int pos) {
        return (Math.abs(pos) - 1) % ENCODE;
    }

    private int getY(int pos) {
        return (Math.abs(pos) - 1) / ENCODE;
    }

    private boolean isHorz(int pos) {
        return pos > 0;
    }
}
