package lab;

public class MergeSort<T extends Comparable<T>> {

    public void sort(Object[] elements, int left, int right) {
        if (left >= right) {
            return;
        }

        int middle = (left + right) / 2;

        sort(elements, left, middle);
        sort(elements, middle + 1, right);

        merge(elements, left, middle, right);
    }

    @SuppressWarnings("unchecked")
    private void merge(Object[] elements, int left, int middle, int right) {
        int leftArraySize = middle - left + 1;
        int rightArraySize = right - middle;

        Object[] leftArray = new Object[leftArraySize];
        Object[] rightArray = new Object[rightArraySize];

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
            if (((T) leftArray[leftArrayIndex]).compareTo(((T) rightArray[rightArrayIndex])) <= 0) {
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
