import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int segmentCount = Integer.parseInt(reader.readLine());

            Solver solver = new Solver(segmentCount);

            while (segmentCount-- > 0) {
                int[] line = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                solver.addEdge(line[0], line[1], line[2], line[3]);
            }

            System.out.println(solver.result());
        }
    }
}