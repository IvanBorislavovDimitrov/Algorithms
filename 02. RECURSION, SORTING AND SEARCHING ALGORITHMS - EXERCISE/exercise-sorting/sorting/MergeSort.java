import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] strNumbs = bufferedReader.readLine().split(" ");
        int[] numbers = new int[strNumbs.length];
        for (int i = 0; i < strNumbs.length; i++) {
            numbers[i] = Integer.parseInt(String.valueOf(strNumbs[i]));
        }
        mergeSort(numbers, 0, numbers.length - 1);
        StringBuilder sb = new StringBuilder();
        for (int number : numbers) {
            sb.append(number).append(" ");
        }
        System.out.println(sb);
    }

    public static void mergeSort(int[] elements, int left, int right) {
        if (left >= right) {
            return;
        }

        int middle = (left + right) / 2;

        mergeSort(elements, left, middle);
        mergeSort(elements, middle + 1, right);

        merge(elements, left, middle, right);
    }

    @SuppressWarnings("unchecked")
    private static void merge(int[] elements, int left, int middle, int right) {
        int leftArraySize = middle - left + 1;
        int rightArraySize = right - middle;

        int[] leftArray = new int[leftArraySize];
        int[] rightArray = new int[rightArraySize];

        for (int i = 0; i < leftArray.length; i++) {
            leftArray[i] = elements[left + i];
        }
        for (int i = 0; i < rightArray.length; i++) {
            rightArray[i] = elements[middle + 1 + i];
        }

        int leftArrayIndex = 0;
        int rightArrayIndex = 0;

        int initialIndex = left;
        while (leftArrayIndex < leftArray.length && rightArrayIndex < rightArray.length) {
            if (leftArray[leftArrayIndex] <= rightArray[rightArrayIndex]) {
                elements[initialIndex] = leftArray[leftArrayIndex++];
            } else {
                elements[initialIndex] = rightArray[rightArrayIndex++];
            }
            initialIndex++;
        }

        while (leftArrayIndex < leftArray.length) {
            elements[initialIndex++] = leftArray[leftArrayIndex++];
        }

        while (rightArrayIndex < rightArray.length) {
            elements[initialIndex++] = rightArray[rightArrayIndex++];
        }
    }

}
