package HardWeeks.src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solver {
    private int hardWeekLimit;

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
        int maxNumberOfTasksInWeek = 0;
        int numberOfHardWeeks = 0;
        Queue<Integer> bag = new LinkedList<Integer>();
        Queue<Integer> bag2 = new LinkedList<Integer>();
        for (int i = 0; i < pred.length; i++) {
            if (pred[i] == 0)
                bag.add(i);
        }
        if (bag.size() > hardWeekLimit)
            numberOfHardWeeks++;
        if (bag.size() > maxNumberOfTasksInWeek)
            maxNumberOfTasksInWeek = bag.size();
        do {
            while (!bag.isEmpty()) {
                int task = bag.remove();
                for (int suc : suc[task]) {
                    pred[suc]--;
                    if (pred[suc] == 0)
                        bag2.add(suc);
                }
            }
            if (bag2.size() > hardWeekLimit)
                numberOfHardWeeks++;
            if (bag2.size() > maxNumberOfTasksInWeek)
                maxNumberOfTasksInWeek = bag2.size();
            Queue<Integer> temp = bag;
            bag = bag2;
            bag2 = temp;
        } while (!bag.isEmpty());
        return new int[] { maxNumberOfTasksInWeek, numberOfHardWeeks };
    }
}
