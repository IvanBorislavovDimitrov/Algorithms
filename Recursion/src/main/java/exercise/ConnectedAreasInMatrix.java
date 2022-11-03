package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectedAreasInMatrix {

    private static List<Area> areas = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        char[][] matrix = readMatrix();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (isValid(row, col, matrix)) {
                    Area area = new Area();
                    int size = findSize(row, col, matrix);
                    area.row = row;
                    area.col = col;
                    area.size = size;
                    areas.add(area);
                }
            }
        }

        Collections.sort(areas);

        System.out.println(String.format("Total areas found: %d", areas.size()));
        for (int i = 0; i < areas.size(); i++) {
            System.out.println(String.format("Area #%d at %s", i + 1, areas.get(i)));
        }
    }

    private static int findSize(int row, int col, char[][] matrix) {
        if (!isInBounds(row, col, matrix) || !isValid(row, col, matrix)) {
            return 0;
        }

        matrix[row][col] = '.';

        return 1 + findSize(row, col + 1, matrix) +
                findSize(row + 1, col, matrix) +
                findSize(row, col - 1, matrix) +
                findSize(row - 1, col, matrix);
    }

    private static boolean isInBounds(int row, int col, char[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static boolean isValid(int row, int col, char[][] matrix) {
        return matrix[row][col] == '-';
    }

    private static char[][] readMatrix() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int rows = Integer.parseInt(bufferedReader.readLine());
        int cols = Integer.parseInt(bufferedReader.readLine());
        char[][] matrix = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            matrix[i] = bufferedReader.readLine().toCharArray();
        }

        return matrix;
    }

    private static class Area implements Comparable<Area> {
        private int row;
        private int col;
        private int size;

        @Override
        public int compareTo(Area area) {
            if (size == area.size) {
                if (row == area.row) {
                    return Integer.compare(col, area.col);
                }

                return Integer.compare(row, area.row);
            }

            return Integer.compare(area.size, size);
        }

        @Override
        public String toString() {
            return String.format("(%d, %d), size: %d", row, col, size);
        }
    }
}
