import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CombinationsWithRepetition {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        char[] symbols = readArray(bufferedReader);
        int n = Integer.parseInt(bufferedReader.readLine());
        printCombinations(symbols, 0, new char[n], 0);
    }

    private static void printCombinations(char[] symbols, int index, char[] combinations, int start) {
        if (index == combinations.length) {
            printCharArray(combinations);
            return;
        }

        for (int i = start; i < symbols.length; i++) {
            combinations[index] = symbols[i];
            printCombinations(symbols, index + 1, combinations, i);
        }
    }

    private static void printCharArray(char[] arr) {
        for (char c : arr) {
            System.out.printf("%c ", c);
        }
        System.out.println();
    }

    private static char[] readArray(BufferedReader bufferedReader) throws IOException {
        String[] strs = bufferedReader.readLine().split(" ");
        char[] chars = new char[strs.length];
        int index = 0;
        for (String str : strs) {
            chars[index++] = str.charAt(0);
        }

        return chars;
    }
}

