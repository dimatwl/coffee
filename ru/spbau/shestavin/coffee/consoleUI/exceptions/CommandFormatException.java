package ru.spbau.shestavin.coffee.consoleUI.exceptions;

/**
 * Classname:
 * User: dimatwl
 * Date: 5/30/12
 * Time: 12:09 PM
 */
public class CommandFormatException extends Exception {
    public CommandFormatException(String inpLine) {
        super(inpLine);
    }
}
