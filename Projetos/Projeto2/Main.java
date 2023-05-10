import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Miguel Valido 60477
 * @author Pedro Fernandes 60694
 */
public class Main {
    private static final String STUCK = "Stuck";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] line = reader.readLine().split(" ");

            int rows = Integer.parseInt(line[0]);
            int columns = Integer.parseInt(line[1]);
            int cases = Integer.parseInt(line[2]);

            char[][] field = new char[rows][columns];

            for (int i = 0; i < rows; i++) {
                field[i] = reader.readLine().toCharArray();
            }

            Solver solver = new Solver(field);

            for (int i = 0; i < cases; i++) {
                line = reader.readLine().split(" ");

                int row = Integer.parseInt(line[0]) - 1;
                int column = Integer.parseInt(line[1]) - 1;

                int result = solver.solve(row, column);

                System.out.println(result == Solver.STUCK ? STUCK : result);
            }
        }
    }
}