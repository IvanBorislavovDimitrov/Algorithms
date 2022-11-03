package lab;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CyclesInGraph {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Map<String, List<String>> graph = readGraph();
        List<String> visited = new ArrayList<>();
        String startNode = getGraphFirstElement(graph);
        try {
            isGraphCyclic(graph, visited, startNode, new ArrayList<>());
            System.out.println("Acyclic: Yes");
        } catch (IllegalArgumentException e) {
            System.out.println("Acyclic: No");
        }
    }

    private static Map<String, List<String>> readGraph() {
        Map<String, List<String>> graph = new HashMap<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if ("stop".equals(line)) {
                return graph;
            }
            String[] pair = line.split("–");
            String startNode = pair[0];
            String endNode = pair[1];
            if (!graph.containsKey(startNode)) {
                graph.put(startNode, new ArrayList<>());
            }
            if (!graph.containsKey(endNode)) {
                graph.put(endNode, new ArrayList<>());
            }
            graph.get(startNode).add(endNode);
            graph.get(endNode).add(startNode);
        }
        return graph;
    }

    private static String getGraphFirstElement(Map<String, List<String>> graph) {
        for (Map.Entry<String, List<String>> node : graph.entrySet()) {
            return node.getKey();
        }
        return null;
    }

    private static void isGraphCyclic(Map<String, List<String>> graph, List<String> visited, String startNode,
                                      List<AbstractMap.SimpleEntry<String, String>> cycle) {
        if (!visited.contains(startNode)) {
            visited.add(startNode);
            for (String node : graph.get(startNode)) {
                if (visited.contains(node) && isEdgeFree(cycle, startNode, node)) {
                    throw new IllegalArgumentException();
                }
                addEdge(cycle, startNode, node);
                isGraphCyclic(graph, visited, node, cycle);
                removeEdge(cycle, startNode, node);
            }
        }
    }

    private static void addEdge(List<AbstractMap.SimpleEntry<String, String>> cycle, String start, String end) {
        cycle.add(new AbstractMap.SimpleEntry<>(start, end));
    }

    private static void removeEdge(List<AbstractMap.SimpleEntry<String, String>> cycle, String start, String end) {
        cycle.removeIf(p -> p.getKey().equals(start) && p.getValue().equals(end));
    }

    private static boolean isEdgeFree(List<AbstractMap.SimpleEntry<String, String>> cycle, String start, String end) {
        return !(cycle.stream().anyMatch(p -> p.getKey().equals(start) && p.getValue().equals(end)) ||
                cycle.stream().anyMatch(p -> p.getKey().equals(end) && p.getValue().equals(start)));
    }
}
