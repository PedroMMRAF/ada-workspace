import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static char EASY_PLOT = 'e';
    public static char EASY_PLOT_HARP = 'h';
    public static char EASY_PLOT_POTION = 'p';
    public static char EASY_PLOT_CLOAK = 'c';
    public static char DOG_PLOT = '3';
    public static char TROLL_PLOT = 't';
    public static char DRAGON_PLOT = 'd';
    public static int HARP_TIME = 4;// dog
    public static int POTION_TIME = 5;// dog or troll
    public static int CLOAK_TIME = 6;// dog, troll or dragon

    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(in.readLine());
            for (int i = 0; i < T; i++) {
                System.out.println(solve(in.readLine()));
            }

        }
    }

    private static int solve(char[] plot) {
        int maxMonster = 4;
        int monsterOccurrences = 0;
        int easyPlotsOccurrences=0;
        for (int i = plot.length - 1; i > 0; i--) {
            if(plot[i]==EASY_PLOT)//fazer switch?
        }
    }
}