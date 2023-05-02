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
            int lines = Integer.parseInt(in.readLine());

            while (lines-- > 0) {
                String line = in.readLine();
                Solver solver = new Solver(line);
                System.out.println(solver.solve());
            }
        }
    }
}