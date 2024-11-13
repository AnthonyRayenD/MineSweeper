package com.minesweeper.common;

import com.minesweeper.model.Board;

import static com.minesweeper.service.MineSweeperService.ALPHABET;

/**
 * This class contains common methods
 */
public final class CommonUtils {

    /**
     */
    private CommonUtils() {
        //Do nothing
    }

    /**
     * This method will return the row number from block id
     *
     * @param blockId - the block id
     * @return the row number
     */
    public static int getRow(String blockId) {
        final String rowString = blockId.substring(0, 1);
        return ALPHABET.indexOf(rowString.toUpperCase());
    }

    /**
     * This method will return the column number from block id
     *
     * @param blockId - the block id
     * @return the column number
     */
    public static int getColumn(String blockId) {
        final String columnString = blockId.substring(1);
        return Integer.parseInt(columnString) - 1;
    }

    /**
     * This will return true if block exists on the given row / column combination.
     *
     * @param board - the board
     * @param row   - the row
     * @param col   - the column
     * @return true/false
     */
    public static boolean isValidBlock(final Board board, int row, int col) {
        return row >= 0 && row < board.getRowCount() && col >= 0 && col < board.getColumnCount();
    }
}
