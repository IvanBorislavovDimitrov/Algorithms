package exam_20_08_2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LeTourDeSofia {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private static Map<Integer, List<Integer>> graph = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        int a = Integer.parseInt(bufferedReader.readLine());
        int edgesCount = Integer.parseInt(bufferedReader.readLine());
        int destinationNode = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < edgesCount; i++) {
            String[] edgeParts = bufferedReader.readLine().split(" ");
            int startNode = Integer.parseInt(edgeParts[0]);
            int endNode = Integer.parseInt(edgeParts[1]);
            graph.putIfAbsent(startNode, new ArrayList<>());
            graph.putIfAbsent(endNode, new ArrayList<>());
            graph.get(startNode).add(endNode);
        }


        System.out.println(bestDistance(destinationNode));
    }

    private static int bestDistance(int startNode) {
        List<Integer> visited = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startNode);
        queue.offer(0);
        int iterations = 0;
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            visited.add(currentNode);
            int depth = queue.poll();
            if (iterations != 0 && graph.get(currentNode).contains(startNode)) {
                return depth + 1;
            }
            iterations++;
            List<Integer> neighbours = graph.get(currentNode);
            neighbours.stream().filter(x -> !visited.contains(x)).forEach(neighbour -> {
                queue.offer(neighbour);
                queue.offer(depth + 1);
            });
        }
        return iterations;
    }
}
