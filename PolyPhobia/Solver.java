import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Solver {
    private class Point implements Serializable {
        private int a;
        private int b;

        public Point(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + a;
            result = prime * result + b;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Point other = (Point) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                return false;
            if (a != other.a)
                return false;
            if (b != other.b)
                return false;
            return true;
        }

        private Solver getEnclosingInstance() {
            return Solver.this;
        }
    }

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
