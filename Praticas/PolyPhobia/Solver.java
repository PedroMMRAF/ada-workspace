import java.util.HashMap;
import java.util.Map;

public class Solver {
    private Map<Integer, Integer> map;
    private int[] partition;
    private int segments;

    public Solver(int segmentCount) {
        map = new HashMap<>(segmentCount * 2);
        partition = new int[segmentCount * 2];
        segments = 0;

        for (int i = 0; i < segmentCount * 2; i++) {
            partition[i] = -1;
        }
    }

    public void addEdge(int i, int j, int k, int l) {
        int p1 = point(i, j);
        int p2 = point(k, l);

        map.putIfAbsent(p1, map.size());
        map.putIfAbsent(p2, map.size());

        int rep1 = findPathCompr(map.get(p1));
        int rep2 = findPathCompr(map.get(p2));

        if (rep1 != rep2) {
            union(rep1, rep2);
            segments++;
        }
    }

    private int point(int i, int j) {
        return i + j * 1000;
    }

    private int findPathCompr(int p) {
        if (partition[p] == -1)
            return p;

        return partition[p] = findPathCompr(partition[p]);
    }

    private void union(int rep1, int rep2) {
        partition[rep2] = rep1;
    }

    public int result() {
        return segments;
    }

}
