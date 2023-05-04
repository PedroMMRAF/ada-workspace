import java.io.IOException;

public class Main {
    private static String STUCK = "Stuck";

    public static void main(String[] args) throws IOException {
        int[] line;

        try (var reader = Util.getReader()) {
            line = Util.readIntLine(reader);

            int rows = line[0];
            int cols = line[1];
            int cases = line[2];

            char[][] field = new char[rows][cols];

            for (int i = 0; i < rows; i++) {
                field[i] = reader.readLine().toCharArray();
            }

            Solver solver = new Solver(field);

            while (cases-- > 0) {
                line = Util.readIntLine(reader);

                int result = solver.solve(line[1] - 1, line[0] - 1);

                System.out.println(result != -1 ? result : STUCK);
            }
        }
    }
}
