import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] line = reader.readLine().split(" ");
            int R = Integer.parseInt(line[0]);
            int D = Integer.parseInt(line[1]);

            Solver solver = new Solver(R);

            for (int d = 0; d < D; d++) {
                line = reader.readLine().split(" ");
                int r1 = Integer.parseInt(line[0]);
                int r2 = Integer.parseInt(line[1]);
                int v = Integer.parseInt(line[2]);

                solver.addEdge(r1, r2, v);
            }

            line = reader.readLine().split(" ");
            int S = Integer.parseInt(line[0]);
            int E = Integer.parseInt(line[1]);
            int I = Integer.parseInt(line[2]);

            System.out.println(solver.solve(S, E, I));
        } catch (Bruh e) {
            System.out.println("Out of budget");
        }
    }
}