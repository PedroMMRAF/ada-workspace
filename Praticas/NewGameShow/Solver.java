import java.util.LinkedList;
import java.util.List;

public class Solver {
    private final List<Integer>[] graph;
    private final int[][] labels;

    @SuppressWarnings("unchecked")
    public Solver(int nodes) {
        graph = new List[nodes];
        labels = new int[nodes][nodes];

        for (int n = 0; n < nodes; n++)
            graph[n] = new LinkedList<>();
    }

    public void addEdge(int start, int end, int label) {
        graph[end].add(start);
        labels[end][start] = -label;
    }

    public int solve(int start, int goal, int budget) throws Bruh {
        int change = -bellmanFord(goal, start);

        if (change > 0)
            throw new Bruh();

        return Math.max(budget + change, 0);
    }

    private int bellmanFord(int start, int goal) throws Bruh {
        int[] length = new int[graph.length];
        int[] via = new int[graph.length];

        for (int n = 0; n < graph.length; n++)
            length[n] = Integer.MAX_VALUE;

        length[start] = 0;
        via[start] = start;

        boolean changes = false;
        for (int i = 1; i < graph.length; i++) {
            changes = updateLenghts(length, via);
            if (!changes || length[goal] < 0)
                break;
        }

        if (changes && updateLenghts(length, via))
            throw new Bruh();

        return length[goal];
    }

    private boolean updateLenghts(int[] length, int[] via) {
        boolean changes = false;
        for (int tail = 0; tail < graph.length; tail++) {
            for (int head : graph[tail]) {
                if (length[tail] == Integer.MAX_VALUE)
                    continue;

                int newLen = length[tail] + labels[tail][head];

                if (newLen >= length[head])
                    continue;

                length[head] = newLen;
                via[head] = tail;
                changes = true;
            }
        }
        return changes;
    }
}