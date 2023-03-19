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
    public static int NUMBER_OF_MONSTERS = 3;

    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(in.readLine());
            for (int i = 0; i < T; i++) {
                System.out.println(solve(in.readLine()));
            }

        }
    }

    private static int solve(char[] plot) {
        int res = 0;
        boolean holdingItem = false;
        int[] choices = new int[NUMBER_OF_MONSTERS];// 0->harp,1->potion,2->cloak
        if (hasMonster(plot[plot.length - 1]))
            res--;
        for (int i = plot.length - 1; i > 0; i--) {
            if (holdingItem) {

            } else {// noItem
                if (hasMonster(plot[i])) {

                }
            }
        }
        return res;
    }

    /*
     * private static int solve(char[] plot) {
     * int res = 0;
     * boolean foundCloak = false;
     * boolean foundPotion = false;
     * int maxMonster = HARP_TIME;
     * int monsterOccurrences = 0;
     * int easyPlotsOccurrences = 0;
     * int cloakMinusPotion = 0;
     * int cloakMinusHarp = 0;
     * int potionMinusHarp = 0;
     * for (int i = plot.length - 1; i > 0; i--) {
     * if (monsterOccurrences == 0) {
     * if (isEasyPlot(plot[i])) {// fazer switch?
     * if (isEasyPlot(plot[i - 1])) {
     * res++;
     * } else {
     * res += 2;// Se a casa i-1 tiver monstro precisamos de entrar com objeto mas
     * podemos sair
     * // sem
     * }
     * } else {// primeiro monstro encontrado
     * monsterOccurrences++;
     * if (plot[i] == DOG_PLOT) {
     * maxMonster = HARP_TIME;
     * cloakMinusHarp = 2;
     * cloakMinusPotion = 1;
     * potionMinusHarp = 1;
     * } else {
     * cloakMinusHarp = 0;
     * if (plot[i] == TROLL_PLOT) {
     * maxMonster = POTION_TIME;
     * cloakMinusPotion = 1;
     * } else {
     * maxMonster = CLOAK_TIME;
     * cloakMinusPotion = 0;
     * }
     * }
     * 
     * }
     * } else {// temos de estar a carregar um objeto, visto que
     * monsterOccurrences>0
     * if (maxMonster == CLOAK_TIME) {// dragao
     * if (plot[i] == EASY_PLOT_CLOAK) {
     * res += monsterOccurrences * CLOAK_TIME;
     * monsterOccurrences = 0;
     * if (isEasyPlot(plot[i - 1]))
     * res += 2;
     * else
     * res += 3;
     * } else if (isEasyPlot(plot[i])) {
     * res += 3;
     * } else
     * monsterOccurrences++;
     * } else if (maxMonster == POTION_TIME) {// troll
     * if (foundCloak) {
     * if (isTrivialPlot(plot[i]) || plot[i] == EASY_PLOT_HARP) {
     * cloakMinusPotion -= 2;
     * easyPlotsOccurrences++;
     * } else if (plot[i] == DOG_PLOT || plot[i] == TROLL_PLOT) {
     * monsterOccurrences++;
     * cloakMinusPotion++;
     * }
     * }
     * }
     * }
     * }
     * return res;
     * }
     */
    private static boolean hasMonster(char c) {
        return c == DOG_PLOT || c == TROLL_PLOT || c == DRAGON_PLOT;
    }

    private static int getMonster(char c) {
        if (c == DOG_PLOT)
            return HARP_TIME;
        if (c == TROLL_PLOT)
            return POTION_TIME;
        return CLOAK_TIME;
    }

    private static boolean isTrivialPlot(char field) {
        return field == EASY_PLOT;
    }

    private static boolean hasObject(char field) {
        return field == EASY_PLOT_HARP || field == EASY_PLOT_POTION || field == EASY_PLOT_CLOAK;
    }
}