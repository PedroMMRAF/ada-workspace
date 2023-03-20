package src;

public class MatrixProblem {
    private final int[] d;
    private final int size;
    private int count;

    private final long[][] p;

    public MatrixProblem(int size) {
        this.count = 0;
        this.size = size;
        this.d = new int[size];
        this.p = new long[size][size];
    }

    public void add(int d) {
        this.d[this.count++] = d;
    }

    public long solve() {
        // Base case
        for (int i = 0; i < size; i++) {
            p[i][i] = 0;
        }

        // Next cases
        for (int s = 1; s < size - 1; s++) {
            for (int j = size - 1; j > s; j--) {
                int i = j - s;

                long res = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    res = Math.min(res, p[i][k] + p[k + 1][j] + d[i - 1] * d[j] * d[k]);
                }

                p[i][j] = res;
            }
        }

        return p[1][size - 1];
    }

    public long recSolve() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                p[i][j] = Integer.MIN_VALUE;
            }
        }

        return P(1, size - 1);
    }

    public long P(int i, int j) {
        if (p[i][j] != Integer.MIN_VALUE) {
            return p[i][j];
        }

        if (i == j) {
            return 0;
        }

        long res = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            res = Math.min(res, P(i, k) + P(k + 1, j) + d[i - 1] * d[j] * d[k]);
        }

        return p[i][j] = res;
    }
}
