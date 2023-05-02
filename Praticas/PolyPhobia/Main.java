import java.io.IOException;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = Util.getReader()) {
            int segmentCount = Integer.parseInt(reader.readLine());

            Solver solver = new Solver(segmentCount);

            while (segmentCount-- > 0) {
                int[] line = Util.readIntLine(reader);

                solver.addEdge(line[0], line[1], line[2], line[3]);
            }

            System.out.println(solver.result());
        }
    }
}