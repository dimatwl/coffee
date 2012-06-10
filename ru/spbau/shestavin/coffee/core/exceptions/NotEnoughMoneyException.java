package ru.spbau.shestavin.coffee.core.exceptions;

/**
 * Classname:
 * User: dimatwl
 * Date: 5/30/12
 * Time: 10:38 AM
 */
public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException(String inpLine) {
        super(inpLine);
    }
}
