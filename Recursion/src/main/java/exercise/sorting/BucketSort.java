package exercise.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] strNumbs = bufferedReader.readLine().split(" ");
        int[] numbers = new int[strNumbs.length];
        for (int i = 0; i < strNumbs.length; i++) {
            numbers[i] = Integer.parseInt(String.valueOf(strNumbs[i]));
        }
        bucketSort(numbers);
        StringBuilder sb = new StringBuilder();
        for (int number : numbers) {
            sb.append(number).append(" ");
        }
        System.out.println(sb);
    }

    public static void bucketSort(int[] input) {
        final int[] code = hash(input);

        List[] buckets = new List[code[1]];
        for (int i = 0; i < code[1]; i++) {
            buckets[i] = new ArrayList();
        }

        for (int i : input) {
            buckets[hash(i, code)].add(i);
        }

        for (List bucket : buckets) {
            Collections.sort(bucket);
        }

        int ndx = 0;
        for (int b = 0; b < buckets.length; b++) {
            for (Object v : buckets[b]) {
                input[ndx++] = (int) v;
            }
        }
    }

    private static int hash(int i, int[] code) {
        return (int) ((double) i / code[0] * (code[1] - 1));
    }

    private static int[] hash(int[] input) {
        int m = input[0];
        for (int i = 1; i < input.length; i++) {
            if (m < input[i]) {
                m = input[i];
            }
        }
        return new int[] { m, (int) Math.sqrt(input.length) };
    }
}
