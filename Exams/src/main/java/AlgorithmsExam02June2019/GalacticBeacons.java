package AlgorithmsExam02June2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GalacticBeacons {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private static int countOfDirectionChanged = 0;
    private static char[][] matrix;
    private static boolean[][] used;
    private static int startRow;
    private static int startCol;

    public static void main(String[] args) throws IOException {
        int totalsRows = Integer.parseInt(bufferedReader.readLine());
        parseMatrix(totalsRows);
        used = new boolean[matrix.length][matrix[0].length];
        setStartEnd();
        traverseMatrix(startRow, startCol);
        System.out.println(countOfDirectionChanged);
    }

    private static void parseMatrix(int totalRows) throws IOException {
        matrix = new char[totalRows][totalRows];
        for (int i = 0; i < totalRows; i++) {
            matrix[i] = bufferedReader.readLine().toCharArray();
        }
    }

    private static void traverseMatrix(int row, int col) {
        if (isOutOfMatrix(row, col)) {
            return;
        }
        if (matrix[row][col] == '5') {
            return;
        }

        if (canGo(row, col) && !used[row][col]) {
            if (mustReturn(row, col)) {
                countOfDirectionChanged++;
            }
            used[row][col] = true;
            traverseMatrix(row + 1, col); // down
            traverseMatrix(row, col + 1); // right
            traverseMatrix(row - 1, col); // up
            traverseMatrix(row, col - 1); // left
            used[row][col] = false;
        }

    }

    private static boolean mustReturn(int row, int col) {
        boolean left = true;
        boolean right = true;
        boolean down = true;
        boolean up = true;
        if (!isOutOfMatrix(row + 1, col)) {
            down = (matrix[row + 1][col] == '1' || used[row + 1][col]);
        }
        if (!isOutOfMatrix(row, col + 1)) {
            right = (matrix[row][col + 1] == '1' || used[row][col + 1]);
        }
        if (!isOutOfMatrix(row - 1, col)) {
            up = (matrix[row - 1][col] == '1' || used[row - 1][col]);
        }
        if (!isOutOfMatrix(row, col - 1)) {
            left = (matrix[row][col - 1] == '1' || used[row][col - 1]);
        }
        return right && left && down && up;
    }

    private static boolean canGo(int row, int col) {
        return matrix[row][col] == '0' || matrix[row][col] == '3';
    }

    private static boolean isOutOfMatrix(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }

    private static void setStartEnd() {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == '3') {
                    startRow = row;
                    startCol = col;
                }
            }
        }
    }
}
