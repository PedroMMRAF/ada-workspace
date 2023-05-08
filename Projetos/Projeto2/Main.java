import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static String STUCK = "Stuck";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            main(reader);
        }
    }

    private static void main(BufferedReader reader) throws IOException {
        int[] line = readIntLine(reader);

        int rows = line[0];
        int cols = line[1];
        int cases = line[2];

        char[][] field = readCharMatrix(reader, rows, cols);

        Solver solver = new Solver(field);

        while (cases-- > 0) {
            solveCase(reader, solver);
        }
    }

    private static void solveCase(BufferedReader reader, Solver solver) throws IOException {
        int[] line = readIntLine(reader);

        int result = solver.solve(line[1] - 1, line[0] - 1);

        System.out.println(result == -1 ? STUCK : result);
    }

    private static char[][] readCharMatrix(BufferedReader reader, int rows, int cols) throws IOException {
        char[][] result = new char[rows][cols];

        for (int i = 0; i < rows; i++)
            result[i] = reader.readLine().toCharArray();

        return result;
    }

    private static int[] readIntLine(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
