package com.minesweeper.service;

import com.minesweeper.exceptions.NotFoundException;
import com.minesweeper.model.Block;
import com.minesweeper.model.Board;
import com.minesweeper.service.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This class contains test cases related to GamePlayService.
 */
class GamePlayServiceTest {

    /**
     * The gameBoardService.
     */
    private GamePlayService gamePlayService;

    /**
     * The gameBoardService.
     */
    private GameBoardService gameBoardService;

    @BeforeEach
    void setup() {
        gamePlayService = new GamePlayService(Locale.ENGLISH);
        gameBoardService = new GameBoardService();
    }

    /**
     * Test board creation.
     */
    @Test
    void testCreateBoardMethod() {
        final int row = 5;
        final int column = 5;
        final int mineCount = 4;
        final Board board = TestUtils.createBoard(gameBoardService,row, column, mineCount);

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
        final Board board = TestUtils.createBoard(gameBoardService,row, column, mineCount);

        gamePlayService.showBlock(board, 3, 3);
        final Block block = board.getGrid()[3][3];
        Assertions.assertTrue(block.isRevealed());
    }

    /**
     * Test when a valid block id is revealed the block will get updated as isRevealed true.
     */
    @Test
    void testShowBlockWithValidBlockId() {

        final int row = 4;
        final int column = 4;
        final int mineCount = 3;
        final Board board = TestUtils.createBoard(gameBoardService,row, column, mineCount);

        gamePlayService.showBlock(board, "A4");
        final Block block = board.getGrid()[0][3];
        Assertions.assertTrue(block.isRevealed());
    }
}