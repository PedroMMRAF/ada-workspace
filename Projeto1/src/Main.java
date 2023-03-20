import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int lines = Integer.parseInt(in.readLine());

            while (lines-- > 0) {
                char[] line = in.readLine().toCharArray();
                System.out.println(new Solver(line).solve());
            }
        }
    }
}