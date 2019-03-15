import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(bufferedReader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        insertionSort(numbers);
        Arrays.stream(numbers).forEach(n -> System.out.printf("%d ", n));
        System.out.println();
    }

    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int prevIndex = i - 1;
            while (prevIndex >= 0 && key < arr[prevIndex]) {
                int current = arr[prevIndex + 1];
                arr[prevIndex + 1] = arr[prevIndex];
                arr[prevIndex] = current;

                prevIndex--;
            }
        }
    }

}
