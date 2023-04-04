import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Integer[] line = readInts(reader);
            Solver solver = new Solver(line[0], line[1], line[2]);

            int precedences = line[3];

            while (precedences-- > 0) {
                line = readInts(reader);
                solver.addEdge(line[0], line[1]);
            }

            Arrays.stream(solver.solve()).forEach(System.out::println);
        }
    }

    private static Integer[] readInts(BufferedReader reader) throws IOException {
        return (Integer[]) Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).toArray();
    }
}
