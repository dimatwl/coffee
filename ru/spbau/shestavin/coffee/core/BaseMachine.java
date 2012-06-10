package ru.spbau.shestavin.coffee.core;

import ru.spbau.shestavin.coffee.core.exceptions.*;

import java.util.*;

/**
 * Classname:
 * User: dimatwl
 * Date: 5/30/12
 * Time: 12:22 AM
 */
public class BaseMachine implements BusinessMachine {
    private Map<String, Integer> menu = new TreeMap<String, Integer>();
    private Map<String, Integer> additions = new TreeMap<String, Integer>();

    private Set<Integer> supportedMoneyAmounts = new TreeSet<Integer>();

    private Integer currentCustomerCredit = 0;
    private List<String> currentCustomerAdditions = new ArrayList<String>();

    protected boolean isMoneyAmountSupported(int inpAmount) {
        return this.supportedMoneyAmounts.contains(inpAmount);
    }

    protected boolean isProductAvailable(String inpName) {
        return this.menu.containsKey(inpName);
    }

    protected boolean isAdditionAvailable(String inpName) {
        return this.additions.containsKey(inpName);
    }

    protected void registerProduct(String inpProductName, Integer inpPrice) throws InvalidMoneyAmountException, EmptyProductNameException {
        if (inpPrice < 0) {
            throw new InvalidMoneyAmountException("Negative price.");
        } else if (inpProductName.isEmpty()) {
            throw new EmptyProductNameException("Product name is empty.");
        } else {
            this.menu.put(inpProductName, inpPrice);
        }
    }

    protected void registerMoneyAmount(Integer inpAmount) throws InvalidMoneyAmountException {
        if (inpAmount < 0) {
            throw new InvalidMoneyAmountException("Negative amount");
        } else {
            this.supportedMoneyAmounts.add(inpAmount);
        }
    }

    protected void registerAddition(String inpAdditionName, Integer inpPrice) throws InvalidMoneyAmountException, EmptyProductNameException {
        if (inpPrice < 0) {
            throw new InvalidMoneyAmountException("Negative price.");
        } else if (inpAdditionName.isEmpty()) {
            throw new EmptyProductNameException("Addition name is empty.");
        } else {
            this.additions.put(inpAdditionName, inpPrice);
        }
    }

    @Override
    public void insertMoney(int inpAmount) throws UnsupportedMoneyAmountException {
        if (!isMoneyAmountSupported(inpAmount)) {
            throw new UnsupportedMoneyAmountException("Amount of " + inpAmount + " is not supported.");
        } else {
            this.currentCustomerCredit += inpAmount;
        }
    }

    @Override
    public String getProduct(String inpProductName) throws ProductNotAvailableException, NotEnoughMoneyException {
        if (!this.isProductAvailable(inpProductName)) {
            throw new ProductNotAvailableException("Product " + inpProductName + " not available.");
        } else if (this.currentCustomerCredit < this.menu.get(inpProductName)) {
            throw new NotEnoughMoneyException("Your credit of " + this.currentCustomerCredit + " is too low to get " + inpProductName + " for " + this.menu.get(inpProductName) + ".");
        } else {
            this.currentCustomerCredit -= this.menu.get(inpProductName);
            if (!this.currentCustomerAdditions.isEmpty()){
                inpProductName += " with ";
            }
            for (int i = 0; i < this.currentCustomerAdditions.size(); ++i){
                if (i == this.currentCustomerAdditions.size() - 1){
                    inpProductName += this.currentCustomerAdditions.get(i);
                } else {
                    inpProductName += this.currentCustomerAdditions.get(i) + ", ";
                }
            }
            this.currentCustomerAdditions.clear();
            return inpProductName;
        }
    }

    @Override
    public void getAddition(String inpAdditionName) throws ProductNotAvailableException, NotEnoughMoneyException {
        if (!this.isAdditionAvailable(inpAdditionName)) {
            throw new ProductNotAvailableException("Addition " + inpAdditionName + " not available.");
        } else if (this.currentCustomerCredit < additions.get(inpAdditionName)) {
            throw new NotEnoughMoneyException("Your credit of " + this.currentCustomerCredit + " is too low to get " + inpAdditionName + " for " + this.additions.get(inpAdditionName) + ".");
        } else {
            this.currentCustomerCredit -= this.additions.get(inpAdditionName);
            this.currentCustomerAdditions.add(inpAdditionName);
        }
    }

    @Override
    public List<Integer> getMoneyBack() {
        SortedSet<Integer> amounts = new TreeSet<Integer>(this.supportedMoneyAmounts);
        List<Integer> coins = new ArrayList<Integer>();
        while (this.currentCustomerCredit > 0){
            if (this.currentCustomerCredit < amounts.last() && amounts.size() > 1){
                amounts.remove(amounts.last());
            } else {
                coins.add(amounts.last());
                this.currentCustomerCredit -= amounts.last();
            }
        }
        return coins;
    }

    protected BaseMachine(){}

    @Override
    public void resetAdditions(){
        for (String addition : this.currentCustomerAdditions){
            currentCustomerCredit += this.additions.get(addition);
        }
        this.currentCustomerAdditions.clear();
    }
}
