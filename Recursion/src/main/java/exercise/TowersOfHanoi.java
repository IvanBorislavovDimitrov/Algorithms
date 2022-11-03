package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class TowersOfHanoi {

    private static int stepsTaken = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> source = new Stack<>();
        Stack<Integer> destination = new Stack<>();
        Stack<Integer> spare = new Stack<>();
        int n = Integer.parseInt(input.readLine());
        initSource(source, n);
        printRods(source, destination, spare);
        System.out.println();
        moveDisks(n, source, destination, spare);
        String stop = "";

    }

    private static void moveDisks(int bottomDisk, Stack<Integer> source, Stack<Integer> destination,
                                  Stack<Integer> spare) {
        if (bottomDisk == 1) {
            stepsTaken++;
            destination.push(source.pop());
            System.out.println(String.format("Steps #%d: Moved disk %d", stepsTaken, bottomDisk));
            printRods(source, destination, spare);
            System.out.println();
        } else {
            moveDisks(bottomDisk - 1, source, spare, destination);
            destination.push(source.pop());
            moveDisks(bottomDisk - 1, spare, destination, source);
        }
    }

    private static void printRods(Stack<Integer> source, Stack<Integer> destination,
                                  Stack<Integer> spare) {
        System.out.println("Source: " + source.toString().replaceAll("\\[|\\]", ""));
        System.out.println("Destination: " + destination.toString().replaceAll("\\[|\\]", ""));
        System.out.println("Spare: " + spare.toString().replaceAll("\\[|\\]", ""));
    }

    private static void initSource(Stack<Integer> source, int n) {
        for (int i = 1; i <= n; i++) {
            source.push(i);
        }
    }
}
