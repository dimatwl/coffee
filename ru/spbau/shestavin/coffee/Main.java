package ru.spbau.shestavin.coffee;

import ru.spbau.shestavin.coffee.consoleUI.ConsoleUserInterface;
import ru.spbau.shestavin.coffee.core.MachineFactory;
import ru.spbau.shestavin.coffee.core.UserInterface;


/**
 * Classname:
 * User: dimatwl
 * Date: 5/30/12
 * Time: 10:48 AM
 */
public class Main {
    public static void main(String[] args) {
        UserInterface ui = new ConsoleUserInterface(MachineFactory.createVendingMachine());
        ui.run();
    }
}
