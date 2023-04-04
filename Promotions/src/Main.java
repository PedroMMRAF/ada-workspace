import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] line = reader.readLine().split(" ");

            int lower = Integer.parseInt(line[0]);
            int upper = Integer.parseInt(line[1]);
            int employees = Integer.parseInt(line[2]);

            Solver solver = new Solver(lower, upper, employees);

            int precedences = Integer.parseInt(line[3]);

            while (precedences-- > 0) {
                line = reader.readLine().split(" ");

                int start = Integer.parseInt(line[0]);
                int end = Integer.parseInt(line[1]);

                solver.addEdge(start, end);
            }

            int[] result = solver.solve();

            System.out.println(result[0]);
            System.out.println(result[1]);
            System.out.println(result[2]);
        }
    }
}
