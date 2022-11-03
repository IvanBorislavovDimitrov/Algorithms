package lab;

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
		List<List<Integer>> sequences = new ArrayList<List<Integer>>();
		
		for (int i = 0; i < arr.length; i++) {
			sequences.add(new ArrayList<Integer>());
		}
		
		sequences.get(0).add(arr[0]);
		
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if ((arr[i] > arr[j]) && (sequences.get(i).size() < sequences.get(j).size())) {
					sequences.set(i, new ArrayList<Integer>(sequences.get(j)));
				}
			}
			sequences.get(i).add(arr[i]);
		}
		
		List<Integer> max = sequences.get(0);
		for (int i = 1; i < sequences.size(); i++) {
			if (max.size() < sequences.get(i).size()) {
				max = sequences.get(i);
			}
		}
		
		return max;
	}

}
