package AlgorithmsExam02June2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ParticlesSelector {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n1 = Integer.parseInt(bufferedReader.readLine());
        int n2 = Integer.parseInt(bufferedReader.readLine());
        System.out.println(binomialCoeff(n1, n2));
    }

    private static long binomialCoeff(int n, int k) {
        long[][] table = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i)
                    table[i][j] = 1;
                else
                    table[i][j] = table[i - 1][j - 1] + table[i - 1][j];
            }
        }
        return table[n][k];
    }
}
