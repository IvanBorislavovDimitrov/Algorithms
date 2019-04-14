package combinational_algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PermutationsWithoutRepetition {

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
            printVariations(elements, index + 1);
            for (int i = index + 1; i < elements.length; i++) {
                swap(index, i, elements);
                printVariations(elements,index + 1);
                swap(index, i, elements);
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

