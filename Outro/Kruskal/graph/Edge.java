package graph;

public interface Edge<T> {
    /**
     * @return The edge's label.
     */
    T label();

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
