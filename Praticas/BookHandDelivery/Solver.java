import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Solver {
    public static final int WEEK_DAYS = 7;

    private List<Edge>[][] graph;

    @SuppressWarnings("unchecked")
    public Solver(char[][] matrix) {
        graph = new List[matrix.length][WEEK_DAYS];

        for (int person = 0; person < graph.length; person++) {
            Node first = null;
            Node previous = null;

            for (int weekDay = 0; weekDay < graph[0].length; weekDay++) {
                if (matrix[person][weekDay] == '-')
                    continue;

                Node current = new Node(person, weekDay);
                graph[person][weekDay] = new LinkedList<>();

                if (previous == null) {
                    first = current;
                    previous = current;
                    continue;
                }

                int cost = current.getWeekDay() - previous.getWeekDay();
                graph[person][previous.getWeekDay()].add(new Edge(current, cost));

                previous = current;
            }

            if (first != null) {
                int cost = first.getWeekDay() - previous.getWeekDay() + WEEK_DAYS;
                graph[person][previous.getWeekDay()].add(new Edge(first, cost));
            }
        }

        for (int weekDay = 0; weekDay < graph[0].length; weekDay++) {
            for (int person1 = 0; person1 < graph.length; person1++) {
                char type = matrix[person1][weekDay];

                if (type == '-')
                    continue;

                int person2 = person1 + 1;
                while (person2 < graph.length && matrix[person2][weekDay] != type)
                    person2++;

                if (person2 < graph.length) {
                    graph[person1][weekDay].add(new Edge(new Node(person2, weekDay), 0));
                    graph[person2][weekDay].add(new Edge(new Node(person1, weekDay), 0));
                }
            }
        }
    }

    private boolean[][] selected;
    private int[][] length;
    private Node[][] via;
    private PriorityQueue<Edge> connected;

    public int solve(int start, int end) {
        selected = new boolean[graph.length][graph[0].length];
        length = new int[graph.length][graph[0].length];
        via = new Node[graph.length][graph[0].length];
        connected = new PriorityQueue<>(graph.length * graph[0].length);

        for (int person = 0; person < graph.length; person++) {
            for (int weekDay = 0; weekDay < graph[0].length; weekDay++) {
                length[person][weekDay] = Integer.MAX_VALUE;
            }
        }

        for (int weekDay = 0; weekDay < graph[0].length; weekDay++) {
            if (graph[start][weekDay] == null)
                continue;

            length[start][weekDay] = 0;
            via[start][weekDay] = new Node(start, weekDay);
            connected.add(new Edge(via[start][weekDay], 0));
        }

        while (!connected.isEmpty()) {
            Node node = connected.remove().getOut();
            selected[node.getPerson()][node.getWeekDay()] = true;
            exploreNode(node);
        }

        int result = Integer.MAX_VALUE;
        for (int weekDay = 0; weekDay < graph[0].length; weekDay++)
            result = Math.min(length[end][weekDay], result);

        return result;
    }

    private void exploreNode(Node source) {
        for (Edge edge : graph[source.getPerson()][source.getWeekDay()]) {
            Node node = edge.getOut();

            if (selected[node.getPerson()][node.getWeekDay()])
                continue;

            int newLength = length[source.getPerson()][source.getWeekDay()] + edge.getCost();
            if (newLength >= length[node.getPerson()][node.getWeekDay()])
                continue;

            boolean nodeIsInQueue = length[node.getPerson()][node.getWeekDay()] < Integer.MAX_VALUE;
            length[node.getPerson()][node.getWeekDay()] = newLength;
            via[node.getPerson()][node.getWeekDay()] = source;

            if (nodeIsInQueue)
                connected.remove(edge);

            connected.add(new Edge(node, newLength));
        }
    }
}