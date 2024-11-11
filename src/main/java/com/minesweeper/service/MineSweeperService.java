package com.minesweeper.service;

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
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

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
     * This method will display a block value.
     *
     * @param board   - the board
     * @param blockId - the block to be displayed
     */
    public void showBlock(Board board, String blockId) {
        int row = getRow(blockId);
        int col = getColumn(blockId);
        final Block[][] grid = board.getGrid();
        if (isValidBlock(board, row, col) && !grid[row][col].isRevealed()) {
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
        if (isValidBlock(board, row, column) && !grid[row][column].isRevealed()) {
            grid[row][column].setRevealed(true);
            if (grid[row][column].getAdjacentMines() == 0 && !grid[row][column].isMine()) {
                showAdjacentBlocks(board, row, column);
            }
        }
    }

    private void showAdjacentBlocks(final Board board, int row, int column) {

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

    }

    /**
     * This will return true if block exists on the given row / column combination.
     *
     * @param board - the board
     * @param row   - the row
     * @param col   - the column
     * @return true/false
     */
    private boolean isValidBlock(final Board board, int row, int col) {
        return row >= 0 && row < board.getRowCount() && col >= 0 && col < board.getColumnCount();
    }

    /**
     * This method will return the row number from block id
     *
     * @param blockId - the block id
     * @return the row number
     */
    private int getRow(String blockId) {
        final String rowString = blockId.substring(0, 1);
        return ALPHABET.indexOf(rowString.toUpperCase());
    }

    /**
     * This method will return the column number from block id
     *
     * @param blockId - the block id
     * @return the column number
     */
    private int getColumn(String blockId) {
        final String columnString = blockId.substring(1);
        return Integer.parseInt(columnString) - 1;
    }

    /**
     * This will return true if the grid combination is a Mine.
     *
     * @param board   - the board
     * @param blockId - the blockId
     * @return true if block is Mine
     */
    public boolean hasMine(final Board board, final String blockId) {
        final int row = getRow(blockId);
        final int column = getColumn(blockId);
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
