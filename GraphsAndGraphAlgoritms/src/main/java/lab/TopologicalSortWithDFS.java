package lab;

import java.io.IOException;
import java.util.*;

public class TopologicalSortWithDFS {

    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        graph.addEdges("A", List.of("B", "C"));
        graph.addEdges("B", List.of("D", "E"));
        graph.addEdges("C", List.of("F"));
        graph.addEdges("D", List.of("C", "F"));
        graph.addEdges("E", List.of("D"));
        graph.addEdges("F", List.of());
        Stack<String> tasks = graph.topologicalSort();
        String res = "";
        while (!tasks.isEmpty()) {
            res += tasks.pop() + " ";
        }
        System.out.println(res);
    }

    private static class Graph {

        private final Map<String, List<String>> adjacentNodes = new LinkedHashMap<>();

        public void addNode(String node) {
            adjacentNodes.putIfAbsent(node, new ArrayList<>());
        }

        public void addEdges(String node, List<String> nodes) {
            adjacentNodes.put(node, nodes);
        }

        public Stack<String> topologicalSort() {
            Set<String> visited = new HashSet<>();
            Stack<String> sortedTasks = new Stack<>();
            Set<String> cycleNodes = new HashSet<>();
            for (Map.Entry<String, List<String>> adjacentNode : adjacentNodes.entrySet()) {
                if (!visited.contains(adjacentNode.getKey())) {
                    dfs(adjacentNode.getKey(), visited, cycleNodes, sortedTasks);
                }
            }
            return sortedTasks;
        }

        private void dfs(String node, Set<String> visited, Set<String> cycleNodes, Stack<String> sortedTasks) {
            if (cycleNodes.contains(node)) {
                throw new IllegalStateException("Cycle has been detected!");
            }
            if (!visited.contains(node)) {
                visited.add(node);
                cycleNodes.add(node);
                List<String> childNodes = adjacentNodes.get(node);
                for (String childNode : childNodes) {
                    dfs(childNode, visited, cycleNodes, sortedTasks);
                }
                cycleNodes.remove(node);
                sortedTasks.push(node);
            }
        }

    }

}
