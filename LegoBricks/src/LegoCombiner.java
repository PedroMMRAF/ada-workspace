public class LegoCombiner {
    private static final int MAX_SIZE = 1001;
    private static final int[] BRICKS = {1, 2, 3, 4, 6, 8, 10, 12, 16};

    private final int[] powers;
    private int maxSpace;

    public LegoCombiner(int columns) {
        powers = new int[columns + 1];
        maxSpace = 0;
    }

    public void addSpace(int n) {
        powers[n]++;
        maxSpace = Math.max(maxSpace, n);
    }

    public long solve() {
        long result = 1;

        int[] memory = new int[maxSpace + 1];
        memory[0] = 1;

        for (int i = 1; i <= maxSpace; i++) {
            for (int j = 0, brick; j < BRICKS.length && ((brick = i - BRICKS[j]) >= 0); j++) {
                memory[i] += memory[brick];
            }

            result *= pow(memory[i], powers[i]);
        }

        return result;
    }

    private int pow(int base, int exponent) {
        int result = 1;

        for (int j = 0; j < exponent; j++) {
            result *= base;
        }

        return result;
    }
}


