package com.minesweeper.exceptions;

/**
 * The not found exception found
 */
public class NotFoundException extends Exception {

    /**
     * @param message - the error message
     */
    public NotFoundException(final String message) {
        super(message);
    }
}
