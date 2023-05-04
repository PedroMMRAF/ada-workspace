package graph;

public class EdgeImpl<L> implements Edge<L> {
    private L label;
    private int node1;
    private int node2;

    public EdgeImpl(int node1, int node2, L label) {
        this.node1 = node1;
        this.node2 = node2;
        this.label = label;
    }

    @Override
    public L label() {
        return label;
    }

    @Override
    public int firstNode() {
        return node1;
    }

    @Override
    public int secondNode() {
        return node2;
    }

    @Override
    public int oppositeNode(int node) throws InvalidNodeException {
        if (node == node1)
            return node2;
        else if (node == node2)
            return node1;

        throw new InvalidNodeException(node);
    }
}
