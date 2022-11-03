package exercise.searching;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BinarySearch {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] stringNumbers = bufferedReader.readLine().split(" ");
        int[] numbers = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            numbers[i] = Integer.parseInt(stringNumbers[i]);
        }
        int number = Integer.parseInt(bufferedReader.readLine());
        System.out.println(binarySearch(numbers, number, 0, numbers.length - 1));
    }

    private static int binarySearch(int[] numbers, int number, int left, int right) {
        int middle = (left + right) / 2;
        int comparedNumber = numbers[middle];
        if (left > right) {
            return -1;
        }

        if (number == comparedNumber) {
            return middle;
        }

        if (number > comparedNumber) {
            return binarySearch(numbers, number, middle + 1, right);
        }

        return binarySearch(numbers, number, 0, middle - 1);
    }
}
