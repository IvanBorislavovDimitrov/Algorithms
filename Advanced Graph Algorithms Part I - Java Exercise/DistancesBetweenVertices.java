import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class DistancesBetweenVertices {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int numberOfVertices = Integer.parseInt(bufferedReader.readLine());
        int pairsCount = Integer.parseInt(bufferedReader.readLine());
        Map<Integer, List<Integer>> graph = readGraph(numberOfVertices);
        List<Pair<Integer, Integer>> pairs = pairsToFind(pairsCount);
        printShortestDistances(graph, pairs);
    }

    private static Map<Integer, List<Integer>> readGraph(int numberOfVertices) throws IOException {
        Map<Integer, List<Integer>> graph = new LinkedHashMap<>();
        for (int i = 0; i < numberOfVertices; i++) {
            String[] pair = bufferedReader.readLine().split(":");
            int startNode = Integer.parseInt(pair[0]);
            if (!graph.containsKey(startNode)) {
                graph.put(startNode, new ArrayList<>());
            }
            if (pair.length == 2) {
                List<Integer> neighbours = Arrays.stream(pair[1].split(" ")).map(Integer::parseInt).collect(Collectors.toList());
                graph.get(startNode).addAll(neighbours);
            }
        }
        return graph;
    }

    private static List<Pair<Integer, Integer>> pairsToFind(int pairsCount) throws IOException {
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < pairsCount; i++) {
            String[] pair = bufferedReader.readLine().split("-");
            int startNode = Integer.parseInt(pair[0]);
            int endNode = Integer.parseInt(pair[1]);
            pairs.add(new Pair<>(startNode, endNode));
        }
        return pairs;
    }

    private static void printShortestDistances(Map<Integer, List<Integer>> graph, List<Pair<Integer, Integer>> pairs) {
        pairs.forEach((pair) -> {
            int bestDistance = bestDistance(graph, pair.getKey(), pair.getValue(), new ArrayList<>());
            System.out.println(String.format("{%d, %d} -> %d", pair.getKey(), pair.getValue(), bestDistance));
        });
    }

    private static int bestDistance(Map<Integer, List<Integer>> graph, int startNode, int endNode, List<Integer> visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startNode);
        queue.offer(0);
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            visited.add(currentNode);
            int depth = queue.poll();
            if (currentNode == endNode) {
                return depth;
            }
            List<Integer> neighbours = graph.get(currentNode);
            neighbours.stream().filter(x -> !visited.contains(x)).forEach(neighbour -> {
                queue.offer(neighbour);
                queue.offer(depth + 1);
            });
        }
        return -1;
    }
}
