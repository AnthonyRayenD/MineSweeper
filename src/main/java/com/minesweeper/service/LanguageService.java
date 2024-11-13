package com.minesweeper.service;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class contains localization methods.
 */
public class LanguageService {

    /**
     * The ResourceBundle
     */
    final ResourceBundle messages;

    /**
     * The constructor
     *
     * @param locale - the locale
     */
    public LanguageService(final Locale locale) {

        this.messages = ResourceBundle.getBundle("messages", locale);
    }

    /**
     * This method will return the localization message
     *
     * @param code - te code
     * @return the message
     */
    public String getMessage(String code) {
        return messages.getString(code);
    }
}
