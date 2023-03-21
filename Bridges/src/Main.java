package Bridges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int samples = Integer.parseInt(in.readLine());

            while (samples-- > 0) {
                City[] north = readRiverside(in);
                City[] south = readRiverside(in);

                Solver solver = new Solver(north, south);
                int solution = solver.iterative();

                System.out.println(solution);
            }
        }
    }

    private static City[] readRiverside(BufferedReader in) throws IOException {
        int count = Integer.parseInt(in.readLine());

        City[] cities = new City[count];

        for (int i = 0; i < count; i++) {
            String[] line = in.readLine().split(" ");
            cities[i] = new City(line[0], line[1], Integer.parseInt(line[2]));
        }

        return cities;
    }

    private static int[] solve(City[] north, City[] south) {
        int[][] matrix = new int[north.length][south.length];
        int[][] matrix2 = new int[north.length][south.length];
        boolean[][] matrix3 = new boolean[north.length][south.length];

        for (int i = 0; i < north.length; i++) {
            matrix[i][0] = north[i].bridgeValue(south[0]);
            if (matrix[i][0] > 0) {
                matrix2[i][0] = 1;
                matrix3[i][0] = true;
            }
        }

        for (int i = 1; i < south.length; i++) {
            matrix[0][i] = south[i].bridgeValue(north[0]);
            if (matrix[0][i] > 0) {
                matrix2[0][i] = 1;
                matrix3[0][i] = true;
            }
        }

        for (int i = 1; i < north.length; i++) {
            for (int j = 1; j < south.length; j++) {
                int newBridge = north[i].bridgeValue(south[j]);
                boolean newB = true;
                int max = matrix[i - 1][j - 1] + newBridge;

                if (matrix3[i][j - 1]) {
                    if (matrix[i][j - 1] > max) {
                        max = matrix[i][j - 1];
                        matrix2[i][j] = matrix2[i][j - 1];
                        newB = false;
                    }
                } else if (matrix[i][j - 1] + newBridge > max) {
                    max = matrix[i][j - 1] + newBridge;
                    if (newBridge > 0)
                        matrix2[i][j] = matrix2[i][j - 1] + 1;
                    else {
                        matrix2[i][j] = matrix2[i][j - 1];
                        newB = false;
                    }
                }

                if (matrix3[i - 1][j]) {
                    if (matrix[i - 1][j] > max) {
                        max = matrix[i - 1][j];
                        matrix2[i][j] = matrix2[i - 1][j];
                        newB = false;
                    } else if (matrix[i - 1][j] + newBridge > max) {
                        max = matrix[i - 1][j] + newBridge;
                        if (newBridge > 0)
                            matrix2[i][j] = matrix2[i - 1][j] + 1;
                        else {
                            matrix2[i][j] = matrix2[i - 1][j];
                            newB = false;
                        }
                    }
                }

                matrix[i][j] = max;
                if (newB) {
                    matrix3[i][j] = true;
                    if (newBridge > 0)
                        matrix2[i][j] = matrix2[i - 1][j - 1] + 1;
                    else
                        matrix2[i][j] = matrix2[i - 1][j - 1];
                }
            }
        }

        int[] ret = new int[2];
        ret[0] = matrix[north.length - 1][south.length - 1];
        ret[1] = matrix2[north.length - 1][south.length - 1];
        return ret;
    }
}
