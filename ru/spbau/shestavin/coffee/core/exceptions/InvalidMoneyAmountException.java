package ru.spbau.shestavin.coffee.core.exceptions;

/**
 * Classname:
 * User: dimatwl
 * Date: 5/30/12
 * Time: 10:17 AM
 */
public class InvalidMoneyAmountException extends Exception {
    public InvalidMoneyAmountException(String inpLine) {
        super(inpLine);
    }
}
