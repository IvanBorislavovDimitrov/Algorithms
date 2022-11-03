package lab;

public class QuickSort<T extends Comparable<T>> {

    public void sort(Object[] elements, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivot = partition(elements, low, high);

        sort(elements, low, pivot - 1);
        sort(elements, pivot + 1, high);
    }

    @SuppressWarnings("unchecked")
    private int partition(Object[] elements, int low, int high) {
        Object pivot = elements[high];
        int indexOfSmallerElement = low - 1;
        for (int i = low; i < high; i++) {
            if (((T) elements[i]).compareTo((T) pivot) <= 0) {
                indexOfSmallerElement++;
                Object temp = elements[indexOfSmallerElement];
                elements[indexOfSmallerElement] = elements[i];
                elements[i] = temp;
            }
        }

        Object temp = elements[indexOfSmallerElement + 1];
        elements[indexOfSmallerElement + 1] = elements[high];
        elements[high] = temp;

        return indexOfSmallerElement + 1;
    }
}
