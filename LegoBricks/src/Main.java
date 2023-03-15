import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static int[] MAX_BLOCKS = {};

    public static void main(String[] args) throws IOException {
        System.out.println(run(System.in));
    }

    public static long run(InputStream input) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(input))) {
            String[] rows_columns = in.readLine().split(" ");
            int rows = Integer.parseInt(rows_columns[0]);
            int columns = Integer.parseInt(rows_columns[1]);

            LegoCombiner combiner = new LegoCombiner(columns);

            for (int i = 0; i < rows; i++) {
                fillPowers(combiner, in.readLine().toCharArray());
            }

            return combiner.solve();
        }
    }

    private static void fillPowers(LegoCombiner combiner, char[] line) {
        int size = 0;

        char seq_block = '.';
        for (char block : line) {
            if (block != seq_block) {
                if (seq_block != '.') {
                    combiner.addSpace(size);
                }
                seq_block = block;
                size = 0;
            }
            size++;
        }

        if (seq_block != '.') {
            combiner.addSpace(size);
        }
    }
}