package ru.spbau.shestavin.coffee.consoleUI.exceptions;

/**
 * Classname:
 * User: dimatwl
 * Date: 5/30/12
 * Time: 12:08 PM
 */
public class UnsupportedCommandException extends Exception {
    public UnsupportedCommandException(String inpLine) {
        super(inpLine);
    }
}
