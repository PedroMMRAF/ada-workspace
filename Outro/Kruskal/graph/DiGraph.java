package graph;

public interface DiGraph<T> extends Graph<T> {
    /**
     * @param node a node
     * @return The incoming degree of the specified node.
     */
    int inDegree(int node);

    /**
     * @param node a node
     * @return The outgoing degree of the specified node.
     */
    int outDegree(int node);

    /**
     * @param node a node
     * @return The incoming nodes adjacent to the specified node.
     */
    Iterable<Integer> inAdjacentNodes(int node);

    /**
     * @param node a node
     * @return The outgoing nodes adjacent to the specified node.
     */
    Iterable<Integer> outAdjacentNodes(int node);

    /**
     * @param node a node
     * @return The incoming edges incident on the specified node.
     */
    Iterable<Edge<T>> inIncidentEdges(int node);

    /**
     * @param node a node
     * @return The outgoing edges incident on the specified node.
     */
    Iterable<Edge<T>> outIncidentEdges(int node);
}
