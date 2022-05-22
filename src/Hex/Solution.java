package Hex;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read the number of test cases.
        int t = scanner.nextInt();
        for (int caseIndex = 1; caseIndex <= t; caseIndex++) {
            // Read the board size.
            int n = scanner.nextInt();
            // Read each row of the board.
            char[][] board = new char[n][];
            for (int i = 0; i < n; i++) {
                board[i] = scanner.next().toCharArray();
            }
            // Determine the game status and display it.
            String status = determineStatus(board);
            System.out.println("Case #" + caseIndex + ": " + status);
        }

//        char[][] board = {
//                {'B', 'B', 'B', 'B'},
//                {'B', 'B', 'B', 'B'},
//                {'R', 'R', 'R', '.'},
//                {'R', 'R', 'R', 'R'}};
//
//        String res = determineStatus(board);
//
//        for (char[] row : board) {
//            System.out.println(row);
//        }
//
//        System.out.println(res);
    }

    /** Returns a status string as specified by the Hex problem statement. */
    static String determineStatus(char[][] board) {
        //  B, R
        int[] countMarked = countStones(board);

        if (countMarked[0] == 0 && countMarked[1] == 0) {
            return "Nobody wins";
        } else if (!isValid(countMarked)) {
            return "Impossible";
        } else {
            int bluePaths = numOfBluePaths(board);
            int redPaths = numOfRedPaths(board);

            if (bluePaths == 1 && redPaths == 0) {
                if (countMarked[0] >= countMarked[1]) {
                    return "Blue wins";
                }
                return "Impossible";
            }
            else if (bluePaths == 0 && redPaths == 1) {
                if (countMarked[1] >= countMarked[0]) {
                    return "Red wins";
                }
                return "Impossible";
            }
            else if (bluePaths > 1 || redPaths > 1) return "Impossible";
            else return "Nobody wins";
        }
    }

    private static int numOfBluePaths(char[][] board) {
        char cellColor = 'B';
        int numberOfPaths = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == cellColor && isConnectedBlue(board, new int[] {i, 0}, cellColor)) {
                numberOfPaths++;
            }
        }

        return numberOfPaths;
    }

    private static boolean isConnectedBlue(char[][] board, int[] startCell, char cellColor) {
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        stack.push(startCell);

        while (!stack.isEmpty()) {
            int[] curCell = stack.pop();

            if (board[curCell[0]][curCell[1]] == 'v') continue;
            board[curCell[0]][curCell[1]] = 'v';

            if (curCell[1] == board.length-1) return true;

            addNeighboursBlue(board, curCell, stack, cellColor);
        }

        return false;
    }

    private static int numOfRedPaths(char[][] board) {
        char cellColor = 'R';
        int numberOfPaths = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[0][i] == cellColor &&
                    isConnectedRed(board, new int[] {0, i}, cellColor)) {
                numberOfPaths++;
            }
        }

        return numberOfPaths;
    }

    private static boolean isConnectedRed(char[][] board, int[] startCell, char cellColor) {

        ArrayDeque<int[]> stack = new ArrayDeque<>();
        stack.push(startCell);

        while (!stack.isEmpty()) {
            int[] curCell = stack.pop();

            if (board[curCell[0]][curCell[1]] == 'v') continue;
            board[curCell[0]][curCell[1]] = 'v';

            if (curCell[0] == board.length-1) return true;

            addNeighboursRed(board, curCell, stack, cellColor);
        }

        return false;
    }

    private static void addNeighboursBlue(char[][] board, int[] curCell, ArrayDeque<int[]> stack, char cellColor) {
        int row = curCell[0];
        int col = curCell[1];
        int length = board.length;

        // left
        if (col-1 >= 0 && board[row][col-1] == cellColor) {
            stack.push(new int[] {row, col-1});
        }
        // left down
        if (row+1 < length && col-1 >= 0 && board[row+1][col-1] == cellColor) {
            stack.push(new int[] {row+1, col-1});
        }
        // down
        if (row+1 < length && board[row+1][col] == cellColor) {
            stack.push(new int[] {row+1, col});
        }
        // right
        if (col+1 < length && board[row][col+1] == cellColor) {
            stack.push(new int[] {row, col+1});
        }
        // up right
        if (row-1 >= 0 && col+1 < length && board[row-1][col+1] == cellColor) {
            stack.push(new int[] {row-1, col+1});
        }
        // up
        if (row-1 >= 0 && board[row-1][col] == cellColor) {
            stack.push(new int[] {row-1, col});
        }
    }
    private static void addNeighboursRed(char[][] board, int[] curCell, ArrayDeque<int[]> stack, char cellColor) {
        int row = curCell[0];
        int col = curCell[1];
        int length = board.length;

        // up
        if (row-1 >= 0 && board[row-1][col] == cellColor) {
            stack.push(new int[] {row-1, col});
        }
        // up right
        if (row-1 >= 0 && col+1 < length && board[row-1][col+1] == cellColor) {
            stack.push(new int[] {row-1, col+1});
        }
        // right
        if (col+1 < length && board[row][col+1] == cellColor) {
            stack.push(new int[] {row, col+1});
        }
        // down
        if (row+1 < length && board[row+1][col] == cellColor) {
            stack.push(new int[] {row+1, col});
        }
        // left down
        if (row+1 < length && col-1 >= 0 && board[row+1][col-1] == cellColor) {
            stack.push(new int[] {row+1, col-1});
        }
        // left
        if (col-1 >= 0 && board[row][col-1] == cellColor) {
            stack.push(new int[] {row, col-1});
        }
    }

    private static boolean isValid(int[] countMarked) {
        return
                countMarked[0] + 1 == countMarked[1] ||
                countMarked[0] - 1 == countMarked[1] ||
                countMarked[0] == countMarked[1] ||
                countMarked[0] == countMarked[1] + 1 ||
                countMarked[0] == countMarked[1] - 1;
    }

    private static int[] countStones(char[][] board) {
        int[] result = new int[2];

        for (char[] row : board) {
            for (char cell : row) {
                if (cell == 'B') {
                    result[0]++;
                } else if (cell == 'R') {
                    result[1]++;
                }
            }
        }

        return result;
    }
}
