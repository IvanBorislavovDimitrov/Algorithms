package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ReverseArray {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(bufferedReader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] reversed = new int[numbers.length];
        reverseArray(numbers, 0, numbers.length - 1, reversed);

        Arrays.stream(reversed).forEach(n -> System.out.printf("%d ", n));
        System.out.println();
    }

    private static void reverseArray(int[] arr, int start, int end, int[] newArr) {
        if (start >= arr.length) {
            return;
        }
        reverseArray(arr, start + 1, end - 1, newArr);
        newArr[start] = arr[end];
    }
}
