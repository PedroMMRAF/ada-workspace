import java.util.Scanner;

public class Slow {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int result = Integer.MIN_VALUE;
            int numberOfChildren = in.nextInt();
            in.nextLine();

            while (numberOfChildren-- > 0) {
                int sticks = in.nextInt();
                while (sticks-- > 0)
                    result = Math.max(result, in.nextInt());
                in.nextLine();
            }

            System.out.println(result);
        }
    }
}
