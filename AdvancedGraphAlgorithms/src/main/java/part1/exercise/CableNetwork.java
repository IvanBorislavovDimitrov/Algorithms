package part1.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CableNetwork {

    static class Edge {
        private int first;
        private int second;
        private int cost;
    }

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private static Map<Integer, List<Edge>> graph = new LinkedHashMap<>();
    private static Set<Integer> spanningTree = new HashSet<>();
    private static int budget;
    private static int used;

    public static void main(String[] args) throws IOException {
        budget = Integer.parseInt(bufferedReader.readLine().split(" ")[1]);
        int nodes = Integer.parseInt(bufferedReader.readLine().split(" ")[1]);
        int edges = Integer.parseInt(bufferedReader.readLine().split(" ")[1]);

        for (int i = 0; i < edges; i++) {
            Edge edge = new Edge();
            String[] edgeParts = bufferedReader.readLine().split(" ");
            edge.first = Integer.parseInt(edgeParts[0]);
            edge.second = Integer.parseInt(edgeParts[1]);
            edge.cost = Integer.parseInt(edgeParts[2]);
            if (!graph.containsKey(edge.first)) {
                graph.put(edge.first, new ArrayList<>());
            }
            if (!graph.containsKey(edge.second)) {
                graph.put(edge.second, new ArrayList<>());
            }
            graph.get(edge.first).add(edge);
            graph.get(edge.second).add(edge);
            if (edgeParts.length > 3) {
                spanningTree.add(edge.first);
                spanningTree.add(edge.second);
            }
        }
        prim();
        System.out.println("Budget used: " + used);
    }

    private static void prim() {
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.cost));
        graph.forEach((key, value) -> queue.addAll(value));
        while (!queue.isEmpty()) {
            Edge min = queue.poll();
            int nonTreeNode = -1;
            if (spanningTree.contains(min.first) && !spanningTree.contains(min.second)) {
                nonTreeNode = min.second;
            }
            if (!spanningTree.contains(min.first) && spanningTree.contains(min.second)) {
                nonTreeNode = min.first;
            }
            if (nonTreeNode == -1) {
                continue;
            }
            if (budget >= min.cost) {
                budget -= min.cost;
                used += min.cost;
            } else {
                break;
            }
            spanningTree.add(nonTreeNode);
            queue.addAll(graph.get(nonTreeNode));
        }
    }
}
