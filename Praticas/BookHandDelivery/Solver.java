import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solver {
    private static class Node {
        private int weekDay;
        private int person;
        private char location;

        private List<Edge> outEdges;

        public Node(int weekDay, int person, char location) {
            this.weekDay = weekDay;
            this.person = person;
            this.location = location;

            this.outEdges = new LinkedList<>();
        }

        public int getWeekDay() {
            return weekDay;
        }

        public int getPerson() {
            return person;
        }

        public char getLocation() {
            return location;
        }

        public void addEdge(Node other, int cost) {
            this.outEdges.add(new Edge(other, cost));
        }

        public Iterable<Edge> outEdges() {
            return () -> this.outEdges.iterator();
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + weekDay;
            result = prime * result + person;
            result = prime * result + location;
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
            Node other = (Node) obj;
            if (weekDay != other.weekDay)
                return false;
            if (person != other.person)
                return false;
            if (location != other.location)
                return false;
            return true;
        }
    }

    private static class Edge {
        private Node outNode;
        private int cost;

        public Edge(Solver.Node outNode, int cost) {
            this.outNode = outNode;
            this.cost = cost;
        }

        public Node getOutNode() {
            return outNode;
        }

        public int getCost() {
            return cost;
        }
    }

    public static int WEEK_DAYS = 7;

    private Node[][] nodes;

    @SuppressWarnings("unchecked")
    public Solver(char[][] matrix) {
        nodes = new Node[matrix.length][WEEK_DAYS + 1];

        for (int person = 0; person < matrix.length; person++) {
            nodes[person][0] = new Node(person, 0, '-');
            nodes[person][1] = new Node(person, 1, matrix[person][1]);
            nodes[person][0].addEdge(nodes[person][1], 0);

            for (int weekDay = 1; weekDay < WEEK_DAYS; weekDay++) {
                nodes[person][weekDay + 1] = new Node(person, weekDay + 1, matrix[person][weekDay + 1]);
                nodes[person][weekDay].addEdge(nodes[person][weekDay + 1], 1);
            }

            nodes[person][WEEK_DAYS].addEdge(nodes[person][1], 1);
        }

        for (int weekDay = 1; weekDay <= WEEK_DAYS; weekDay++) {
            for (int person1 = 0; person1 < nodes.length; person1++) {
                for (int person2 = person1 + 1; person2 < nodes.length; person2++) {

                }
            }
        }
    }

    public int solve(int start, int end) {

    }
}