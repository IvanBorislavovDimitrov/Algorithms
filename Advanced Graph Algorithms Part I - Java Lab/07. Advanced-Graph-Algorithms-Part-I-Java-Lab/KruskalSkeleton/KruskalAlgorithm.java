import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgorithm {

	public static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {
		Collections.sort(edges);
		int[] parents = new int[numberOfVertices];
		setupParents(parents);
		List<Edge> spanningTree = new ArrayList<Edge>();
		for (Edge edge : edges) {
			int rootStartNode = findRoot(edge.getStartNode(), parents);
			int rootEndNode = findRoot(edge.getEndNode(), parents);
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

	public static int findRoot(int node, int[] parent) {
		int root = node;
		while (parent[root] != root) {
			root = parent[root];
		}
		return root;
	}
}
