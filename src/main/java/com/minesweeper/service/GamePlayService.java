package com.minesweeper.service;

import com.minesweeper.common.CommonUtils;
import com.minesweeper.exceptions.NotFoundException;
import com.minesweeper.model.Block;
import com.minesweeper.model.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.Scanner;

/**
 * This class contains game play related methods.
 */
public class GamePlayService {

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(GamePlayService.class);

    /**
     * The game status.
     */
    private boolean gameOver = false;

    /**
     * The language service
     */
    private final LanguageService languageService;

    /**
     * The GameBoardService
     */
    private final GameBoardService gameBoardService;

    /**
     * The scanner
     */
    private final Scanner scanner;

    /**
     *
     */
    public GamePlayService(final Locale locale) {
        languageService = new LanguageService(locale);
        gameBoardService = new GameBoardService();
        scanner = new Scanner(System.in);
    }

    /**
     * This method handles the game play
     */
    public void playGame() throws NotFoundException {

        logger.info(languageService.getMessage("greeting"));

        logger.info(languageService.getMessage("enterGridSize"));
        int gridSize = scanner.nextInt();

        int mineCount;
        logger.info(languageService.getMessage("enterMineNumber"));
        mineCount = scanner.nextInt();

        // Validate mine count
        while (!CommonUtils.validateMineCount(gridSize, mineCount)) {
            logger.info(languageService.getMessage("invalidMineNumber"));
            logger.info(languageService.getMessage("enterMineNumber"));
            mineCount = scanner.nextInt();
        }
        playGame(gridSize, mineCount);
    }

    /**
     * This method will play game
     *
     * @param gridSize - the grid size
     * @param mineCount - the mine count
     */
    private void playGame(final int gridSize, final int mineCount) throws NotFoundException {
        final Board board = gameBoardService.createBoard(gridSize, gridSize, mineCount);
        logger.info(languageService.getMessage("displayMineField"));
        gameBoardService.printBoard(board, false);

        while (!gameOver && !gameBoardService.isAllOpen(board.getGrid(), gridSize, gridSize)) {
            logger.info(languageService.getMessage("selectMessage"));
            final String input = scanner.next();

            if (gameBoardService.hasMine(board, input)) {
                gameOver = true;
                logger.info(languageService.getMessage("failureMessage"));
                gameBoardService.printBoard(board, true);

            } else {
                showBlock(board, input);
                logger.info(languageService.getMessage("displayUpdatedField"));
                gameBoardService.printBoard(board, false);
            }

            if (gameBoardService.isAllOpen(board.getGrid(), gridSize, gridSize)) {

                logger.info(languageService.getMessage("successMessage"));
            }
        }
    }

    /**
     * This method will display a block value.
     *
     * @param board   - the board
     * @param blockId - the block to be displayed
     */
    public void showBlock(Board board, String blockId) {
        int row = CommonUtils.getRow(blockId);
        int col = CommonUtils.getColumn(blockId);
        final Block[][] grid = board.getGrid();
        if (CommonUtils.isValidBlock(board, row, col) && !grid[row][col].isRevealed()) {
            grid[row][col].setRevealed(true);
            if (grid[row][col].getAdjacentMines() == 0 && !grid[row][col].isMine()) {
                showAdjacentBlocks(board, row, col);
            }
        }
    }

    /**
     * This method will display a block value.
     *
     * @param board  - the board
     * @param row    - the row number
     * @param column - the column number
     */
    public void showBlock(Board board, int row, int column) {
        final Block[][] grid = board.getGrid();
        if (CommonUtils.isValidBlock(board, row, column) && !grid[row][column].isRevealed()) {
            grid[row][column].setRevealed(true);
            if (grid[row][column].getAdjacentMines() == 0 && !grid[row][column].isMine()) {
                showAdjacentBlocks(board, row, column);
            }
        }
    }

    /**
     * This method will display the blocks next to selected combination
     *
     * @param board  - the board
     * @param row    - the row
     * @param column - the column
     */
    private void showAdjacentBlocks(final Board board, int row, int column) {
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = column - 1; c <= column + 1; c++) {
                showBlock(board, r, c);
            }
        }
    }
}
