public class Solver {
    private final City[] north;
    private final City[] south;

    private final int[][] values;
    private final int[][] bridges;

    public Solver(City[] north, City[] south) {
        this.north = north;
        this.south = south;

        this.values = new int[north.length + 1][south.length + 1];
        this.bridges = new int[north.length + 1][south.length + 1];
    }

    public int[] solve() {
        for (int n = 1; n <= north.length; n++) {
            for (int s = 1; s <= south.length; s++) {
                City northCity = north[n - 1];
                City southCity = south[s - 1];

                int[] pair = maxOf(makePair(n - 1, s), makePair(n, s - 1));

                if (northCity.matches(southCity)) {
                    int[] withBridge = makePair(n - 1, s - 1);
                    withBridge[0] += northCity.getValue() + southCity.getValue();
                    withBridge[1]++;

                    pair = maxOf(pair, withBridge);
                }

                values[n][s] = pair[0];
                bridges[n][s] = pair[1];
            }
        }

        return makePair(north.length, south.length);
    }

    private int[] maxOf(int[] vb1, int[] vb2) {
        int v1 = vb1[0];
        int v2 = vb2[0];
        int b1 = vb1[1];
        int b2 = vb2[1];

        if (v1 > v2)
            return new int[] {v1, b1};

        if (v1 < v2)
            return new int[] {v2, b2};

        return new int[] {v1, Math.min(b1, b2)};
    }

    private int[] makePair(int n1, int s1) {
        return new int[] {values[n1][s1], bridges[n1][s1]};
    }
}
