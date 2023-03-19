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
}