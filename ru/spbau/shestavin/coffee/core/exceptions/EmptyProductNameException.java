package ru.spbau.shestavin.coffee.core.exceptions;

/**
 * Classname:
 * User: dimatwl
 * Date: 5/30/12
 * Time: 12:26 AM
 */
public class EmptyProductNameException extends Exception {
    public EmptyProductNameException(String inpLine) {
        super(inpLine);
    }
}
