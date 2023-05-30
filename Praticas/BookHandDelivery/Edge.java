public class Edge implements Comparable<Edge> {
    private Node out;
    private int cost;

    public Edge(Node out, int cost) {
        this.out = out;
        this.cost = cost;
    }

    public Node getOut() {
        return out;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.cost, other.cost);
    }
}