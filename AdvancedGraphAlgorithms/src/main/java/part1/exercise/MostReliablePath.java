package part1.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class MostReliablePath {

    static class Node {
        private int index;
        private int weight;

        @Override
        public String toString() {
            return index + "=" + weight;
        }
    }

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        int nodes = Integer.parseInt(bufferedReader.readLine().split(" ")[1]);
        String[] pathParts = bufferedReader.readLine().split(" ");
        int startNode = Integer.parseInt(pathParts[1]);
        int endNode = Integer.parseInt(pathParts[3]);
        int edges = Integer.parseInt(bufferedReader.readLine().split(" ")[1]);
        graph = new ArrayList[edges];
        fillGraph(edges);
        double[] percentages = new double[nodes];
        double[] distances = new double[nodes];
        markDistancesAsInfinity(distances);
        distances[startNode] = 0;
        int[] previous = new int[nodes];
        markPreviousMinusOne(previous);

        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingDouble(i -> distances[(int) i]).reversed());
        queue.offer(startNode);
        while (!queue.isEmpty()) {
            int parentNodeIndex = queue.poll();
            if (distances[parentNodeIndex] == Integer.MAX_VALUE) {
                break;
            }

            visited.add(parentNodeIndex);
            for (Node node : graph[parentNodeIndex]) {
                if (!visited.contains(node.index)) {
                    queue.offer(node.index);
                }
                if (visited.contains(node.index)) {
                    continue;
                }

                double newDistance = (distances[parentNodeIndex] + node.weight) / 100;
                if (distances[node.index] < newDistance) {
                    distances[node.index] = newDistance;
                    previous[node.index] = parentNodeIndex;
                    percentages[node.index] = node.weight;
                    List<Integer> tmp = new ArrayList<>(queue);
                    queue = new PriorityQueue<>(Comparator.comparingDouble(i -> distances[(int) i]).reversed());
                    queue.addAll(tmp);
                }
            }
        }

        List<Integer> path = buildPath(previous, startNode, endNode);
        System.out.println(String.format("Most reliable path reliability: %.2f%%", calculatePercentage(percentages, previous, startNode, endNode) * 100));
        System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining(" -> ")));

    }

    private static void fillGraph(int edges) throws IOException {
        for (int i = 0; i < edges; i++) {
            String[] edgeParts = bufferedReader.readLine().split(" ");
            int first = Integer.parseInt(edgeParts[0]);
            int second = Integer.parseInt(edgeParts[1]);
            int weight = Integer.parseInt(edgeParts[2]);
            Node node = new Node();
            node.index = second;
            node.weight = weight;
            if (graph[first] == null) {
                graph[first] = new ArrayList<>();
            }
            graph[first].add(node);
            Node node1 = new Node();
            node1.index = first;
            node1.weight = weight;
            if (graph[second] == null) {
                graph[second] = new ArrayList<>();
            }
            graph[second].add(node1);
        }
    }

    private static void markDistancesAsInfinity(double[] distances) {
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MIN_VALUE;
        }
    }

    private static void markPreviousMinusOne(int[] previous) {
        for (int i = 0; i < previous.length; i++) {
            previous[i] = -1;
        }
    }

    private static List<Integer> buildPath(int[] previous, int startNode, int endNode) {
        List<Integer> path = new ArrayList<>();
        path.add(endNode);
        while (previous[endNode] != -1) {
            endNode = previous[endNode];
            path.add(endNode);
        }
        Collections.reverse(path);
        return path;
    }

    private static double calculatePercentage(double[] percentages, int[] previous, int startNode, int endNode) {
        double value = 1;
        while (previous[endNode] != -1) {
            value *= (percentages[endNode] / 100);
            endNode = previous[endNode];
        }
        return value;
    }
}
