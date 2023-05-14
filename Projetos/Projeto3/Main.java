import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Miguel Valido 60477
 * @author Pedro Fernandes 60694
 */
public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] line = reader.readLine().split(" ");

            int regions = Integer.parseInt(line[0]);
            int links = Integer.parseInt(line[1]);

            Solver solver = new Solver(regions);

            for (int i = 0; i < regions; i++) {
                line = reader.readLine().split(" ");

                int population = Integer.parseInt(line[0]);
                int departure = Integer.parseInt(line[1]);

                solver.addRegion(i, population, departure);
            }

            for (int i = 0; i < links; i++) {
                line = reader.readLine().split(" ");

                int start = Integer.parseInt(line[0]);
                int end = Integer.parseInt(line[1]);

                solver.addLink(start, end);
            }

            solver.setSafeRegion(Integer.parseInt(reader.readLine()));

            System.out.println(solver.solve());
        }
    }
}