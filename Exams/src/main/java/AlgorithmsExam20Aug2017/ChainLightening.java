package AlgorithmsExam20Aug2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ChainLightening {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> graph = new LinkedHashMap<>();
    private static List<Edge> edges = new ArrayList<>();
    private static List<int[]> lightnings = new ArrayList<>();
    private static int[] hits;

    public static void main(String[] args) throws IOException {
        int nodesCount = Integer.parseInt(bufferedReader.readLine());
        int edgesCount = Integer.parseInt(bufferedReader.readLine());
        int lightningsCount = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < edgesCount; i++) {
            String[] edgeParts = bufferedReader.readLine().split(" ");
            int startNode = Integer.parseInt(edgeParts[0]);
            int endNode = Integer.parseInt(edgeParts[1]);
            int weight = Integer.parseInt(edgeParts[2]);
            Edge node = new Edge(startNode, endNode, weight);
            graph.putIfAbsent(startNode, new ArrayList<>());
            graph.putIfAbsent(endNode, new ArrayList<>());
            graph.get(startNode).add(node);
            edges.add(node);
        }
        for (int i = 0; i < lightningsCount; i++) {
            String[] lighteningPartsStrings = bufferedReader.readLine().split(" ");
            int[] lightningsParts = new int[lighteningPartsStrings.length];
            lightningsParts[0] = Integer.parseInt(lighteningPartsStrings[0]);
            lightningsParts[1] = Integer.parseInt(lighteningPartsStrings[1]);
            lightnings.add(lightningsParts);
        }

        List<Edge> mst = kruskal(nodesCount);
        rebuildGraph(mst);

        hits = new int[graph.size() * 100];
        for (int[] lightning : lightnings) {
            int start = lightning[0];
            if (start >= graph.size()) {
                continue;
            }
            int power = lightning[1];
            hit(start, power, new boolean[graph.size() * 100]);
        }

        System.out.println(Arrays.stream(hits).max().orElse(0));
    }

    private static void hit(int node, int power, boolean[] visited) {
        if (visited[node]) {
            return;
        }
        visited[node] = true;
        hits[node] += power;
        if (graph.get(node) == null) {
            graph.put(node, new ArrayList<>());
        }
        for (Edge edge : graph.get(node)) {
            hit(edge.startNode, power / 2, visited);
            hit(edge.endNode, power / 2, visited);
        }
    }

    private static void rebuildGraph(List<Edge> mst) {
        graph = new LinkedHashMap<>();
        for (Edge edge : mst) {
            if (!graph.containsKey(edge.startNode)) {
                graph.put(edge.startNode, new ArrayList<>());
            }
            if (!graph.containsKey(edge.endNode)) {
                graph.put(edge.endNode, new ArrayList<>());
            }
            graph.get(edge.startNode).add(edge);
            graph.get(edge.endNode).add(edge);
        }
    }

    private static List<Edge> kruskal(int numberOfVertices) {
        Collections.sort(edges);
        int[] parents = new int[numberOfVertices];
        setupParents(parents);
        List<Edge> spanningTree = new ArrayList<>();
        for (Edge edge : edges) {
            int rootStartNode = findRoot(edge.startNode, parents);
            int rootEndNode = findRoot(edge.endNode, parents);
            if (rootStartNode != rootEndNode) {
                spanningTree.add(edge);
                parents[rootStartNode] = rootEndNode;
            }
        }
        return spanningTree;
    }

    private static void setupParents(int[] parents) {
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    private static int findRoot(int node, int[] parent) {
        int root = node;
        while (parent[root] != root) {
            root = parent[root];
        }
        return root;
    }
}

class Edge implements Comparable<Edge> {
    int startNode;
    int endNode;
    int weight;

    public Edge() {
    }

    public Edge(int startNode, int endNode, int weight) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(weight, o.weight);
    }

    @Override
    public String toString() {
        return startNode + " " + endNode + " " + weight + " ";
    }
}