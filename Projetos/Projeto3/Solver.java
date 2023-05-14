import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class Solver {
    private static class Edge {
        private int outNode;
        private int label;

        public Edge(int outNode, int label) {
            this.outNode = outNode;
            this.label = label;
        }

        public int outNode() {
            return outNode;
        }

        public int label() {
            return label;
        }
    }

    private static int SOURCE = 0;

    private int sink;
    private int[] departures;

    // Represents the outIncidentEdges on linked lists
    private List<Edge>[] network;

    @SuppressWarnings("unchecked")
    public Solver(int regions) {
        departures = new int[regions];

        network = new List[regions * 2 + 1];

        for (int i = 0; i < regions * 2 + 1; i++)
            network[i] = new LinkedList<>();
    }

    public void addRegion(int i, int population, int departure) {
        departures[i++] = departure;

        addEdge(SOURCE, i, population);
        addEdge(i, departures.length + i, departure);
    }

    public void addLink(int start, int end) {
        addEdge(departures.length + start, end, departures[start - 1]);
        addEdge(departures.length + end, start, departures[end - 1]);
    }

    public void setSafeRegion(int region) {
        sink = region;
    }

    private void addEdge(int start, int end, int label) {
        network[start].add(new Edge(end, label));
        network[end].add(new Edge(start, 0));
    }

    public int solve() {
        int[][] flow = new int[network.length][network.length];
        int[] via = new int[network.length];

        int flowValue = 0;
        int increment;

        while ((increment = findPath(flow, via)) != 0) {
            flowValue += increment;

            int node = sink;

            while (node != SOURCE) {
                int origin = via[node];

                flow[origin][node] += increment;
                flow[node][origin] -= increment;

                node = origin;
            }
        }

        return flowValue;
    }

    private int findPath(int[][] flow, int[] via) {
        Queue<Integer> waiting = new LinkedList<>();

        boolean[] found = new boolean[network.length];
        int[] pathIncrement = new int[network.length];

        waiting.add(SOURCE);
        found[SOURCE] = true;
        via[SOURCE] = 0;
        pathIncrement[SOURCE] = Integer.MAX_VALUE;

        while (!waiting.isEmpty()) {
            int origin = waiting.remove();

            for (Edge edge : network[origin]) {
                int destination = edge.outNode();
                int residue = edge.label() - flow[origin][destination];

                if (!found[destination] && residue > 0) {
                    found[destination] = true;
                    via[destination] = origin;
                    pathIncrement[destination] = Math.min(pathIncrement[origin], residue);

                    if (destination == sink)
                        return pathIncrement[destination];

                    waiting.add(destination);
                }
            }
        }

        return 0;
    }
}