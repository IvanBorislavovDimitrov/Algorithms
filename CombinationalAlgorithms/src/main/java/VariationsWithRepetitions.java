import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VariationsWithRepetitions {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        char[] symbols = readArray(bufferedReader);
        int n = Integer.parseInt(bufferedReader.readLine());
        printVariations(symbols, 0, new char[n]);
    }

    private static void printVariations(char[] symbols, int index, char[] variations) {
        if (index == variations.length) {
            printCharArray(variations);
            return;
        }

        for (int i = 0; i < symbols.length; i++) {
            variations[index] = symbols[i];
            printVariations(symbols, index + 1, variations);
        }
    }

    private static void printCharArray(char[] arr) {
        StringBuilder sb = new StringBuilder();
        for (char c : arr) {
            sb.append(c).append(" ");
        }
        System.out.println(sb);
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

