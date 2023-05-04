package graph;

import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class UndiGraphImpl<L> implements UndiGraph<L> {
    private int nodes;
    private List<Edge<L>> edges;
    private List<Edge<L>>[] incidentEdges;

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
    public Edge<L> addEdge(int node1, int node2, L label) {
        Edge<L> edge = new EdgeImpl<>(node1, node2, label);

        this.edges.add(edge);
        this.incidentEdges[node1].add(edge);
        this.incidentEdges[node2].add(edge);

        return edge;
    }

    private boolean makesEdge(int node1, int node2, Edge<L> edge) {
        return (edge.firstNode() == node1 || edge.firstNode() == node2)
                && (edge.secondNode() == node1 || edge.secondNode() == node2);
    }

    @Override
    public boolean edgeExists(int node1, int node2) {
        for (Edge<L> edge : edges) {
            if (makesEdge(node1, node2, edge))
                return true;
        }
        return false;
    }

    @Override
    public Iterable<Integer> nodes() {
        return () -> IntStream.range(0, nodes).iterator();
    }

    @Override
    public Iterable<Edge<L>> edges() {
        return () -> edges.iterator();
    }

    @Override
    public int degree(int node) {
        return incidentEdges[node].size();
    }

    @Override
    public Iterable<Integer> adjacentNodes(int node) {
        return () -> new Iterator<Integer>() {
            private Iterator<Edge<L>> underlying = incidentEdges[node].iterator();

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
    public Iterable<Edge<L>> incidentEdges(int node) {
        return () -> incidentEdges[node].iterator();
    }
}
