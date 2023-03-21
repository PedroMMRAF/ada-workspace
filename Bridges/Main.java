package Bridges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("a");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int lines = Integer.parseInt(in.readLine());
            while (lines-- > 0) {
                int north = Integer.parseInt(in.readLine());
                City[] northCities = new City[north];
                for (int i = 0; i < north; i++) {
                    String[] line = in.readLine().split(" ");
                    northCities[i] = new City(line[0], line[1], Integer.parseInt(line[2]));
                }
                int south = Integer.parseInt(in.readLine());
                City[] southCities = new City[south];
                for (int i = 0; i < south; i++) {
                    String[] line = in.readLine().split(" ");
                    southCities[i] = new City(line[0], line[1], Integer.parseInt(line[2]));
                }
                int[] solution = solve(northCities, southCities);
                System.out.println(solution[0] + " " + solution[1]);
            }
        }
    }

    private static int[] solve(City[] northCities, City[] southCities) {
        int[][] matrix = new int[northCities.length][southCities.length];
        int[][] matrix2 = new int[northCities.length][southCities.length];
        boolean[][] matrix3 = new boolean[northCities.length][southCities.length];
        for (int i = 0; i < northCities.length; i++) {
            matrix[i][0] = northCities[i].bridgeValue(southCities[0]);
            if (matrix[i][0] > 0) {
                matrix2[i][0] = 1;
                matrix3[i][0] = true;
            }
        }
        for (int i = 1; i < southCities.length; i++) {
            matrix[0][i] = southCities[i].bridgeValue(northCities[0]);
            if (matrix[0][i] > 0) {
                matrix2[0][i] = 1;
                matrix3[0][i] = true;
            }
        }
        for (int i = 1; i < northCities.length; i++) {
            for (int j = 1; j < southCities.length; j++) {
                int newBridge = northCities[i].bridgeValue(southCities[j]);
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
                System.out.println(matrix[i][j]);
            }
        }
        int[] ret = new int[2];
        ret[0] = matrix[northCities.length - 1][southCities.length - 1];
        ret[1] = matrix2[northCities.length - 1][southCities.length - 1];
        return ret;
    }
}
