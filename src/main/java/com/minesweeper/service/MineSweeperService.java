package com.minesweeper.service;

import com.minesweeper.common.CommonUtils;
import com.minesweeper.model.Block;
import com.minesweeper.model.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * This class contains minesweeper game logic methods.
 */
public class MineSweeperService {

    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(MineSweeperService.class);

    /**
     * The alphabet string.
     */
    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * This method will create a game board with mines & numbers.
     *
     * @param rowCount    - the number of rows
     * @param columnCount - the number of columns
     * @param mineCount   - the number of mines
     * @return {@link com.minesweeper.model.Board}
     */
    public Board createBoard(int rowCount, int columnCount, int mineCount) {
        Board board = new Board(rowCount, columnCount, mineCount);

        //Populate each item in grid
        final Block[][] grid = board.getGrid();

        for (int row = 0; row < rowCount; row++) {

            for (int column = 0; column < columnCount; column++) {

                grid[row][column] = new Block(ALPHABET + row);
            }
        }
        //Populate mines
        populateMines(rowCount, columnCount, mineCount, board);

        return board;
    }

    /**
     * This method will print the current game board.
     *
     * @param board     - the board
     * @param revealAll - this is reveal all values of the board if true
     */
    public void printBoard(final Board board, boolean revealAll) {

        //Used to print header
        populateHeader(board);

        for (int row = 0; row < board.getRowCount(); row++) {

            logger.info("{} ", ALPHABET.charAt(row));

            for (int col = 0; col < board.getColumnCount(); col++) {
                Block cell = board.getGrid()[row][col];
                if (revealAll || cell.isRevealed()) {
                    if (cell.isMine()) {
                        logger.info("* ");
                    } else {
                        logger.info("{} ", cell.getAdjacentMines());
                    }
                } else {

                    logger.info("_ ");
                }
            }
            logger.info("\n");
        }
    }

    /**
     * This method is used to print the header.
     *
     * @param board - the board
     */
    private void populateHeader(final Board board) {
        int header = 0;
        while (header <= board.getColumnCount()) {
            if (header == 0) {
                logger.info("  ");
            } else {
                logger.info("{} ", header);
            }
            header++;
        }
        logger.info("\n");
    }

    /**
     * This method will randomly populate mines in the board.
     *
     * @param rowCount    - the total number of rows
     * @param columnCount - the total number of columns
     * @param mineCount   - the number mines to be populated
     * @param board       - the board
     */
    private void populateMines(final int rowCount, final int columnCount, final int mineCount, final Board board) {

        final Random random = new Random();
        final Block[][] grid = board.getGrid();
        int placedMineCount = 0;

        while (placedMineCount < mineCount) {

            int row = random.nextInt(rowCount);
            int column = random.nextInt(columnCount);

            final Block block = grid[row][column];

            if (!block.isMine()) {
                block.setMine(true);
                placedMineCount++;
                updateAdjacentBlocks(board, row, column);
            }

        }

    }

    /**
     * This method will update the adjacent blocks with mines.
     *
     * @param board  - the board
     * @param row    - the row number
     * @param column - the column number
     */
    private void updateAdjacentBlocks(final Board board, int row, int column) {
        final Block[][] grid = board.getGrid();
        for (int adjRow = row - 1; adjRow <= row + 1; adjRow++) {
            for (int col = column - 1; col <= column + 1; col++) {
                grid[adjRow][col].setAdjacentMines(grid[adjRow][col].getAdjacentMines() + 1);
            }
        }
    }

    /**
     * This will return true if the grid combination is a Mine.
     *
     * @param board   - the board
     * @param blockId - the blockId
     * @return true if block is Mine
     */
    public boolean hasMine(final Board board, final String blockId) {
        final int row = CommonUtils.getRow(blockId);
        final int column = CommonUtils.getColumn(blockId);
        return board.getGrid()[row][column].isMine();
    }

    /**
     * This will return true if all blocked are revealed.
     *
     * @param grid    - the grid
     * @param rows    - the rows
     * @param columns - the columns
     * @return true if all revealed
     */
    public boolean isAllOpen(final Block[][] grid, final int rows, int columns) {

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Block cell = grid[row][col];
                if (!cell.isMine() && !cell.isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }
}
