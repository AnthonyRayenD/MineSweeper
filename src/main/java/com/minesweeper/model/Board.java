package com.minesweeper.model;

/**
 * This class contains elements related to Minesweeper grid
 */
public class Board {

    /**
     * The row count.
     */
    private int rowCount;

    /**
     * The column count.
     */
    private int columnCount;

    /**
     * The mine count.
     */
    private int numberOfMines;

    /**
     * The grid.
     */
    private Block[][] grid;

    /**
     * The constructor
     *
     * @param rowCount - the rowCount
     * @param columnCount - the columnCount
     * @param numberOfMines - the numberOfMines
     */
    public Board(final int rowCount, final int columnCount, final int numberOfMines) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.numberOfMines = numberOfMines;
        this.grid = new Block[rowCount][columnCount];
    }

    /**
     * @return value of horizontalBlockCount
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * @param rowCount the horizontalBlockCount to set
     */
    public void setRowCount(final int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * @return value of verticalBlockCount
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * @param columnCount the verticalBlockCount to set
     */
    public void setColumnCount(final int columnCount) {
        this.columnCount = columnCount;
    }

    /**
     * @return value of numberOfMines
     */
    public int getNumberOfMines() {
        return numberOfMines;
    }

    /**
     * @param numberOfMines the numberOfMines to set
     */
    public void setNumberOfMines(final int numberOfMines) {
        this.numberOfMines = numberOfMines;
    }

    /**
     * @return value of grid
     */
    public Block[][] getGrid() {
        return grid;
    }

    /**
     * @param grid the grid to set
     */
    public void setGrid(final Block[][] grid) {
        this.grid = grid;
    }
}
