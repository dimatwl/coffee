package ru.spbau.shestavin.coffee.core.exceptions;

/**
 * Classname:
 * User: dimatwl
 * Date: 5/30/12
 * Time: 1:04 AM
 */
public class ProductNotAvailableException extends Exception {
    public ProductNotAvailableException(String inpLine) {
        super(inpLine);
    }
}
