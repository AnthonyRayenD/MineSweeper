package com.minesweeper;

import com.minesweeper.model.Board;
import com.minesweeper.service.MineSweeperService;

import java.util.Scanner;

/**
 * The main class
 */
public class Game {

    private static boolean gameOver = false;

    public static void main(String[] args) {

        System.out.println("Welcome to Minesweeper!");

        final MineSweeperService service = new MineSweeperService();
        Scanner scanner = new Scanner(System.in);

        playGame(service, scanner);
        System.out.println("Press any key to play again...");


    }

    /**
     * @param service - the service
     * @param scanner
     */
    public static void playGame(final MineSweeperService service, final Scanner scanner) {
        System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
        int gridSize = scanner.nextInt();

        System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
        int mineCount = scanner.nextInt();

        final Board board = service.createBoard(gridSize, gridSize, mineCount);

        service.printBoard(board, false);

        while (!gameOver && !service.isAllOpen(board.getGrid(), gridSize, gridSize)) {
            System.out.println("THE VALUE " + gameOver);
            System.out.print("Select a square to reveal (e.g. A1): ");
            final String input = scanner.next();

            if (service.hasMine(board, input)) {
                gameOver = true;
                System.out.println("Oh no, you detonated a mine! Game over.");
                service.printBoard(board, true);
            } else {
                service.showBlock(board, input);
                System.out.println("Here is your updated minefield:");
                service.printBoard(board, false);
            }
        }

    }
}