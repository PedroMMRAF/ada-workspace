import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solver {
    private final int lower, upperBound, employees;

    private final List<Integer>[] successors, predecessors;

    @SuppressWarnings("unchecked")
    public Solver(int lower, int upper, int employees) {
        this.lower = lower;
        this.upperBound = upper;
        this.employees = employees;

        successors = (List<Integer>[]) new Object[employees];
        predecessors = (List<Integer>[]) new Object[employees];

        for (int i = 0; i < employees; i++) {
            successors[i] = new ArrayList<>();
            predecessors[i] = new ArrayList<>();
        }
    }

    public int getLowerBound() {
        return lower;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public int getEmployees() {
        return employees;
    }

    public void addEdge(int from, int to) {
        successors[from].add(to);
        predecessors[to].add(from);
    }

    public Set<Integer> allPred(int node) {
        List<Integer> pred = predecessors[node];
        Set<Integer> result = new HashSet<>();

        for (int p : pred) {
            result.addAll(allPred(p));
        }

        return result;
    }

    public int[] solve() {
        int notPromoted = 0;
        for (int i = 0; i < employees; i++) {
            if (allPred(i).size() > upperBound) {
                notPromoted++;
            }
        }

        return new int[] { notPromoted };
    }
}
