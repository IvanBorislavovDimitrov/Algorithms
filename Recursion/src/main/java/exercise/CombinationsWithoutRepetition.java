package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CombinationsWithoutRepetition {

    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int k = Integer.parseInt(bufferedReader.readLine());
        int[] arr = new int[k];
        printCombinations(arr, 0, n);
    }

    private static void printCombinations(int[] arr, int index, int n) {
        if (index == arr.length) {
            if (areDifferent(arr) && isValid(arr)) {
                printArr(arr, 0);
                System.out.println();
            }
            return;
        }

        for (int i = index; i < n; i++) {
            arr[index] = i + 1;
            printCombinations(arr, index + 1, n);

        }
    }

    private static void printArr(int[] arr, int start) {
        if (start == arr.length) {
            return;
        }

        System.out.print(arr[start] + " ");

        printArr(arr, start + 1);
    }

    private static boolean areDifferent(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                return false;
            }
        }

        return true;
    }

    private static boolean isValid(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                return false;
            }
        }

        return true;
    }
}