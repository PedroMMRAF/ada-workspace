import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author Miguel Valido 60477
 * @author Pedro Fernandes 60694
 */
public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int[] line = readInts(in);

            Solver solver = new Solver(line[0]);
            int numOfTasks = line[1];

            while (numOfTasks-- > 0) {
                line = readInts(in);
                solver.addEdge(line[0], line[1], line[2]);
            }

            int[] solution = solver.solve();

            System.out.println(solution[0] + " " + solution[1]);
        }
    }

    private static int[] readInts(BufferedReader in) throws IOException {
        String[] line = in.readLine().split("\s");
        return Arrays.stream(line).map(Integer::parseInt).mapToInt(Integer::intValue).toArray();
    }
}