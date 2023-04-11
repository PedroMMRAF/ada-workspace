import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solver {
    private final int lowerBound, upperBound, employees;

    private final List<Integer>[] successors, predecessors;

    @SuppressWarnings("unchecked")
    public Solver(int lower, int upper, int employees) {
        this.lowerBound = lower;
        this.upperBound = upper;
        this.employees = employees;

        successors = new List[employees];
        predecessors = new List[employees];

        for (int i = 0; i < employees; i++) {
            successors[i] = new ArrayList<>();
            predecessors[i] = new ArrayList<>();
        }
    }

    public int getLowerBound() {
        return lowerBound;
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
            result.add(p);
            result.addAll(allPred(p));
        }

        return result;
    }

    public Set<Integer> allSucc(int node) {
        List<Integer> succ = successors[node];
        Set<Integer> result = new HashSet<>();

        for (int s : succ) {
            result.add(s);
            result.addAll(allSucc(s));
        }

        return result;
    }

    public int[] solve() {
        int notPromoted = 0;
        int promotedA = 0;
        int promotedB = 0;

        for (int i = 0; i < employees; i++) {
            if (allPred(i).size() >= upperBound)
                notPromoted++;

            int grade = employees - allSucc(i).size();

            if (grade <= lowerBound)
                promotedA++;

            if (grade <= upperBound)
                promotedB++;
        }

        return new int[] { promotedA, promotedB, notPromoted };
    }
}