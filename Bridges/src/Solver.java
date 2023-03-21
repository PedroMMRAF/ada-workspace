package Bridges;

public class Solver {
    private final City[] north;
    private final City[] south;

    private int[][] values;
    private int[][] bridges;

    public Solver(City[] north, City[] south) {
        this.north = north;
        this.south = south;

        this.values = new int[north.length + 1][south.length + 1];
        this.bridges = new int[north.length + 1][south.length + 1];
    }

    public int recursive() {
        return L(north.length - 1, south.length - 1);
    }

    public int L(int n, int s) {
        if (n == -1 || s == -1) {
            return 0;
        }

        int base = 0;

        if (north[n].matches(south[s])) {
            base = north[n].getValue() + south[s].getValue() + L(n - 1, s - 1);
        }

        return Math.max(base, Math.max(L(n, s - 1), L(n - 1, s)));
    }

    public int iterative() {
        for (int n = 1; n <= north.length; n++) {
            for (int s = 1; s <= south.length; s++) {
                if (north[n - 1].matches(south[s - 1])) {
                    int bridge = north[n - 1].getValue() + south[s - 1].getValue() + values[n - 1][s - 1];
                    int count = bridges[n - 1][s - 1] + 1;

                    if (count > )
                }
                else {

                }


                values[n][s] = Math.max(values[n][s], Math.max(values[n - 1][s], values[n][s - 1]));
            }
        }

        return values[north.length][south.length];
    }
}
