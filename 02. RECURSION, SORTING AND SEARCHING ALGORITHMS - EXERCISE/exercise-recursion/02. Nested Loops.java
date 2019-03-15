import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NestedLoopsToRecursion {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int loops = Integer.parseInt(bufferedReader.readLine());

        int[] elements = new int[loops];
        nestedLoops(elements, 0);
    }

    private static void nestedLoops(int[] elements, int index) {
        if (index == elements.length) {
            printArr(elements);
            return;
        }

        for (int i = 0; i < elements.length; i++) {
            elements[index] = i + 1;
            nestedLoops(elements, index + 1);
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
