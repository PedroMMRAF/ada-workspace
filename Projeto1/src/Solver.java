public class Solver {
    private static final char EMPTY_PLOT = 'e';
    private static final char HARP_PLOT = 'h';
    private static final char POTION_PLOT = 'p';
    private static final char CLOAK_PLOT = 'c';
    private static final char DOG_PLOT = '3';
    private static final char TROLL_PLOT = 't';
    private static final char DRAGON_PLOT = 'd';

    private static final int HARP_TIME = 4;
    private static final int POTION_TIME = 5;
    private static final int CLOAK_TIME = 6;
    private static final int CROSS_WITH_ITEM_TIME = 3;

    private final char[] plot;

    private int noItemTime;
    private int harpTime;
    private int potionTime;
    private int cloakTime;

    private int foundMonster;

    public Solver(char[] plot) {
        this.plot = plot;

        this.noItemTime = 0;
        this.harpTime = Integer.MAX_VALUE;
        this.potionTime = Integer.MAX_VALUE;
        this.cloakTime = Integer.MAX_VALUE;

        this.foundMonster = 0;
    }

    public int solve() {
        for (char c : plot) {
            switch (c) {
                case EMPTY_PLOT -> crossEmptyField();
                case HARP_PLOT -> crossHarpField();
                case POTION_PLOT -> crossPotionField();
                case CLOAK_PLOT -> crossCloakField();
                case DOG_PLOT -> crossDogField();
                case TROLL_PLOT -> crossTrollField();
                case DRAGON_PLOT -> crossDragonField();
            }
        }
        return getResult();
    }

    private void addHarpTime(int time) {
        if (harpTime < Integer.MAX_VALUE)
            harpTime += time;
    }

    private void addPotionTime(int time) {
        if (potionTime < Integer.MAX_VALUE)
            potionTime += time;
    }

    private void addCloakTime(int time) {
        if (cloakTime < Integer.MAX_VALUE)
            cloakTime += time;
    }

    private void crossEmptyField() {
        noItemTime = getResult() + 1 + foundMonster;

        addHarpTime(CROSS_WITH_ITEM_TIME);
        addPotionTime(CROSS_WITH_ITEM_TIME);
        addCloakTime(CROSS_WITH_ITEM_TIME);

        this.foundMonster = 0;
    }

    private void crossHarpField() {
        noItemTime = getResult() + 1 + foundMonster;
        harpTime = noItemTime + 1;

        addPotionTime(CROSS_WITH_ITEM_TIME);
        addCloakTime(CROSS_WITH_ITEM_TIME);

        this.foundMonster = 0;
    }

    private void crossPotionField() {
        noItemTime = getResult() + 1 + foundMonster;
        potionTime = noItemTime + 1;

        addHarpTime(CROSS_WITH_ITEM_TIME);
        addCloakTime(CROSS_WITH_ITEM_TIME);

        this.foundMonster = 0;
    }

    private void crossCloakField() {
        noItemTime = getResult() + 1 + foundMonster;
        cloakTime = noItemTime + 1;

        addHarpTime(CROSS_WITH_ITEM_TIME);
        addPotionTime(CROSS_WITH_ITEM_TIME);

        this.foundMonster = 0;
    }

    private void crossDogField() {
        noItemTime = Integer.MAX_VALUE;

        addHarpTime(HARP_TIME);
        addPotionTime(POTION_TIME);
        addCloakTime(CLOAK_TIME);

        this.foundMonster = 1;
    }

    private void crossTrollField() {
        harpTime = noItemTime = Integer.MAX_VALUE;

        addPotionTime(POTION_TIME);
        addCloakTime(CLOAK_TIME);

        this.foundMonster = 1;
    }

    private void crossDragonField() {
        potionTime = harpTime = noItemTime = Integer.MAX_VALUE;

        addCloakTime(CLOAK_TIME);

        this.foundMonster = 1;
    }

    private int getResult() {
        return Math.min(noItemTime, Math.min(harpTime, Math.min(potionTime, cloakTime)));
    }
}
