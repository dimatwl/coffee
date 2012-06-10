package ru.spbau.shestavin.coffee.core;

import ru.spbau.shestavin.coffee.core.exceptions.EmptyProductNameException;
import ru.spbau.shestavin.coffee.core.exceptions.InvalidMoneyAmountException;

/**
 * Created with IntelliJ IDEA.
 * User: DimaTWL
 * Date: 6/4/12
 * Time: 8:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class MachineFactory {
    public static BusinessMachine createCoffeeMachine(){
        BaseMachine coffeeMachine = new BaseMachine();
        try {
            coffeeMachine.registerMoneyAmount(1);
            coffeeMachine.registerMoneyAmount(2);
            coffeeMachine.registerMoneyAmount(3);
            coffeeMachine.registerMoneyAmount(5);
        } catch (InvalidMoneyAmountException e) {
            //that can't be!
            throw new RuntimeException("Internal error.");
        }
        try {
            coffeeMachine.registerProduct("cappuccino", 4);
            coffeeMachine.registerProduct("latte", 6);
            coffeeMachine.registerAddition("sugar", 1);
        } catch (InvalidMoneyAmountException e) {
            //that can't be!
            throw new RuntimeException("Internal error.");
        } catch (EmptyProductNameException e) {
            //that can't be!
            throw new RuntimeException("Internal error.");
        }
        return coffeeMachine;
    }

    public static BusinessMachine createVendingMachine(){
        BaseMachine vendingMachine = (BaseMachine)MachineFactory.createCoffeeMachine();
        try {
            vendingMachine.registerMoneyAmount(10);
            vendingMachine.registerMoneyAmount(50);
        } catch (InvalidMoneyAmountException e) {
            //that can't be!
            throw new RuntimeException("Internal error.");
        }
        try {
            vendingMachine.registerProduct("bonaqua", 10);
            vendingMachine.registerProduct("perrier", 30);
        } catch (InvalidMoneyAmountException e) {
            //that can't be!
            throw new RuntimeException("Internal error.");
        } catch (EmptyProductNameException e) {
            //that can't be!
            throw new RuntimeException("Internal error.");
        }
        return vendingMachine;
    }
}
