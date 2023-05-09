package graph;

public interface Graph<T> {
    /**
     * @return The number of nodes this graph has.
     */
    int numNodes();

    /**
     * @return The number of edges this graph has.
     */
    int numEdges();

    /**
     * Adds an edge to the graph.
     * 
     * @param node1 The origin node
     * @param node2 The destination node
     * @param label The node's label
     * @return The created edge.
     */
    Edge<T> addEdge(int node1, int node2, T label);

    /**
     * @param node1 The origin node
     * @param node2 The destination node
     * @return `true` if an edge with the given nodes exists, `false` otherwise.
     */
    boolean edgeExists(int node1, int node2);

    /**
     * @return An iterable of all nodes.
     */
    Iterable<Integer> nodes();

    /**
     * @return An iterable of all edges.
     */
    Iterable<Edge<T>> edges();
}
