package edu.hw1.task8;

import java.util.ArrayList;
import java.util.List;

public final class ChessUtils {
    private static final int ROWS = 8;
    private static final int COLUMNS = 8;
    private static final String MESSAGE = "Wrong input";
    private static List<Pair<Integer, Integer>> moves = new ArrayList<>();

    private ChessUtils() {

    }

    public static boolean knightBoardCapture(int[][] board) {
        if (board == null || board.length != ROWS) {
            throw new IllegalArgumentException(MESSAGE);
        }
        for (int i = 0; i < ROWS; i++) {
            if (board[i].length != COLUMNS) {
                throw new IllegalArgumentException(MESSAGE);
            }
        }
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == 1) {
                    moves.clear();
                    moves.add(new Pair<>(i - 2, j + 1));
                    moves.add(new Pair<>(i - 2, j - 1));
                    moves.add(new Pair<>(i + 2, j + 1));
                    moves.add(new Pair<>(i + 2, j - 1));
                    moves.add(new Pair<>(i - 1, j + 2));
                    moves.add(new Pair<>(i - 1, j - 2));
                    moves.add(new Pair<>(i + 1, j + 2));
                    moves.add(new Pair<>(i + 1, j - 2));
                    for (Pair<Integer, Integer> currentMove : moves) {
                        try {
                            if (board[currentMove.getFirst()][currentMove.getSecond()] == 1) {
                                return false;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }
                    }
                }
            }
        }
        return true;
    }
}
