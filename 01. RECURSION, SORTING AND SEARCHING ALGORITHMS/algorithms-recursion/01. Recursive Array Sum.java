import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(bufferedReader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println(sumArray(arr, 0));
    }

    private static int sumArray(int[] arr, int index) {
        if (index == arr.length) {
            return 0;
        }

        return arr[index] + sumArray(arr, index + 1);
    }
}
