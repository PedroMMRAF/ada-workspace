import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(in.readLine());
            for (int i = 0; i < T; i++) {
                System.out.println(new Solver(in.readLine()).solve());
            }
        }
    }
}