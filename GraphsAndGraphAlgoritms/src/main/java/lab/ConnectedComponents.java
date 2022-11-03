package lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConnectedComponents {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int graphSize = Integer.parseInt(bufferedReader.readLine());
		List<Integer>[] graph = new ArrayList[graphSize];
		for (int i = 0; i < graphSize; i++) {
			String line = bufferedReader.readLine();
			if ("".equals(line)) {
				graph[i] = new ArrayList<Integer>();
				continue;
			}
			graph[i] = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
		}
		boolean[] visited = new boolean[graphSize];

		for (int i = 0; i < visited.length; i++) {
			if (visited[i]) {
				continue;
			}
			List<Integer> connectedComponents = new ArrayList<Integer>();
			traverseGraph(graph, visited, i, connectedComponents);
			System.out.println("Connected component: "
					+ connectedComponents.stream().map(String::valueOf).collect(Collectors.joining(" ")));
		}
	}

	private static void traverseGraph(List<Integer>[] graph, boolean[] visited, int vertex,
			List<Integer> connectedComponents) {
		if (visited[vertex] || vertex >= graph.length) {
			return;
		}
		visited[vertex] = true;
		List<Integer> childElements = graph[vertex];
		for (int i = 0; i < childElements.size(); i++) {
			int childElementIndex = childElements.get(i);
			traverseGraph(graph, visited, childElementIndex, connectedComponents);
		}
		connectedComponents.add(vertex);
	}
}
