import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {

    public static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {
        List<Integer> visitedNodes = new ArrayList<>();
        int[] previous = new int[graph.length];
        int[] distances = new int[graph.length];
        makeArrayValuesInfinite(distances);
        setArrayValuesToMinusOne(previous);
        while (true) {
            int smallestUnvisitedNode = findUnvisitedNodeWithSmallestKnownDistance(distances, visitedNodes);
            if (smallestUnvisitedNode == -1) {
                break;
            }
            visitUnvisitedNeighboursForNode(smallestUnvisitedNode, graph, distances, visitedNodes, previous);
            visitedNodes.add(smallestUnvisitedNode);
        }
        return buildShortestPath(previous, sourceNode, destinationNode);
    }

    private static void makeArrayValuesInfinite(int[] array) {
        for (int i = 1; i < array.length; i++) {
            array[i] = Integer.MAX_VALUE;
        }
    }

    private static void setArrayValuesToMinusOne(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = -1;
        }
    }

    private static int findUnvisitedNodeWithSmallestKnownDistance(int[] distances, List<Integer> visitedNodes) {
        int smallestDistance = Integer.MAX_VALUE;
        int smallestNode = -1;
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] < smallestDistance && !visitedNodes.contains(i)) {
                smallestDistance = distances[i];
                smallestNode = i;
            }
        }
        return smallestNode;
    }

    private static void visitUnvisitedNeighboursForNode(int node, int[][] graph, int[] distances, List<Integer> visitedNodes, int[] previous) {
        for (int i = 0; i < graph[node].length; i++) {
            if (graph[node][i] != 0 && !visitedNodes.contains(i)) {
                int currentDistance = graph[node][i] + distances[node];
                if (distances[i] > currentDistance) {
                    distances[i] = currentDistance;
                    previous[i] = node;
                }
            }
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
