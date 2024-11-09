package com.minesweeper.model;

/**
 * This class represents each cell in the Board
 */
public class Block {

    /**
     * Identify whether the Block is a mine or number.
     */
    private boolean isMine;

    /**
     * Identify if the block is revealed.
     */
    private boolean isRevealed;

    /**
     * The adjacentMines.
     */
    private int adjacentMines;

    /**
     * @return value of isMine
     */
    public boolean isMine() {
        return isMine;
    }

    /**
     * @param mine the isMine to set
     */
    public void setMine(final boolean mine) {
        isMine = mine;
    }

    /**
     * @return value of isRevealed
     */
    public boolean isRevealed() {
        return isRevealed;
    }

    /**
     * @param revealed the isRevealed to set
     */
    public void setRevealed(final boolean revealed) {
        isRevealed = revealed;
    }

    /**
     * @return value of adjacentMines
     */
    public int getAdjacentMines() {
        return adjacentMines;
    }

    /**
     * @param adjacentMines the adjacentMines to set
     */
    public void setAdjacentMines(final int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }
}
