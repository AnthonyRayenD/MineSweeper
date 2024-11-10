package com.minesweeper.model;

/**
 * This class represents each cell in the Board
 */
public class Block {

    private String id;

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
     * The constructor.
     *
     * @param id - the id
     */
    public Block(final String id) {
        this.id = id;
    }

    /**
     * @return value of id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final String id) {
        this.id = id;
    }

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
