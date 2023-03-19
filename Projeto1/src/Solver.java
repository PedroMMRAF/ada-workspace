
public class Solver {
    private static final char EASY_PLOT = 'e';
    private static final char EASY_PLOT_HARP = 'h';
    private static final char EASY_PLOT_POTION = 'p';
    private static final char EASY_PLOT_CLOAK = 'c';
    private static final char DOG_PLOT = '3';
    private static final char TROLL_PLOT = 't';
    private static final char DRAGON_PLOT = 'd';
    private static final int HARP_TIME = 4;// dog
    private static final int POTION_TIME = 5;// dog or troll
    private static final int CLOAK_TIME = 6;// dog, troll or dragon
    private static final int CROSS_WITH_ITEM_TIME = 3;
    private static final int NUMBER_OF_MONSTERS = 3;
    private static final int NO_ITEM_POSITION = 0;
    private static final int HARP_POSITION = 1;
    private static final int POTION_POSITION = 2;
    private static final int CLOAK_POSITION = 3;

    private String plot;
    private int[] times;
    private int foundMonster;

    public Solver(String plot) {
        this.plot = plot;
        this.times = new int[NUMBER_OF_MONSTERS + 1];// 0 -> No item; 1 -> Harp; 2 -> Potion; 3 -> Cloak;
        this.foundMonster = 0;
        initializeTimes();
    }

    private void initializeTimes() {
        for (int i = HARP_POSITION; i < times.length; i++)
            times[i] = Integer.MAX_VALUE;
    }

    public int solve() {
        for (int i = 0; i < plot.length(); i++) {
            char c = plot.charAt(i);
            if (isEmpty(c)) {
                crossEmptyField();
            }
            if (hasHarp(c)) {
                crossHarpField();
            }

            if (hasPotion(c)) {
                crossPotionField();
            }

            if (hasCloak(c)) {
                crossCloakField();
            }

            if (hasDog(c)) {
                crossDogField();
            }

            if (hasTroll(c)) {
                crossTrollField();
            }

            if (hasDragon(c)) {
                crossDragonField();
            }
        }
        return getResult();
    }

    private void crossEmptyField() {
        times[NO_ITEM_POSITION] = getResult() + 1 + foundMonster;
        addTime(HARP_POSITION, CROSS_WITH_ITEM_TIME);
        addTime(POTION_POSITION, CROSS_WITH_ITEM_TIME);
        addTime(CLOAK_POSITION, CROSS_WITH_ITEM_TIME);
        this.foundMonster = 0;
    }

    private void crossHarpField() {
        times[NO_ITEM_POSITION] = getResult() + 1 + foundMonster;
        times[HARP_POSITION] = times[NO_ITEM_POSITION] + 1;
        addTime(POTION_POSITION, CROSS_WITH_ITEM_TIME);
        addTime(CLOAK_POSITION, CROSS_WITH_ITEM_TIME);
        this.foundMonster = 0;
    }

    private void crossPotionField() {
        times[NO_ITEM_POSITION] = getResult() + 1 + foundMonster;
        times[POTION_POSITION] = times[NO_ITEM_POSITION] + 1;
        addTime(HARP_POSITION, CROSS_WITH_ITEM_TIME);
        addTime(CLOAK_POSITION, CROSS_WITH_ITEM_TIME);
        this.foundMonster = 0;
    }

    private void crossCloakField() {
        times[NO_ITEM_POSITION] = getResult() + 1 + foundMonster;
        times[CLOAK_POSITION] = times[NO_ITEM_POSITION] + 1;
        addTime(HARP_POSITION, CROSS_WITH_ITEM_TIME);
        addTime(POTION_POSITION, CROSS_WITH_ITEM_TIME);
        this.foundMonster = 0;
    }

    private void crossDogField() {
        times[NO_ITEM_POSITION] = Integer.MAX_VALUE;
        addTime(HARP_POSITION, HARP_TIME);
        addTime(POTION_POSITION, POTION_TIME);
        addTime(CLOAK_POSITION, CLOAK_TIME);
        this.foundMonster = 1;
    }

    private void crossTrollField() {
        times[HARP_POSITION] = times[NO_ITEM_POSITION] = Integer.MAX_VALUE;
        addTime(POTION_POSITION, POTION_TIME);
        addTime(CLOAK_POSITION, CLOAK_TIME);
        this.foundMonster = 1;
    }

    private void crossDragonField() {
        times[POTION_POSITION] = times[HARP_POSITION] = times[NO_ITEM_POSITION] = Integer.MAX_VALUE;
        addTime(CLOAK_POSITION, CLOAK_TIME);
        this.foundMonster = 1;
    }

    private void addTime(int pos, int time) {
        if (times[pos] < Integer.MAX_VALUE)
            times[pos] += time;
    }

    private int getResult() {
        return Math.min(times[0], Math.min(times[1], Math.min(times[2], times[3])));
    }

    private boolean isEmpty(char field) {
        return field == EASY_PLOT;
    }

    private boolean hasCloak(char field) {
        return field == EASY_PLOT_CLOAK;
    }

    private boolean hasPotion(char field) {
        return field == EASY_PLOT_POTION;
    }

    private boolean hasHarp(char field) {
        return field == EASY_PLOT_HARP;
    }

    private boolean hasDog(char field) {
        return field == DOG_PLOT;
    }

    private boolean hasTroll(char field) {
        return field == TROLL_PLOT;
    }

    private boolean hasDragon(char field) {
        return field == DRAGON_PLOT;
    }
}
