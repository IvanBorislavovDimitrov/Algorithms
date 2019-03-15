package searching_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InversionCount {


    public static void main(String[] args) throws IOException {
        int[] arr = readArray();
        System.out.println(mergeSort(arr, arr.length));
    }

    private static int mergeSort(int[] arr, int array_size) {
        int[] temp = new int[array_size];
        return mergeSort(arr, temp, 0, array_size - 1);
    }

    private static int mergeSort(int[] arr, int[] temp, int left, int right) {
        int mid, inversionCount = 0;
        if (right > left) {
            mid = (right + left) / 2;

            inversionCount = mergeSort(arr, temp, left, mid);
            inversionCount += mergeSort(arr, temp, mid + 1, right);

            inversionCount += merge(arr, temp, left, mid + 1, right);
        }
        return inversionCount;
    }

    private static int merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i, j, k;
        int inversionCount = 0;

        i = left;
        j = mid;
        k = left;
        while ((i <= mid - 1) && (j <= right)) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                inversionCount += mid - i;
            }
        }
        while (i <= mid - 1) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }

        return inversionCount;
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
