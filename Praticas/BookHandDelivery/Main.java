import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final String IMPOSSIBLE = "impossible";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int people = Integer.parseInt(reader.readLine());
            char[][] matrix = new char[people][Solver.WEEK_DAYS];

            for (int i = 0; i < people; i++) {
                String line = reader.readLine();

                for (int j = 0; j < Solver.WEEK_DAYS; j++) {
                    matrix[i][j] = line.charAt(j * 2);
                }
            }

            Solver solver = new Solver(matrix);

            int cases = Integer.parseInt(reader.readLine());

            for (int i = 0; i < cases; i++) {
                String[] line = reader.readLine().split(" ");

                int start = Integer.parseInt(line[0]);
                int end = Integer.parseInt(line[1]);

                int result = solver.solve(start, end);
                System.out.println(result == Integer.MAX_VALUE ? IMPOSSIBLE : result);
            }
        }
    }
}