import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @author Miguel Valido 60477
 * @author Pedro Fernandes 60694
 */
public class Solver {

    class EdgeComparator implements Comparator<Edge> {
        public int compare(Edge e1, Edge e2) {
            // First compare by precedent
            int precedentCompare = Integer.compare(e1.getPrecedent(), e2.getPrecedent());
            if (precedentCompare != 0) {
                return precedentCompare;
            }
            return Integer.compare(e1.getSuccessor(), e2.getSuccessor());
        }
    }

    static public class Edge {
        private int precedent;
        private int cost;
        private int successor;

        public Edge(int precedent, int successor, int cost) {
            this.precedent = precedent;
            this.cost = cost;
            this.successor = successor;
        }

        public int getPrecedent() {
            return precedent;
        }

        public int getSuccessor() {
            return successor;
        }

        public int getCost() {
            return cost;
        }
    }

    private List<Edge>[] tasksByPred;
    private int[] inDegree;
    private int[] maxCost;
    private List<Edge> toReturn;

    @SuppressWarnings("unchecked")
    public Solver(int numOfNodes, int numOfTasks) {
        tasksByPred = new List[numOfNodes + 1];
        inDegree = new int[numOfNodes + 1];
        maxCost = new int[numOfNodes + 1];
        toReturn = new ArrayList<Edge>();
        for (int i = 1; i <= numOfNodes; i++) {
            tasksByPred[i] = new ArrayList<Edge>();
        }
    }

    public void addEdge(int i, int j, int k) {
        tasksByPred[i].add(new Edge(i, j, k));
        inDegree[j]++;
    }

    public List<Edge> solve() {
        Stack<Integer> bag = new Stack<Integer>();
        for (int i = 1; i < maxCost.length; i++)
            if (inDegree[i] == 0)
                bag.push(i);

        while (!bag.isEmpty()) {
            int node = bag.pop();
            for (Edge edge : tasksByPred[node]) {
                int cost = maxCost[node] + edge.getCost();
                if (maxCost[edge.getSuccessor()] < cost)
                    maxCost[edge.getSuccessor()] = cost;
                inDegree[edge.getSuccessor()]--;
                if (inDegree[edge.getSuccessor()] == 0)
                    bag.push(edge.getSuccessor());
            }
        }

        for (int i = 1; i < maxCost.length; i++)
            for (Edge edge : tasksByPred[i])
                if (maxCost[i] + edge.getCost() < maxCost[edge.getSuccessor()])
                    toReturn.add(edge);

        Collections.sort(toReturn, new EdgeComparator());
        return toReturn;
    }

}
