import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class AreasInMatrix {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int rowsCount = Integer.parseInt(bufferedReader.readLine());
        Character[][] matrix = readMatrix(rowsCount);
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        Pair<Integer, Integer> unvisitedElement = getFirstUnvisitedElements(visited);
        Map<Character, Integer> letters = new TreeMap<>();
        while (unvisitedElement != null) {
            unvisitedElement = getFirstUnvisitedElements(visited);
            if (unvisitedElement == null) {
                System.out.println("Areas: " + letters.values().stream().mapToInt(i -> i).sum());
                letters.forEach((letter, count) -> System.out.println(String.format("Letter '%c' -> %d", letter, count)));
                return;
            }
            char c = matrix[unvisitedElement.getKey()][unvisitedElement.getValue()];
            letters.put(c, letters.get(c) == null ? 1 : letters.get(c) + 1);
            visitConnectedLettersAreas(matrix, visited, unvisitedElement.getKey(), unvisitedElement.getValue(), c);
        }
    }

    private static Character[][] readMatrix(int rows) throws IOException {
        Character[][] matrix = new Character[rows][];
        for (int i = 0; i < rows; i++) {
            String s = bufferedReader.readLine();
            Character[] row = new Character[s.length()];
            for (int i1 = 0; i1 < s.toCharArray().length; i1++) {
                row[i1] = s.charAt(i1);
            }
            matrix[i] = row;
        }
        return matrix;
    }

    private static Pair<Integer, Integer> getFirstUnvisitedElements(boolean[][] visited) {
        for (int i = 0; i < visited.length; i++) {
            for (int i1 = 0; i1 < visited[i].length; i1++) {
                if (!visited[i][i1]) {
                    return new Pair<>(i, i1);
                }
            }
        }
        return null;
    }

    private static void visitConnectedLettersAreas(Character[][] matrix, boolean[][] visited, int startRow, int startCol, char c) {
        if (!isInMatrix(startRow, startCol, matrix) || matrix[startRow][startCol] != c) {
            return;
        }
        if (visited[startRow][startCol]) {
            return;
        }
        visited[startRow][startCol] = true;

        visitConnectedLettersAreas(matrix, visited, startRow + 1, startCol, c); // right
        visitConnectedLettersAreas(matrix, visited, startRow, startCol + 1, c); // down
        visitConnectedLettersAreas(matrix, visited, startRow, startCol - 1, c); // up
        visitConnectedLettersAreas(matrix, visited, startRow - 1, startCol, c); // left
    }

    private static boolean isInMatrix(int row, int col, Character[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }
}
