package graph;

public interface Edge<LL> {
    /**
     * @return The edge's label.
     */
    LL label();

    /**
     * @return The edge's origin node.
     */
    int firstNode();

    /**
     * @return The edge's destination node.
     */
    int secondNode();

    /**
     * @param node a node
     * @return The edge's node opposite to the given node.
     */
    int oppositeNode(int node) throws InvalidNodeException;
}
