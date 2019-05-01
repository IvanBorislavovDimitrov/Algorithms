package com.ivan.algorithms.graphs.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TopologicalSortingDFSAlgorithm {

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
		LinkedList<String> sortedNodes = new LinkedList<String>();
		Set<String> visitedNodes = new HashSet<String>();
		Set<String> cycleNodes = new HashSet<String>();
		for (Map.Entry<String, List<String>> node : graph.entrySet()) {
			topologicalSort(graph, node.getKey(), node.getValue(), sortedNodes, visitedNodes, cycleNodes);
		}
		System.out.println(
				"Connected component: " + sortedNodes.stream().map(String::valueOf).collect(Collectors.joining(" ")));
	}

	private static void topologicalSort(Map<String, List<String>> graph, String nodeName, List<String> children,
			LinkedList<String> sortedNodes, Set<String> visitedNodes, Set<String> cycleNodes) {
		if (cycleNodes.contains(nodeName)) {
			throw new IllegalArgumentException("There is at least one cycle in the graph");
		}
		if (visitedNodes.contains(nodeName)) {
			return;
		}
		cycleNodes.add(nodeName);
		visitedNodes.add(nodeName);
		for (String childNode : children) {
			topologicalSort(graph, childNode, graph.get(childNode), sortedNodes, visitedNodes, cycleNodes);
		}
		sortedNodes.addFirst(nodeName);
		cycleNodes.remove(nodeName);
	}
}
