import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Miguel Valido 60477
 * @author Pedro Fernandes 60694
 */
public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String[] firstLine = in.readLine().split(" ");
            int numberOfTasks = Integer.parseInt(firstLine[0]);
            int numberOfPrecedents = Integer.parseInt(firstLine[1]);
            int hardWeekLimit = Integer.parseInt(firstLine[2]);
            Solver solver = new Solver(numberOfTasks, hardWeekLimit);
            while (numberOfPrecedents-- > 0) {
                String[] line = in.readLine().split(" ");
                solver.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
            }
            int[] solution = solver.solve();
            System.out.println(solution[0] + " " + solution[1]);
        }
    }
}