/**
 * @author Miguel Valido 60477
 * @author Pedro Fernandes 60694
 */
public class Solver {
    private static final char EMPTY_PLOT = 'e';
    private static final char HARP_PLOT = 'h';
    private static final char POTION_PLOT = 'p';
    private static final char CLOAK_PLOT = 'c';
    private static final char DOG_PLOT = '3';
    private static final char TROLL_PLOT = 't';
    private static final char DRAGON_PLOT = 'd';

    private static final int NO_ITEM = 0;
    private static final int HARP = 1;
    private static final int POTION = 2;
    private static final int CLOAK = 3;
    private static final int[] items = { NO_ITEM, HARP, POTION, CLOAK };

    private static final int CROSS_PLOT_WITHOUT_ITEM = 1;
    private static final int CROSS_PLOT_WITH_ITEM = 3;

    private final String plot;

    private int[] times;

    private int foundMonster;

    public Solver(String plot) {
        this.plot = plot;

        times = new int[4];

        times[NO_ITEM] = foundMonster = 0;
        times[HARP] = times[POTION] = times[CLOAK] = Integer.MIN_VALUE;
    }

    public int solve() {
        for (int i = 0; i < plot.length(); i++) {
            switch (plot.charAt(i)) {
                case EMPTY_PLOT -> crossEasyPlot(NO_ITEM);
                case HARP_PLOT -> crossEasyPlot(HARP);
                case POTION_PLOT -> crossEasyPlot(POTION);
                case CLOAK_PLOT -> crossEasyPlot(CLOAK);
                case DOG_PLOT -> crossPlot(HARP);
                case TROLL_PLOT -> crossPlot(POTION);
                case DRAGON_PLOT -> crossPlot(CLOAK);
            }
        }
        return getMinOfTimes();
    }

    private void crossEasyPlot(int plotItem) {
        // We drop any item after finding a monster
        times[NO_ITEM] = getMinOfTimes() + CROSS_PLOT_WITHOUT_ITEM + foundMonster;

        for (int item : items)
            if (plotItem == item)
                // Just picked up the item
                times[item] = times[NO_ITEM] + 1;
            else
                // Still had the item
                times[item] += CROSS_PLOT_WITH_ITEM;

        foundMonster = 0;
    }

    private void crossPlot(int monster) {
        for (int item : items)
            if (item >= monster)
                times[item] += CROSS_PLOT_WITH_ITEM + item;
            else
                times[item] = Integer.MIN_VALUE;

        foundMonster = 1;
    }

    private int getMinOfTimes() {
        int min = Integer.MAX_VALUE;

        for (int t : times)
            if (t > 0)
                min = Math.min(min, t);

        return min;
    }
}
