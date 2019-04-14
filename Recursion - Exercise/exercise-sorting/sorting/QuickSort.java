import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuickSort {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] strNumbs = bufferedReader.readLine().split(" ");
        int[] numbers = new int[strNumbs.length];
        for (int i = 0; i < strNumbs.length; i++) {
            numbers[i] = Integer.parseInt(String.valueOf(strNumbs[i]));
        }
        quickSort(numbers, 0, numbers.length - 1);
        StringBuilder sb = new StringBuilder();
        for (int number : numbers) {
            sb.append(number).append(" ");
        }
        System.out.println(sb);
    }

    public static void quickSort(int[] elements, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivot = partition(elements, low, high);

        quickSort(elements, low, pivot - 1);
        quickSort(elements, pivot + 1, high);
    }

    private static int partition(int[] elements, int low, int high) {
        int pivot = elements[high];
        int indexOfSmallerElement = low - 1;
        for (int i = low; i < high; i++) {
            if (elements[i] <= pivot) {
                indexOfSmallerElement++;
                int temp = elements[indexOfSmallerElement];
                elements[indexOfSmallerElement] = elements[i];
                elements[i] = temp;
            }
        }

        int temp = elements[indexOfSmallerElement + 1];
        elements[indexOfSmallerElement + 1] = elements[high];
        elements[high] = temp;

        return indexOfSmallerElement + 1;
    }
}
