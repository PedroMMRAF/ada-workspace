import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int samples = Integer.parseInt(in.readLine());

            while (samples-- > 0) {
                City[] north = readRiverside(in);
                City[] south = readRiverside(in);

                Solver solver = new Solver(north, south);
                int[] solution = solver.solve();

                System.out.println(solution[0] + " " + solution[1]);
            }
        }
    }

    private static City[] readRiverside(BufferedReader in) throws IOException {
        int count = Integer.parseInt(in.readLine());

        City[] cities = new City[count];

        for (int i = 0; i < count; i++) {
            String[] line = in.readLine().split(" ");
            cities[i] = new City(line[0], line[1], Integer.parseInt(line[2]));
        }

        return cities;
    }
}
