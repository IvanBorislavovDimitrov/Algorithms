import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(bufferedReader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        bubbleSort(numbers);
        Arrays.stream(numbers).forEach(n -> System.out.printf("%d ", n));
        System.out.println();
    }

    private static void bubbleSort(int[] numbers) {
        boolean isMoved = true;
        while (isMoved) {
            isMoved = false;
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    int temp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = temp;
                    isMoved = true;
                }
            }
        }
    }


}
