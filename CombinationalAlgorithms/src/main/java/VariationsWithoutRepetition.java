import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VariationsWithoutRepetition {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        char[] symbols = readArray(bufferedReader);
        int n = Integer.parseInt(bufferedReader.readLine());
        printVariations(symbols, 0, new char[n], new boolean[symbols.length]);
    }

    private static void printVariations(char[] symbols, int index, char[] variations, boolean[] used) {
        if (index == variations.length) {
            printCharArray(variations);
            return;
        }

        for (int i = 0; i < symbols.length; i++) {
            if (!used[i]) {
                used[i] = true;
                variations[index] = symbols[i];
                printVariations(symbols, index + 1, variations, used);
                used[i] = false;
            }
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

