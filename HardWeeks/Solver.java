import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Miguel Valido 60477
 * @author Pedro Fernandes 60694
 */
public class Solver {
    private final int hardWeekLimit;

    private List<Integer>[] suc;
    private int[] pred;

    @SuppressWarnings("unchecked")
    public Solver(int numberOfTasks, int hardWeekLimit) {
        this.hardWeekLimit = hardWeekLimit;
        this.suc = new List[numberOfTasks];
        this.pred = new int[numberOfTasks];

        for (int i = 0; i < numberOfTasks; i++)
            suc[i] = new ArrayList<Integer>();
    }

    public void addEdge(int from, int to) {
        pred[to]++;
        suc[from].add(to);
    }

    public int[] solve() {
        int maxTasksInWeek = 0;
        int hardWeeks = 0;

        Queue<Integer> bag1 = new LinkedList<Integer>();
        Queue<Integer> bag2 = new LinkedList<Integer>();

        for (int i = 0; i < pred.length; i++) {
            if (pred[i] == 0)
                bag1.add(i);
        }

        while (!bag1.isEmpty()) {
            if (bag1.size() > hardWeekLimit)
                hardWeeks++;

            if (bag1.size() > maxTasksInWeek)
                maxTasksInWeek = bag1.size();

            while (!bag1.isEmpty()) {
                int task = bag1.remove();

                for (int suc : suc[task]) {
                    pred[suc]--;

                    if (pred[suc] == 0)
                        bag2.add(suc);
                }
            }

            Queue<Integer> bagt = bag1;
            bag1 = bag2;
            bag2 = bagt;
        }

        return new int[] { maxTasksInWeek, hardWeeks };
    }
}
