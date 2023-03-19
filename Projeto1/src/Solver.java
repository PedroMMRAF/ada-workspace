
public class Solver {
    public char EASY_PLOT = 'e';
    public char EASY_PLOT_HARP = 'h';
    public char EASY_PLOT_POTION = 'p';
    public char EASY_PLOT_CLOAK = 'c';
    public char DOG_PLOT = '3';
    public char TROLL_PLOT = 't';
    public char DRAGON_PLOT = 'd';
    public int HARP_TIME = 4;// dog
    public int POTION_TIME = 5;// dog or troll
    public int CLOAK_TIME = 6;// dog, troll or dragon
    public int NUMBER_OF_MONSTERS = 3;

    private int maxMonster;
    private int[] choices;
    private String plot;
    private boolean holdingItem;

    public Solver(String plot) {
        this.maxMonster = 0;// 0->cao,1->troll,2->dragao
        this.choices = new int[NUMBER_OF_MONSTERS];
        this.plot = plot;
        this.holdingItem = false;
    }

    public int solve() {
        int res = 0;
        if (hasMonster(plot.charAt(plot.length() - 1)))
            res--;
        for (int i = plot.length() - 1; i > 0; i--) {
            char c=plot.charAt(i);
            if (holdingItem) {
                if (isTrivialPlot(c))
                    crossEasyFieldHoldingItem();
                else if(hasObject(c))


            } else {// noItem
                if (hasMonster(c)) {

                }
            }
        }
        return res;
    }

    private void crossEasyFieldHoldingItem() {
        for (int i = maxMonster; i < choices.length; i++)
            choices[i] += 3;
    }

    private boolean hasMonster(char c) {
        return c == DOG_PLOT || c == TROLL_PLOT || c == DRAGON_PLOT;
    }

    private int getMonster(char c) {
        if (c == DOG_PLOT)
            return HARP_TIME;
        if (c == TROLL_PLOT)
            return POTION_TIME;
        return CLOAK_TIME;
    }

    private boolean isTrivialPlot(char field) {
        return field == EASY_PLOT;
    }

    private boolean hasObject(char field) {
        return field == EASY_PLOT_HARP || field == EASY_PLOT_POTION || field == EASY_PLOT_CLOAK;
    }
}
