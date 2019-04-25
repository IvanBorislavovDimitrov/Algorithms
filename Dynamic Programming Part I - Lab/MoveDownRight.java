package com.ivan.algorythms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MoveDownRight {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bufferedReader.readLine());
		Integer.parseInt(bufferedReader.readLine());
		int[][] matrix = readMatrix(n, bufferedReader);
		
	}

	private static int[][] readMatrix(int n, BufferedReader bufferedReader) throws IOException {
		int[][] matrix = new int[n][];
		for (int i = 0; i < n; i++) {
			matrix[i] = Arrays.stream(bufferedReader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
		}
		
		return matrix;
	}
}
