package com.minesweeper.service;

import com.minesweeper.exceptions.NotFoundException;
import com.minesweeper.model.Board;
import com.minesweeper.service.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test class contains methods related to the game board
 */
class GameBoardServiceTest {

    /**
     * The gameBoardService.
     */
    private GameBoardService gameBoardService;

    @BeforeEach
    void setup() {
        gameBoardService = new GameBoardService();
    }

    /**
     * Test has mines when invalid blockId is passed.
     * This will throw a NotFoundException.
     */
    @Test
    void testHasMineWhenBlockDoesNotExist() {

        final int row = 4;
        final int column = 4;
        final int mineCount = 3;
        final Board board = TestUtils.createBoard(gameBoardService, row, column, mineCount);

        final NotFoundException exception = Assertions.assertThrows(NotFoundException.class,
                () -> gameBoardService.hasMine(board, "z10"));

        Assertions.assertEquals("No Blocks Found", exception.getMessage());
    }


}