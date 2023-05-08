package graph;

import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class UndiGraphImpl<T> implements UndiGraph<T> {
    private int nodes;
    private List<Edge<T>> edges;
    private List<Edge<T>>[] incidentEdges;

    @SuppressWarnings("unchecked")
    public UndiGraphImpl(int nodes) {
        this.nodes = nodes;
        this.edges = new LinkedList<>();
        this.incidentEdges = new List[nodes];

        for (int i = 0; i < nodes; i++) {
            this.incidentEdges[i] = new LinkedList<>();
        }
    }

    @Override
    public int numNodes() {
        return nodes;
    }

    @Override
    public int numEdges() {
        return edges.size();
    }

    @Override
    public Edge<T> addEdge(int node1, int node2, T label) {
        Edge<T> edge = new EdgeImpl<>(node1, node2, label);

        this.edges.add(edge);
        this.incidentEdges[node1].add(edge);
        this.incidentEdges[node2].add(edge);

        return edge;
    }

    private boolean isEdge(int node1, int node2, Edge<T> edge) {
        return (edge.firstNode() == node1 && edge.secondNode() == node2)
                || (edge.firstNode() == node2 && edge.secondNode() == node1);
    }

    @Override
    public boolean edgeExists(int node1, int node2) {
        for (Edge<T> edge : edges) {
            if (isEdge(node1, node2, edge))
                return true;
        }
        return false;
    }

    @Override
    public Iterable<Integer> nodes() {
        return () -> IntStream.range(0, nodes).iterator();
    }

    @Override
    public Iterable<Edge<T>> edges() {
        return () -> edges.iterator();
    }

    @Override
    public int degree(int node) {
        return incidentEdges[node].size();
    }

    @Override
    public Iterable<Integer> adjacentNodes(int node) {
        return () -> new Iterator<Integer>() {
            private Iterator<Edge<T>> underlying = incidentEdges[node].iterator();

            @Override
            public boolean hasNext() {
                return underlying.hasNext();
            }

            @Override
            public Integer next() {
                try {
                    return underlying.next().oppositeNode(node);
                } catch (InvalidNodeException e) {
                    return -1;
                }
            }
        };
    }

    @Override
    public Iterable<Edge<T>> incidentEdges(int node) {
        return () -> incidentEdges[node].iterator();
    }
}
