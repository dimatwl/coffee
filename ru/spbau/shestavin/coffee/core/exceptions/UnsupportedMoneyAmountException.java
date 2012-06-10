package ru.spbau.shestavin.coffee.core.exceptions;

/**
 * Classname:
 * User: dimatwl
 * Date: 5/30/12
 * Time: 1:02 AM
 */
public class UnsupportedMoneyAmountException extends Exception {
    public UnsupportedMoneyAmountException(String inpLine) {
        super(inpLine);
    }
}
