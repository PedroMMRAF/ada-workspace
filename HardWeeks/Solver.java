package HardWeeks;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    private int hardWeekLimit;

    private List<Integer>[] suc;
    private List<Integer>[] pred;

    @SuppressWarnings("unchecked")
    public Solver(int numberOfTasks, int hardWeekLimit) {
        this.hardWeekLimit = hardWeekLimit;
        this.suc = new List[numberOfTasks];
        this.pred = new List[numberOfTasks];

        for (int i = 0; i < numberOfTasks; i++) {
            suc[i] = new ArrayList<Integer>();
            pred[i] = new ArrayList<Integer>();
        }
    }
}
