import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Fast2 {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int result = Integer.MIN_VALUE;
            int numberOfChildren = Integer.parseInt(in.readLine());

            while (numberOfChildren-- > 0) {
                String[] tokens = in.readLine().split(" ");
                int sticks = Integer.parseInt(tokens[0]) + 1;
                for (int i = 1; i < sticks; i++) {
                    result = Math.max(Integer.parseInt(tokens[i]), result);
                }
            }

            System.out.println(result);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
