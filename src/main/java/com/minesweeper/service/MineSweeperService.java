package com.minesweeper.service;

import com.minesweeper.model.Block;
import com.minesweeper.model.Board;

import java.util.Random;
import java.util.logging.Logger;

/**
 * This class contains minesweeper game logic methods.
 */
public class MineSweeperService {

    // This is used to name each row
    final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * This method will create a game board with mines & numbers.
     *
     * @param rowCount - the number of rows
     * @param columnCount - the number of columns
     * @param mineCount - the number of mines
     * @return {@link com.minesweeper.model.Board}
     */
    public Board createBoard(int rowCount, int columnCount, int mineCount) {
        Logger.getLogger("Hello");
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
     * @param board - the board
     * @param revealAll - this is reveal all values of the board if true
     */
    public void printBoard(final Board board, boolean revealAll) {

        //Used to print header
        int header = 0;
        while (header <= board.getColumnCount()) {
            if (header == 0) {
                System.out.print("  ");
            } else {
                System.out.print(header + " ");
            }
            header++;
        }
        System.out.println();
        for (int row = 0; row < board.getRowCount(); row++) {
            System.out.print(ALPHABET.charAt(row) + " ");
            for (int col = 0; col < board.getColumnCount(); col++) {
                Block cell = board.getGrid()[row][col];
                if (revealAll || cell.isRevealed()) {
                    if (cell.isMine()) {
                        System.out.print("* ");
                    } else {
                        System.out.print(cell.getAdjacentMines() + " ");
                    }
                } else {

                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }

    /**
     * This method will randomly populate mines in the board.
     *
     * @param rowCount - the total number of rows
     * @param columnCount - the total number of columns
     * @param mineCount - the number mines to be populated
     * @param board - the board
     */
    private void populateMines(final int rowCount, final int columnCount, final int mineCount, final Board board) {

        final Random random = new Random();
        final Block[][] grid = board.getGrid();
        int placedMineCount = 0;

        while (placedMineCount <= mineCount) {

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
     * @param board - the board
     * @param row - the row number
     * @param column - the column number
     */
    private void updateAdjacentBlocks(final Board board, int row, int column) {
        final Block[][] grid = board.getGrid();
        for (int adjRow = row - 1; adjRow <= row + 1; adjRow++) {
            for (int col = column - 1; col <= column + 1; col++) {
                if (isValidBlock(board, adjRow, col) && !grid[adjRow][col].isMine()) {
                    grid[adjRow][col].setAdjacentMines(grid[adjRow][col].getAdjacentMines() + 1);
                }
            }
        }
    }

    private boolean isValidBlock(final Board board, int row, int col) {
        return row >= 0 && row < board.getRowCount() && col >= 0 && col < board.getColumnCount();
    }

    public void clickBlock(String blockName) {


    }
}
