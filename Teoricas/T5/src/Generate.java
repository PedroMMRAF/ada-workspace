package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Generate {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(reader.readLine());
            int size = Integer.parseInt(reader.readLine());

            MatrixProblem p = new MatrixProblem(count);

            for (int i = 0; i < count; i++) {
                int d = (int) (Math.random() * (size - 1)) + 1;
                System.out.printf("%d ", d);
                p.add(d);
            }

            System.out.println();
            System.out.println(p.solve());
        }
    }
}
