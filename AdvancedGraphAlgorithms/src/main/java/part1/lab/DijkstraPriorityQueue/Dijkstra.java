package part1.lab.DijkstraPriorityQueue;

import java.util.*;

public class Dijkstra {

    static class Node {
        private int second;
        private int cost;

        public Node(int second, int cost) {
            this.second = second;
            this.cost = cost;
        }
    }

    private static List<Node>[] graph;

    public static List<Integer> dijkstraAlgorithm(int[][] graphMatrix, int sourceNode, int destinationNode) {
        graph = new ArrayList[graphMatrix.length];
        fillGraph(graphMatrix);
        List<Integer> visited = new ArrayList<>();
        int[] distances = new int[graph.length];
        markValuesAsInfinity(distances);
        distances[sourceNode] = 0;
        int[] previous = new int[graph.length];
        setArrayValuesToMinusOne(previous);
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(n -> distances[n]));
        queue.offer(sourceNode);
        while (!queue.isEmpty()) {
            int minNode = queue.poll();
            if (distances[minNode] == Integer.MAX_VALUE) {
                break;
            }
            visited.add(minNode);
            for (Node edge : graph[minNode]) {
                if (!visited.contains(edge.second)) {
                    queue.offer(edge.second);
                }
                int newDistance = distances[minNode] + edge.cost;
                if (newDistance < distances[edge.second]) {
                    distances[edge.second] = newDistance;
                    PriorityQueue<Integer> tmp = new PriorityQueue<>(queue);
                    queue = new PriorityQueue<>(Comparator.comparingInt(n -> distances[n]));
                    queue.addAll(tmp);
                    previous[edge.second] = minNode;
                }
            }
        }

        return buildShortestPath(previous, sourceNode, destinationNode);
    }

    private static void fillGraph(int[][] graphMatrix) {
        for (int row = 0; row < graphMatrix.length; row++) {
            for (int col = 0; col < graphMatrix.length; col++) {
                if (graph[row] == null) {
                    graph[row] = new ArrayList<>();
                }
                if (graphMatrix[row][col] != 0) {
                    graph[row].add(new Node(col, graphMatrix[row][col]));
                }
            }
        }
    }

    private static void setArrayValuesToMinusOne(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = -1;
        }
    }

    private static void markValuesAsInfinity(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.MAX_VALUE;
        }
    }

    private static List<Integer> buildShortestPath(int[] previous, int sourceNode, int destinationNode) {
        List<Integer> shortestPath = new ArrayList<>();
        int latest = previous[destinationNode];
        shortestPath.add(destinationNode);
        while (latest != sourceNode) {
            if (latest == -1) {
                return null;
            }
            shortestPath.add(latest);
            latest = previous[latest];
        }
        shortestPath.add(latest);
        Collections.reverse(shortestPath);
        return shortestPath;
    }
}
