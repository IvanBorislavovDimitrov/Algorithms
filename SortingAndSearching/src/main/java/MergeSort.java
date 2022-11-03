import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MergeSort {

    public static void main(String[] args) throws IOException {
        int[] numbers = readArray();
        mergeSort(numbers, 0, numbers.length - 1);
        printArr(numbers);
    }

    private static void mergeSort(int[] numbers, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int middleIndex = (startIndex + endIndex) / 2;

        mergeSort(numbers, startIndex, middleIndex);
        mergeSort(numbers, middleIndex + 1, endIndex);

        merge(numbers, startIndex, middleIndex, endIndex);
    }

    private static void merge(int[] numbers, int startIndex, int middleIndex, int endIndex) {
        int leftArraySize = middleIndex - startIndex + 1;
        int rightArraySize = endIndex - middleIndex;

        int[] leftArray = new int[leftArraySize];
        int[] rightArray = new int[rightArraySize];

        for (int i = 0; i < leftArray.length; i++) {
            leftArray[i] = numbers[startIndex + i];
        }
        for (int i = 0; i < rightArray.length; i++) {
            rightArray[i] = numbers[middleIndex + 1 + i];
        }

        int leftArrayIndex = 0;
        int rightArrayIndex = 0;
        int start = startIndex;

        while (leftArrayIndex < leftArray.length && rightArrayIndex < rightArray.length) {
            if (leftArray[leftArrayIndex] <= rightArray[rightArrayIndex]) {
                numbers[start] = leftArray[leftArrayIndex];
                leftArrayIndex++;
            } else {
                numbers[start] = rightArray[rightArrayIndex];
                rightArrayIndex++;
            }
            start++;
        }

        while (leftArrayIndex < leftArraySize) {
            numbers[start] = leftArray[leftArrayIndex];
            start++;
            leftArrayIndex++;
        }

        while (rightArrayIndex < rightArraySize) {
            numbers[start] = rightArray[rightArrayIndex];
            start++;
            rightArrayIndex++;
        }
    }

    private static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }

    private static int[] readArray() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] elements = bufferedReader.readLine().split(" ");
        int[] numbers = new int[elements.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(elements[i]);
        }

        return numbers;
    }
}

