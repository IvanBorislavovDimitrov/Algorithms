import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] set = Arrays.stream(bufferedReader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int border = Integer.parseInt(bufferedReader.readLine());
        int[] arr = new int[border];

        generateCombinations(set, arr, 0, 0 );
    }

    private static void generateCombinations(int[] set, int[] arr, int index, int border) {
        if (index == arr.length) {
            printArr(arr);
            return;
        }

        for (int i = border; i < set.length; i++) {
            arr[index] = set[i];
            generateCombinations(set, arr, index + 1, i + 1);
        }
    }

    private static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }
}
