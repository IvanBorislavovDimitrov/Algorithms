package lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RodCutting {

	private static int[] bestPrice;
	private static int[] price;
	private static int[] bestCombo;
	private static List<Integer> bestss = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		price = Arrays.stream(bufferedReader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
		int length = Integer.parseInt(bufferedReader.readLine());
		bestCombo = new int[length + 1];
		bestPrice = new int[length + 1];
		int cutRod = cutRod(0);
		System.out.println(cutRod);
		System.out.println(bestss);
	}

	private static int cutRod(int n) {
		if (bestPrice[n] >= 0)
			return bestPrice[n];
		if (n == 0)
			return 0;
		int currentBest = bestPrice[n];
		for (int i = 1; i <= n; i++) {
			currentBest = Math.max(currentBest, price[i] + cutRod(n - i));
			if (currentBest > bestPrice[n]) {
				bestPrice[n] = currentBest;
				bestCombo[n] = i;
				bestss.add(i);
			}
		}
		return bestPrice[n];
	}

}
