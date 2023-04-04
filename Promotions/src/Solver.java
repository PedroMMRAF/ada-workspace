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

        successors = (List<Integer>[]) new List[employees];
        predecessors = (List<Integer>[]) new List[employees];

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
        int notPromoted = 0;// n vou mm ser promovido ):
        int promoted = 0;// posso dormir descansado, vou ser promovido (:
        int promotedB = 0;

        for (int i = 0; i < employees; i++) {
            if (allPred(i).size() >= upperBound)
                notPromoted++;

            int succ = allSucc(i).size();

            if (succ >= lowerBound)
                promoted++;

            if (succ >= upperBound)
                promotedB++;
        }

        int promotedFinal = 0;
        int promotedBFinal = 0;

        for (int i = 0; i < employees; i++) {
            int succ = allSucc(i).size();
            int pred = allPred(i).size();

            if (succ >= lowerBound && pred <= lowerBound && pred <= promoted)
                promotedFinal++;

            if (succ >= upperBound && pred <= upperBound && pred <= promotedB)
                promotedBFinal++;
        }

        return new int[] { promotedBFinal, promotedFinal, notPromoted };
    }
}
