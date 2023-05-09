import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Util {
    public static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    public static int[] readIntLine(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
