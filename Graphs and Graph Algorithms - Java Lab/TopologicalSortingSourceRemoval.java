package com.ivan.algorithms.graphs.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopologicalSortingSourceRemoval {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		Map<String, List<String>> graph = new LinkedHashMap<String, List<String>>();

		String line;
		while (!"end".equalsIgnoreCase(line = bufferedReader.readLine())) {
			String[] keyValue = line.split(" ->\\s?");
			String key = keyValue[0];
			if (keyValue.length == 1) {
				graph.put(key, new ArrayList<String>());
				continue;
			}
			List<String> children = Arrays.stream(keyValue[1].split(", ")).collect(Collectors.toList());
			graph.put(key, children);
		}
		List<String> nodes = new ArrayList<String>();
		while (!graph.isEmpty()) {
			String nodeWithoutDependencies = findNodeWithoutDependencies(graph);
			nodes.add(nodeWithoutDependencies);
			graph.remove(nodeWithoutDependencies);
		}
		System.out.println("Topological sorting: " + nodes.stream().collect(Collectors.joining(", ")));
	}

	private static String findNodeWithoutDependencies(Map<String, List<String>> graph) {
		for (Map.Entry<String, List<String>> node : graph.entrySet()) {
			if (!isNodeContained(graph, node.getKey())) {
				return node.getKey();
			}
		}

		throw new IllegalArgumentException("There is at least one cycle in the graph");
	}

	private static boolean isNodeContained(Map<String, List<String>> graph, String key) {
		for (Map.Entry<String, List<String>> node : graph.entrySet()) {
			if (node.getValue().contains(key)) {
				return true;
			}
		}

		return false;
	}
}
