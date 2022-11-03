package lab.tasks;

import java.io.IOException;
import java.util.*;

public class SourceRemovalTopologicalSorting {

    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        graph.addEdges("5", List.of("11"));
        graph.addEdges("7", List.of("11", "8"));
        graph.addEdges("8", List.of("9"));
        graph.addEdges("11", List.of("9", "10", "2"));
        graph.addEdges("9", List.of());
        graph.addEdges("3", List.of("8", "10"));
        graph.addEdges("2", List.of());
        graph.addEdges("10", List.of());
        List<String> tasks = graph.topologicalSort();
        System.out.println(String.join(", ", tasks));
    }

    private static class Graph {

        private final Map<String, List<String>> adjacentNodes = new LinkedHashMap<>();

        public void addNode(String node) {
            adjacentNodes.putIfAbsent(node, new ArrayList<>());
        }

        public void addEdges(String node, List<String> nodes) {
            adjacentNodes.put(node, nodes);
        }

        public List<String> topologicalSort() {
            List<String> sortedTasks = new ArrayList<>();
            Map<String, Integer> predecessors = computePredecessors();
            while (!predecessors.isEmpty()) {
                String taskToRemove = pollNextTask(predecessors);
                sortedTasks.add(taskToRemove);
                List<String> childNodes = adjacentNodes.get(taskToRemove);
                for (String childNode : childNodes) {
                    int currentPredecessorsCount = predecessors.get(childNode);
                    predecessors.put(childNode, currentPredecessorsCount - 1);
                }
                predecessors.remove(taskToRemove);
            }
            return sortedTasks;
        }

        private Map<String, Integer> computePredecessors() {
            Map<String, Integer> predecessorsCount = new HashMap<>();
            for (Map.Entry<String, List<String>> node : adjacentNodes.entrySet()) {
                predecessorsCount.putIfAbsent(node.getKey(), 0);
                for (String childNode : node.getValue()) {
                    predecessorsCount.putIfAbsent(childNode, 0);
                    int currentPredecessorCount = predecessorsCount.get(childNode);
                    predecessorsCount.put(childNode, currentPredecessorCount + 1);
                }
            }
            return predecessorsCount;
        }

        private String pollNextTask(Map<String, Integer> predecessors) {
            for (Map.Entry<String, Integer> predecessor : predecessors.entrySet()) {
                if (predecessor.getValue() == 0) {
                    return predecessor.getKey();
                }
            }
            throw new IllegalStateException("Graph has cycles!");
        }

    }

}
