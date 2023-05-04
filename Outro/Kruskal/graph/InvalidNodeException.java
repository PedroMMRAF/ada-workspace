package graph;

public class InvalidNodeException extends Exception {
    public InvalidNodeException(int node) {
        super(Integer.toString(node));
    }
}
