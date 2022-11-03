package exercise.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LinearSearch {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(bufferedReader.readLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println(linearSearch(numbers, Integer.parseInt(bufferedReader.readLine())));
    }

    private static int linearSearch(int[] numbers, int number) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == number) {
                return i;
            }
        }

        return -1;
    }
}
