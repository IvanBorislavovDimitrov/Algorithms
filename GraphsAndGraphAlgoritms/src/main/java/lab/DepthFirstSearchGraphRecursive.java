package lab;

import java.io.IOException;
import java.util.*;

public class DepthFirstSearchGraphRecursive {

    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        graph.addEdges("A", List.of("B", "C"));
        graph.addEdges("B", List.of("D", "E"));
        graph.addEdges("C", List.of("F"));
        graph.addEdges("D", List.of("C", "F"));
        graph.addEdges("E", List.of("D"));
        graph.addEdges("F", List.of());
        graph.dfs();
    }

    private static class Graph {

        private final Map<String, List<String>> adjacentNodes = new LinkedHashMap<>();

        public void addNode(String node) {
            adjacentNodes.putIfAbsent(node, new ArrayList<>());
        }

        public void addEdges(String node, List<String> nodes) {
            adjacentNodes.put(node, nodes);
        }

        public void dfs() {
            Set<String> visited = new HashSet<>();
            Set<String> cycleNodes = new HashSet<>();
            for (Map.Entry<String, List<String>> adjacentNode : adjacentNodes.entrySet()) {
                if (!visited.contains(adjacentNode.getKey())) {
                    System.out.print("Connected components: ");
                    traverseDfs(adjacentNode.getKey(), adjacentNodes, cycleNodes, visited);
                    System.out.println();
                }
            }
        }

        private void traverseDfs(String node, Map<String, List<String>> adjacentNodes, Set<String> cycleNodes, Set<String> visited) {
            if (cycleNodes.contains(node)) {
                throw new IllegalStateException("Cycle has been detected!");
            }
            if (!visited.contains(node)) {
                visited.add(node);
                cycleNodes.add(node);
                for (String childNode : adjacentNodes.get(node)) {
                    traverseDfs(childNode, adjacentNodes, cycleNodes, visited);
                }
                cycleNodes.remove(node);
                System.out.print(node + " ");
            }
        }
    }

}
