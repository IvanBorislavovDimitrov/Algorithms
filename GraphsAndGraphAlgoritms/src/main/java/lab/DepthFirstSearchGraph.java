package lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class DepthFirstSearchGraph {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int graphSize = Integer.parseInt(bufferedReader.readLine());
        Graph graph = new Graph();
        for (int i = 0; i < graphSize; i++) {
            List<Integer> childNodes = Arrays.stream(bufferedReader.readLine().split(" +"))
                    .filter(line -> !"".equals(line.trim()))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            graph.addEdges(i, childNodes);
        }
        graph.dfs();
    }

    private static class Graph {

        private final Map<Integer, List<Integer>> adjacentNodes = new LinkedHashMap<>();

        public void addNode(int node) {
            adjacentNodes.putIfAbsent(node, new ArrayList<>());
        }

        public void addEdges(int node, List<Integer> nodes) {
            adjacentNodes.put(node, nodes);
        }

        public void dfs() {
            boolean[] visited = new boolean[adjacentNodes.size()];
            for (int i = 0; i < adjacentNodes.size(); i++) {
                if (!visited[i]) {
                    System.out.print("Connected components: ");
                    traverseDfs(i, adjacentNodes, visited);
                    System.out.println();
                }
            }
        }

        private void traverseDfs(int node, Map<Integer, List<Integer>> adjacentNodes, boolean[] visited) {
            if (!visited[node]) {
                visited[node] = true;
                for (int childNode : adjacentNodes.get(node)) {
                    traverseDfs(childNode, adjacentNodes, visited);
                }
                System.out.print(node + " ");
            }
        }
    }

}
