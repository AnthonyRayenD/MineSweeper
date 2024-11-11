package com.minesweeper;

import com.minesweeper.model.Board;
import com.minesweeper.service.MineSweeperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * The main class
 */
public class Game {

    /**
     */
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    /**
     * The game status.
     */
    private static boolean gameOver = false;

    public static void main(String[] args) {

        logger.info("Welcome to Minesweeper!\n");

        final MineSweeperService service = new MineSweeperService();
        Scanner scanner = new Scanner(System.in);

        playGame(service, scanner);
        logger.info("Press any key to play again...\n");

    }

    /**
     * @param service - the service
     * @param scanner - the scanner
     */
    public static void playGame(final MineSweeperService service, final Scanner scanner) {

        logger.info("Enter the size of the grid (e.g. 4 for a 4x4 grid):");
        int gridSize = scanner.nextInt();

        logger.info("Enter the number of mines to place on the grid (maximum is 35% of the total squares): \n");
        int mineCount = scanner.nextInt();

        final Board board = service.createBoard(gridSize, gridSize, mineCount);

        logger.info("Here is your minefield: \n");
        service.printBoard(board, false);

        while (!gameOver && !service.isAllOpen(board.getGrid(), gridSize, gridSize)) {
            logger.info("Select a square to reveal (e.g. A1): \n");
            final String input = scanner.next();

            if (service.hasMine(board, input)) {
                gameOver = true;
                logger.info("Oh no, you detonated a mine! Game over. \n");
                service.printBoard(board, true);
            } else {
                service.showBlock(board, input);
                logger.info("Here is your updated minefield: \n");
                service.printBoard(board, false);
            }
        }

    }
}