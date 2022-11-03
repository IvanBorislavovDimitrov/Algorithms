package lab;

import java.io.IOException;
import java.util.*;

public class DepthFirstSearchGraphIterative {

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
            for (String node : adjacentNodes.keySet()) {
                if (!visited.contains(node)) {
                    System.out.print("Connected components: ");
                    traverseDfs(node, visited);
                    System.out.println();
                }
            }
        }

        private void traverseDfs(String root, Set<String> visited) {
            Stack<String> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                String node = stack.pop();
                if (!visited.contains(node)) {
                    System.out.print(node + " ");
                    visited.add(node);
                }
                List<String> childNodes = adjacentNodes.get(node);
                for (String childNode : childNodes) {
                    if (!visited.contains(childNode)) {
                        stack.push(childNode);
                    }
                }
            }
        }
    }

}
