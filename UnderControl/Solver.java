import java.util.ArrayList;
import java.util.List;

/**
 * @author Miguel Valido 60477
 * @author Pedro Fernandes 60694
 */
public class Solver {
    static public class Edge {
        private int precedent;
        private int successor;
        private int cost;

        public Edge(int precedent, int successor, int cost) {
            this.precedent = precedent;
            this.successor = successor;
            this.cost = cost;
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

    private List<Edge> tasks;

    public Solver(int numOfNodes, int numOfTasks) {
        tasks = new ArrayList<>(numOfTasks);
    }

    public void addEdge(int i, int j, int k) {
        tasks.add(new Edge(i, j, k));
    }

    public int[] solve() {
        return null;
    }
}
