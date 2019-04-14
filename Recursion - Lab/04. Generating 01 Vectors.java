import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(bufferedReader.readLine());
        int[] numbers = new int[number];

        generate01(numbers, 0);
    }

    private static void generate01(int[] numbers, int index) {
        if (index > numbers.length - 1) {
            printArr(numbers);
            return;
        }

        for (int i = 0; i <= 1; i++) {
            numbers[index] = i;
            generate01(numbers, index + 1);
        }
    }

    private static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i);
        }

        System.out.println(sb);
    }
}
