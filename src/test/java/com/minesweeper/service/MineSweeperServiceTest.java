package com.minesweeper.service;

import com.minesweeper.model.Block;
import com.minesweeper.model.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * This class contains test cases related to MineSweeperService.
 */
class MineSweeperServiceTest {

    /**
     * The mineSweeperService.
     */
    private static MineSweeperService mineSweeperService;

    @BeforeAll
    static void setup() {
        mineSweeperService = new MineSweeperService();
    }

    /**
     * Test board creation.
     */
    @Test
    void testCreateBoardMethod() {
        final int row = 5;
        final int column = 5;
        final int mineCount = 4;
        final Board board = createBoard(row, column, mineCount);

        Assertions.assertEquals(row, board.getRowCount());
        Assertions.assertEquals(column, board.getColumnCount());
        Assertions.assertEquals(mineCount, board.getNumberOfMines());
        Assertions.assertEquals(row, board.getRowCount());
    }

    /**
     * Test when a block id is revealed the block will get updated as isRevealed true.
     */
    @Test
    void testShowBlockWithRowAndColumn() {
        final int row = 4;
        final int column = 4;
        final int mineCount = 3;
        final Board board = createBoard(row, column, mineCount);

        mineSweeperService.showBlock(board, 3, 3);
        final Block block = board.getGrid()[3][3];
        Assertions.assertTrue(block.isRevealed());
    }

    /**
     * Test when a block id is revealed the block will get updated as isRevealed true.
     */
    @Test
    void testShowBlockWithBlockId() {

        final int row = 4;
        final int column = 4;
        final int mineCount = 3;
        final Board board = createBoard(row, column, mineCount);

        mineSweeperService.showBlock(board, "A4");
        final Block block = board.getGrid()[0][3];
        Assertions.assertTrue(block.isRevealed());
    }

    /**
     * This will create a board.
     *
     * @param row       - the row count
     * @param column    - the column count
     * @param mineCount - the mine count
     * @return {@link com.minesweeper.model.Board}
     */
    private Board createBoard(final int row, final int column, final int mineCount) {
        return mineSweeperService.createBoard(row, column, mineCount);
    }
}