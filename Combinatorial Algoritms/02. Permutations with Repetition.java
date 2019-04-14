package combinational_algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PermutationsWithRepetition {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        char[] elements = Arrays.stream(bufferedReader.readLine().split("\\s+"))
                .filter(x -> !x.trim().equals(""))
                .collect(Collectors.joining(""))
                .toCharArray();

        printVariations(elements, 0);
    }

    private static void printVariations(char[] elements,int index) {
        if (index == elements.length) {
            printCharArray(elements);
        } else {
            Set<Character> swapped = new HashSet<>();
            for (int i = index; i < elements.length; i++) {
                if (!swapped.contains(elements[i])) {
                    swap(index, i, elements);
                    printVariations(elements, index + 1);
                    swap(index, i, elements);
                    swapped.add(elements[i]);
                }
            }
        }
    }

    private static void swap(int index, int i, char[] elements) {
        char ch = elements[index];
        elements[index] = elements[i];
        elements[i] = ch;
    }

    private static void printCharArray(char[] arr) {
        for (char c : arr) {
            System.out.printf("%c ", c);
        }
        System.out.println();
    }
}

