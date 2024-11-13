package com.minesweeper;

import com.minesweeper.service.GamePlayService;
import com.minesweeper.service.LanguageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * The main class
 */
public class Game {

    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    /**
     * The game play service.
     */
    private static GamePlayService gamePlayService;

    /**
     * The main method
     *
     * @param args - the args
     */
    public static void main(String[] args) {

        final LanguageService languageService = new LanguageService(Locale.ENGLISH);

        gamePlayService = new GamePlayService(Locale.ENGLISH);
        try {
            //Start game play
            gamePlayService.playGame();

        } catch (Exception e) {

            logger.error("An error occurred during the game: {}", e.getMessage(), e);
        }

        logger.info(languageService.getMessage("playAgainMessage"));

    }
}