package searching_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinearSearch {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = readArray(bufferedReader);
        int num = Integer.parseInt(bufferedReader.readLine());
        System.out.println(linearSearch(arr, num));
    }

    private static int linearSearch(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return i;
            }
        }

        return -1;
    }

    private static int[] readArray(BufferedReader bufferedReader) throws IOException {
        String[] elements = bufferedReader.readLine().split(" ");
        int[] numbers = new int[elements.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(elements[i]);
        }

        return numbers;
    }
}

