import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ModifiedKruskalAlgorithm {

    static class Edge {
        private int first;
        private int second;
        private int weight;

        @Override
        public String toString() {
            return first + " <-> " + second + " = " + weight;
        }
    }

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> graph = new HashMap<>();
    private static List<Edge> edges = new ArrayList<>();
    private static List<Edge> minimalSpanningTree = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int nodes = Integer.parseInt(bufferedReader.readLine().split(" ")[1]);
        int edgesCount = Integer.parseInt(bufferedReader.readLine().split(" ")[1]);
        for (int i = 0; i < edgesCount; i++) {
            Edge edge = new Edge();
            String[] edgeInfo = bufferedReader.readLine().split(" ");
            edge.first = Integer.parseInt(edgeInfo[0]);
            edge.second = Integer.parseInt(edgeInfo[1]);
            edge.weight = Integer.parseInt(edgeInfo[2]);
            if (!graph.containsKey(edge.first)) {
                graph.put(edge.first, new ArrayList<>());
            }
            if (!graph.containsKey(edge.second)) {
                graph.put(edge.second, new ArrayList<>());
            }
            graph.get(edge.first).add(edge);
            graph.get(edge.second).add(edge);
            edges.add(edge);
        }
        kruskal();
        System.out.println("Minimum spanning forest weight: " + minimalSpanningTree.stream().mapToInt(e -> e.weight).sum());
//        for (Node edge : minimalSpanningTree) {
//            System.out.println(String.format("(%d %d) -> %d", edge.first, edge.second, edge.weight));
//        }
    }

    private static void kruskal() {
        edges.sort(Comparator.comparingInt(edge -> edge.weight));
        int[] parents = new int[graph.size()];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        for (Edge edge : edges) {
            int rootFirstNode = findRoot(edge.first, parents);
            int rootSecondNode = findRoot(edge.second, parents);
            if (rootFirstNode != rootSecondNode) {
                minimalSpanningTree.add(edge);
                parents[rootSecondNode] = rootFirstNode;
            }
        }
    }

    private static int findRoot(int node, int[] parents) {
        int root = node;
        while (parents[root] != root) {
            root = parents[root];
        }
        return root;
    }
}
