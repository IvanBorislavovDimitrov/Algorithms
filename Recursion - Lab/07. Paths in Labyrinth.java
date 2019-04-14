import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PathsInLabirynt {

    private static List<Character> path = new ArrayList<>();
    private static boolean[][] board;

    public static void main(String[] args) throws IOException {
        char[][] matrix = readMatrix();
        board = new boolean[matrix.length][matrix[0].length];
        findPaths(0, 0, matrix, 'S');
    }

    private static void findPaths(int row, int col, char[][] matrix, char direction) {
        if (!isInMatrix(row, col, matrix.length, matrix[0].length)) {
            return;
        }

        path.add(direction);

        if (matrix[row][col] == 'e') {
            System.out.println(path.stream().skip(1).map(Object::toString).collect(Collectors.joining("")));
            path.remove(path.size() - 1);
            return;
        }

        if (isFree(row, col, matrix) && !board[row][col]) {
            board[row][col] = true;
            findPaths(row, col + 1, matrix, 'R');
            findPaths(row + 1, col, matrix, 'D');
            findPaths(row, col - 1, matrix, 'L');
            findPaths(row - 1, col, matrix, 'U');
            board[row][col] = false;
        }

        path.remove(path.size() - 1);
    }

    private static boolean isInMatrix(int row, int col, int rows, int cols) {
        if (row >= rows || row < 0) {
            return false;
        }
        if (col >= cols || col < 0) {
            return false;
        }

        return true;
    }

    private static boolean isFree(int row, int col, char[][] matrix) {
        return matrix[row][col] == '-';
    }

    private static char[][] readMatrix() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int rows = Integer.parseInt(bufferedReader.readLine());
        int cols = Integer.parseInt(bufferedReader.readLine());
        char[][] matrix = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            String line = bufferedReader.readLine();
            for (int j = 0; j < line.length(); j++) {
                matrix[i][j] = line.charAt(j);
            }
        }

        return matrix;
    }
}
