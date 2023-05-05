import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static String STUCK = "Stuck";

    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            main(reader);
        }
    }

    private static void main(BufferedReader reader) throws IOException {
        int[] line = readIntLine(reader);

        int rows = line[0];
        int columns = line[1];
        int cases = line[2];

        char[][] field = readMatrix(reader, rows, columns);

        Solver solver = new Solver(field);

        while (cases-- > 0)
            solveCase(reader, solver);
    }

    private static void solveCase(BufferedReader reader, Solver solver) throws IOException {
        int[] line = readIntLine(reader);

        int startY = line[0] - 1;
        int startX = line[1] - 1;

        int result = solver.solve(startX, startY);

        System.out.println(result != -1 ? result : STUCK);
    }

    private static char[][] readMatrix(BufferedReader reader, int rows, int columns) throws IOException {
        char[][] result = new char[rows][columns];

        for (int i = 0; i < rows; i++)
            result[i] = reader.readLine().toCharArray();

        return result;
    }

    private static int[] readIntLine(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
    }
}
