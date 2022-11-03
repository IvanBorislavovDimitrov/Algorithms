package lab;

import java.util.Scanner;

public class FibonacciSequence {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] calculations = new int[n + 1];
        System.out.println(fibonacci(n, calculations));
    }

    private static int fibonacci(int n, int[] calculations) {
        if (n == 1 || n == 2) {
            return 1;
        }

        if (calculations[n] == 0) {
            calculations[n] = fibonacci(n - 1, calculations) + fibonacci(n - 2, calculations);
        }

        return calculations[n];
    }
}
