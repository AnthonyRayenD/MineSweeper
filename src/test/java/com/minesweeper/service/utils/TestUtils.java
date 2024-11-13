package com.minesweeper.service.utils;

import com.minesweeper.model.Board;
import com.minesweeper.service.GameBoardService;

/**
 * This class contains utility methods for testing.
 */
public class TestUtils {

    /**
     * This will create a board.
     *
     * @param boardService - the gameBoardService
     * @param row       - the row count
     * @param column    - the column count
     * @param mineCount - the mine count
     * @return {@link com.minesweeper.model.Board}
     */
    public static Board createBoard(final GameBoardService boardService, final int row, final int column, final int mineCount) {
        return boardService.createBoard(row, column, mineCount);
    }
}
