package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BordersControl {

    private static int[] memory;
    private static int[] singleShipTime;
    private static int[] pairShipsTime;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        singleShipTime = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        pairShipsTime = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        memory = new int[singleShipTime.length + 1];

        solve(singleShipTime.length);

        ArrayList<String> output = new ArrayList<>();

        int n = singleShipTime.length;
        while (n > 0) {
            if (memory[n - 1] + singleShipTime[n - 1] == memory[n]) {
                output.add(String.format("Single %d%n", n--));
            } else {
                output.add(String.format("Pair of %d and %d%n", n - 1, n));
                n -= 2;
            }
        }
        output.add(String.format("%d%n", memory[singleShipTime.length]));
        output.add("Optimal Time: ");

        for (int i = output.size() - 1; i >= 0; i--) {
            System.out.print(output.get(i));
        }
    }

    private static void solve(int n) {
        memory[0] = 0;
        memory[1] = singleShipTime[0];
        for (int i = 2; i <= n; i++) {
            memory[i] = Math.min(memory[i - 1] + singleShipTime[i - 1], memory[i - 2] + pairShipsTime[i - 2]);
        }
    }

}
