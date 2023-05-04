package graph;

public interface UndiGraph<L> extends AnyGraph<L> {
    /**
     * @param node a node
     * @return The degree of the specified node.
     */
    int degree(int node);

    /**
     * @param node a node
     * @return The nodes adjacent to the specified node.
     */
    Iterable<Integer> adjacentNodes(int node);

    /**
     * @param node a node
     * @return The edges incident on the specified node.
     */
    Iterable<Edge<L>> incidentEdges(int node);
}
