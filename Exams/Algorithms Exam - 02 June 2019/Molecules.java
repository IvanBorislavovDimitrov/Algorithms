package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Molecules {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<String, List<Node>> graph = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        int numberOfMolecules = Integer.parseInt(bufferedReader.readLine());
        int numberOfEdges = Integer.parseInt(bufferedReader.readLine());
        readGraph(numberOfEdges);
        String[] startEnd = bufferedReader.readLine().split(" ");
        String startNode = startEnd[0];
        String endNode = startEnd[1];
        dijkstra(startNode, endNode);
    }

    private static void readGraph(int numberOfEdges) throws IOException {
        for (int i = 0; i < numberOfEdges; i++) {
            String[] edgeParts = bufferedReader.readLine().split(" ");
            String startNodeName = edgeParts[0];
            String endNodeName = edgeParts[1];
            int weight = Integer.parseInt(edgeParts[2]);
            Node endNode = new Node(endNodeName, weight);
            if (!graph.containsKey(startNodeName)) {
                graph.put(startNodeName, new ArrayList<>());
            }
            if (!graph.containsKey(endNodeName)) {
                graph.put(endNodeName, new ArrayList<>());
            }
            graph.get(startNodeName).add(endNode);
        }
    }

    private static void dijkstra(String startNode, String endNode) {
        Map<String, Long> distances = new LinkedHashMap<>();
        Map<String, String> previous = new LinkedHashMap<>();
        Set<String> visited = new HashSet<>();
        setMaxDistanceForDistances(distances);
        distances.put(startNode, 0L);
        setPrevious(previous);
        previous.put(startNode, "");
        Queue<String> queue = new PriorityQueue<>(Comparator.comparingLong(distances::get));
        queue.add(startNode);
        while (!queue.isEmpty()) {
            String parentNodeName = queue.poll();
            visited.add(parentNodeName);
            if (distances.get(parentNodeName) == Long.MAX_VALUE) {
                break;
            }
            for (Node node : graph.get(parentNodeName)) {
                if (!visited.contains(node.name)) {
                    queue.add(node.name);
                }
                if (visited.contains(node.name)) {
                    continue;
                }
                long newDistance = distances.get(parentNodeName) + node.weight;
                if (newDistance < distances.get(node.name)) {
                    distances.put(node.name, newDistance);
                    previous.put(node.name, parentNodeName);
                    String val = queue.poll();
                    queue.add(val);
                }
            }
        }
        System.out.println(getDistance(endNode, distances));
        StringBuilder sb = new StringBuilder();
        previous.entrySet().stream()
                .filter(d -> d.getValue() == null)
                .map(Map.Entry::getKey)
                .map(Integer::parseInt)
                .sorted()
                .forEach(d -> sb.append(String.format("%s ", d)));
        System.out.println(sb.toString().trim());
    }

    private static void setMaxDistanceForDistances(Map<String, Long> distances) {
        graph.keySet().forEach(n -> distances.put(n, Long.MAX_VALUE));

    }

    private static void setPrevious(Map<String, String> parents) {
        graph.keySet().forEach(n -> parents.put(n, null));
    }

    private static long getDistance(String endNode, Map<String, Long> distances) {
        return distances.get(endNode);
    }
}

class Node {
    String name;
    long weight;

    public Node() {
    }

    public Node(String name, long weight) {
        this.name = name;
        this.weight = weight;
    }
}