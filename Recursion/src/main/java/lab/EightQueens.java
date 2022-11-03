package lab;

import java.util.HashSet;
import java.util.Set;

public class EightQueens {

    private static final int SIZE = 8;
    private static boolean[][] chessboard = new boolean[SIZE][SIZE];
    private static int solutionsFound = 0;

    private static Set<Integer> attackedRows = new HashSet<>();
    private static Set<Integer> attackedCols = new HashSet<>();
    private static Set<Integer> attackedLeftDiagonals = new HashSet<>();
    private static Set<Integer> attackedRightDiagonals = new HashSet<>();

    public static void main(String[] args) {
        putQueens(0);
    }

    private static void putQueens(int row) {
        if (row == SIZE) {
            printSolutions();
            return;
        }

        for (int col = 0; col < SIZE; col++) {
            if (canPlaceQueen(row, col)) {
                markAllAttackedPositions(row, col);
                putQueens(row + 1);
                unmarkAllAttackedPositions(row, col);
            }
        }
    }

    private static void unmarkAllAttackedPositions(int row, int col) {
        attackedRows.remove(row);
        attackedCols.remove(col);
        attackedLeftDiagonals.remove(col - row);
        attackedRightDiagonals.remove(row + col);
        chessboard[row][col] = false;
    }

    private static void markAllAttackedPositions(int row, int col) {
        attackedRows.add(row);
        attackedCols.add(col);
        attackedLeftDiagonals.add(col - row);
        attackedRightDiagonals.add(row + col);
        chessboard[row][col] = true;
    }

    private static boolean canPlaceQueen(int row, int col) {
        return !(attackedRows.contains(row) || attackedCols.contains(col) ||
                attackedLeftDiagonals.contains(col - row) ||
                attackedRightDiagonals.contains(row + col));
    }

    private static void printSolutions() {
        for (boolean[] rowElements : chessboard) {
            for (boolean rowElement : rowElements) {
                if (rowElement) {
                    System.out.print("* ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println();
        solutionsFound++;
    }
}
