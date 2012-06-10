package ru.spbau.shestavin.coffee.core;

import ru.spbau.shestavin.coffee.core.exceptions.NotEnoughMoneyException;
import ru.spbau.shestavin.coffee.core.exceptions.ProductNotAvailableException;
import ru.spbau.shestavin.coffee.core.exceptions.UnsupportedMoneyAmountException;

import java.util.List;

/**
 * Classname:
 * User: dimatwl
 * Date: 5/29/12
 * Time: 11:02 PM
 */
public interface BusinessMachine {
    public void insertMoney(int inpAmount) throws UnsupportedMoneyAmountException;

    public String getProduct(String inpProductName) throws ProductNotAvailableException, NotEnoughMoneyException;

    public void getAddition(String inpAdditionName) throws ProductNotAvailableException, NotEnoughMoneyException;

    public void resetAdditions();

    public List<Integer> getMoneyBack();
}
