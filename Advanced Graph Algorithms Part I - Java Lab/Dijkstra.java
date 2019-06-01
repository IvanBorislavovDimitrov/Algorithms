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
        int endNode = Integer.parseInt(pathParts[2]);
        int edges = Integer.parseInt(bufferedReader.readLine().split(" ")[1]);
        graph = new ArrayList[nodes];
        fillGraph(edges);

        int[] distances = new int[nodes];
        markDistancesAsInfinity(distances);
        distances[startNode] = 0;
        int[] previous = new int[nodes];
        markPreviousMinusOne(previous);

        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(n -> distances[n]));
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
                int newDistance = distances[parentNodeIndex] + node.weight;
                if (distances[node.index] > newDistance) {
                    distances[node.index] = newDistance;
                    previous[node.index] = parentNodeIndex;
                    List<Integer> tmp = new ArrayList<>(queue);
                    queue = new PriorityQueue<>(Comparator.comparingInt(n -> distances[n]));
                    queue.addAll(tmp);
                }
            }
        }

        List<Integer> path = buildPath(previous, startNode, endNode);
        System.out.println("Path distance: " + distances[endNode]);
        System.out.println("Path: " + path.stream().map(String::valueOf).collect(Collectors.joining("->")));

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

    private static void markDistancesAsInfinity(int[] distances) {
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
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
}
