package graph;

import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class DiGraphImpl<T> implements DiGraph<T> {
    private int nodes;
    private List<Edge<T>> edges;
    private List<Edge<T>>[] inIncidentEdges;
    private List<Edge<T>>[] outIncidentEdges;

    @SuppressWarnings("unchecked")
    public DiGraphImpl(int nodes) {
        this.nodes = nodes;
        this.edges = new LinkedList<>();
        this.inIncidentEdges = new List[nodes];
        this.outIncidentEdges = new List[nodes];

        for (int i = 0; i < nodes; i++) {
            this.inIncidentEdges[i] = new LinkedList<>();
            this.outIncidentEdges[i] = new LinkedList<>();
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
        this.outIncidentEdges[node1].add(edge);
        this.inIncidentEdges[node2].add(edge);

        return edge;
    }

    public boolean isEdge(int n1, int n2, Edge<T> edge) {
        return edge.firstNode() == n1 && edge.secondNode() == n2;
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
    public int inDegree(int node) {
        return inIncidentEdges[node].size();
    }

    @Override
    public int outDegree(int node) {
        return outIncidentEdges[node].size();
    }

    @Override
    public Iterable<Integer> inAdjacentNodes(int node) {
        return () -> new Iterator<Integer>() {
            private Iterator<Edge<T>> underlying = inIncidentEdges[node].iterator();

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
    public Iterable<Integer> outAdjacentNodes(int node) {
        return () -> new Iterator<Integer>() {
            private Iterator<Edge<T>> underlying = outIncidentEdges[node].iterator();

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
    public Iterable<Edge<T>> inIncidentEdges(int node) {
        return () -> inIncidentEdges[node].iterator();
    }

    @Override
    public Iterable<Edge<T>> outIncidentEdges(int node) {
        return () -> outIncidentEdges[node].iterator();
    }
}
