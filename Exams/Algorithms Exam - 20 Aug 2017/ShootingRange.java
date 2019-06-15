package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class ShootingRange {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int requiredSum;
    private static int[] elements;
    private static Set<String> result = new HashSet<>();

    public static void main(String[] args) throws IOException {
        String[] numbersStrings = bufferedReader.readLine().split(" ");

        elements = new int[numbersStrings.length];
        for (int i = 0; i < numbersStrings.length; i++) {
            elements[i] = Integer.parseInt(numbersStrings[i]);
        }
        requiredSum = Integer.parseInt(bufferedReader.readLine());
        gen(0);
        result.forEach(System.out::println);

    }

    public static void gen(int index) {
        if (index >= elements.length) {
            printIfMatches(elements);
        } else {
            Set<Integer> swapped = new HashSet<>();
            for (int i = index; i < elements.length; i++) {
                if (!swapped.contains(elements[i])) {
                    swap(index, i);
                    gen(index + 1);
                    swap(index, i);
                    swapped.add(elements[i]);
                }
            }
        }
    }

    private static void swap(int index, int i) {
        int temp = elements[index];
        elements[index] = elements[i];
        elements[i] = temp;
    }

    private static void printIfMatches(int[] variations) {
        int multiplier = 1;
        int sum = 0;
        for (int i = 0; i < variations.length; i++) {
            int num = variations[i];
            sum += (num * multiplier++);
            if (sum == requiredSum) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j <= i; j++) {
                    sb.append(elements[j]).append(" ");
                }
                result.add(sb.toString().trim());
                return;
            }
        }
    }
}
