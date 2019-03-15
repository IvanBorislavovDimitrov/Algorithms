package searching_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Words {

    private static Set<String> words = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = bufferedReader.readLine();
        permutation("", line);
        System.out.println(words.size());
    }

    private static void permutation(String prefix, String str) {
        if (str.length() == 0) {
            if (isWordValid(prefix.toCharArray())) {
                words.add(prefix);
            }
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1));
        }
    }

    private static boolean isWordValid(char[] word) {
        for (int i = 0; i < word.length - 1; i++) {
            if (word[i] == word[i + 1]) {
                return false;
            }
        }

        return true;
    }
}
