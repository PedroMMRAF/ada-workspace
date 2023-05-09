import java.util.HashMap;
import java.util.Map;
import java.awt.Point;

public class Solver {

    private Map<Point, Point> partition;
    private int segments;

    public Solver(int segmentCount) {
        partition = new HashMap<>();
        segments = 0;
    }

    public void addEdge(int i, int j, int k, int l) {
        Point p1 = new Point(i, j);
        Point p2 = new Point(k, l);

        Point rep1 = findPathCompr(p1);
        Point rep2 = findPathCompr(p2);

        if (!rep1.equals(rep2)) {
            union(rep1, rep2);
            segments++;
        }
    }

    public Point findPathCompr(Point p) {
        Point r = partition.get(p);

        if (r == null) {
            partition.put(p, p);
            return p;
        }

        if (r.equals(p))
            return p;

        Point root = findPathCompr(r);

        partition.put(p, root);

        return root;
    }

    public void union(Point rep1, Point rep2) {
        partition.put(rep2, rep1);
    }

    public int result() {
        return segments;
    }
}
