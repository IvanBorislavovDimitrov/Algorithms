package com.ivan.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MoveDownRight {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bufferedReader.readLine());
		Integer.parseInt(bufferedReader.readLine());
		int[][] matrix = readMatrix(n, bufferedReader);
		List<List<Integer>> findHighestSum = findHighestSum(matrix);
		findHighestSum.stream()
			.map(List::toString)
			.collect(Collectors.toCollection(ArrayDeque::new))
			.descendingIterator()
			.forEachRemaining(i -> System.out.print(i + " "));
	}

	private static List<List<Integer>> findHighestSum(int[][] matrix) {
		int[][] sums = new int[matrix.length][matrix[0].length];
		sums[0][0] = matrix[0][0];
		for (int i = 1; i < matrix[0].length; i++) {
			sums[0][i] += (sums[0][i - 1] + matrix[0][i]);
		}
		for (int i = 1; i < matrix.length; i++) {
			sums[i][0] += (sums[i - 1][0] + matrix[i][0]);
		}
		for (int row = 1; row < matrix.length; row++) {
			for (int col = 1; col < matrix[row].length; col++) {
				if (sums[row - 1][col] > sums[row][col - 1]) {
					sums[row][col] = matrix[row][col] + sums[row - 1][col];
				} else {
					sums[row][col] = matrix[row][col] + sums[row][col - 1];
				}
			}
		}

		int currentRow = matrix.length - 1;
		int currentCol = matrix[0].length - 1;

		List<List<Integer>> indeces = new ArrayList<>();

		while (currentRow != 0 || currentCol != 0) {
			List<Integer> currentIndeces = new ArrayList<Integer>();
			currentIndeces.add(currentRow);
			currentIndeces.add(currentCol);
			indeces.add(currentIndeces);
			if (currentRow == 0) {
				currentCol -= 1;
				continue;
			}
			if (currentCol == 0) {
				currentRow -= 1;
				continue;
			}
			if (sums[currentRow - 1][currentCol] > sums[currentRow][currentCol - 1]) {
				currentRow -= 1;
			} else {
				currentCol -= 1;
			}
		}

		List<Integer> currentIndeces = new ArrayList<Integer>();
		currentIndeces.add(currentRow);
		currentIndeces.add(currentCol);
		indeces.add(currentIndeces);

		return indeces;
	}

	private static int[][] readMatrix(int n, BufferedReader bufferedReader) throws IOException {
		int[][] matrix = new int[n][];
		for (int i = 0; i < n; i++) {
			matrix[i] = Arrays.stream(bufferedReader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
		}

		return matrix;
	}
}
