package com.ivan.algorythms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LongestIncreasingSequence {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int[] sequence = Arrays.stream(bufferedReader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
		System.out.println(findLis(sequence).stream().map(String::valueOf).collect(Collectors.joining(" ")));
	}

	private static List<Integer> findLis(int[] arr) {
		List<List<Integer>> cache = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			cache.add(new ArrayList<>());
		}

		cache.get(0).add(arr[0]);

		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j] && cache.get(i).size() < cache.get(j).size()) {
					cache.set(i, new ArrayList<>(cache.get(j)));
				}
			}
			cache.get(i).add(arr[i]);
		}

		List<Integer> longest = cache.get(0);
		for (int i = 0; i < cache.size(); i++) {
			if (longest.size() < cache.get(i).size()) {
				longest = new ArrayList<>(cache.get(i));
			}
		}

		return longest;
	}

}
