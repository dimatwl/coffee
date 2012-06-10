package ru.spbau.shestavin.coffee.consoleUI;

import ru.spbau.shestavin.coffee.consoleUI.exceptions.CommandFormatException;
import ru.spbau.shestavin.coffee.consoleUI.exceptions.UnsupportedCommandException;
import ru.spbau.shestavin.coffee.core.BusinessMachine;
import ru.spbau.shestavin.coffee.core.UserInterface;
import ru.spbau.shestavin.coffee.core.exceptions.NotEnoughMoneyException;
import ru.spbau.shestavin.coffee.core.exceptions.ProductNotAvailableException;
import ru.spbau.shestavin.coffee.core.exceptions.UnsupportedMoneyAmountException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Classname:
 * User: dimatwl
 * Date: 5/30/12
 * Time: 10:53 AM
 */
public class ConsoleUserInterface implements UserInterface {
    BusinessMachine machine;
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public ConsoleUserInterface(BusinessMachine inpMachine) {
        this.machine = inpMachine;
    }

    private void writeMessageToUser(String inpLine) throws IOException{
        Message.print(inpLine);
    }

    private String waitForRequest() throws IOException {
        return in.readLine();
    }

    protected void handleRequest(String inpRequest) throws UnsupportedCommandException, CommandFormatException, NumberFormatException, IOException {
        String request = inpRequest.toLowerCase();
        String[] splittedRequest = request.split(" ");
        if (splittedRequest.length == 0) {
            throw new CommandFormatException("There is no command.");
        } else if (splittedRequest.length > 2) {
            throw new CommandFormatException("Too much arguments.");
        } else if ("add".equals(splittedRequest[0])){
            if (splittedRequest.length < 2){
                throw new CommandFormatException("Nothing to add.");
            }
            String additionName = splittedRequest[1];
            try {
                this.machine.getAddition(additionName);
            } catch (ProductNotAvailableException e) {
                writeMessageToUser("Addition availability: " + e.getMessage());
            } catch (NotEnoughMoneyException e) {
                writeMessageToUser("Addition price: " + e.getMessage());
            }
        } else if ("insert".equals(splittedRequest[0])) {
            if (splittedRequest.length < 2){
                throw new CommandFormatException("Nothing inserted.");
            }
            Integer amount = Integer.parseInt(splittedRequest[1]);
            try {
                this.machine.insertMoney(amount);
            } catch (UnsupportedMoneyAmountException e) {
                writeMessageToUser("Money amount: " + e.getMessage());
            }
        } else if ("select".equals(splittedRequest[0])) {
            if (splittedRequest.length < 2){
                throw new CommandFormatException("Nothing selected.");
            }
            String productName = splittedRequest[1];
            try {
                productName = this.machine.getProduct(productName);
                List<Integer> change = this.machine.getMoneyBack();
                writeMessageToUser("Please get your " + productName + ".");
                String resultMessage = "Please get your change: ";
                for (int i = 0; i < change.size(); ++i){
                    resultMessage += change.get(i);
                    if (i == change.size() - 1){
                        resultMessage += ".";
                    } else {
                        resultMessage += ", ";
                    }
                }
                writeMessageToUser(resultMessage);
            } catch (ProductNotAvailableException e) {
                writeMessageToUser("Product availability: " + e.getMessage());
            } catch (NotEnoughMoneyException e) {
                writeMessageToUser("Product price: " + e.getMessage());
            }
        } else if ("cancel".equals(splittedRequest[0])) {
            if (splittedRequest.length > 1) {
                throw new CommandFormatException("Too much arguments.");
            } else {
                this.machine.resetAdditions();
                List<Integer> change = this.machine.getMoneyBack();
                String resultMessage = "Please get your money back: ";
                for (int i = 0; i < change.size(); ++i){
                    resultMessage += change.get(i);
                    if (i == change.size() - 1){
                        resultMessage += ".";
                    } else {
                        resultMessage += ", ";
                    }
                }
                writeMessageToUser(resultMessage);
            }
        } else {
            throw new UnsupportedCommandException("Command " + splittedRequest[0] + " isn't supported.");
        }
    }

    @Override
    public void run() {
        while (true) {
            try{
                try {
                    String request = this.waitForRequest();
                    if ("exit".equals(request)) {
                        return;
                    } else {
                        this.handleRequest(request);
                    }
                }  catch (NumberFormatException e) {
                    writeMessageToUser("Wrong number format: " + e.getMessage());
                } catch (UnsupportedCommandException e) {
                    writeMessageToUser("Unknown command: " + e.getMessage());
                } catch (CommandFormatException e) {
                    writeMessageToUser("Bad format : " + e.getMessage());
                } catch (IOException e) {
                    writeMessageToUser("IO error occured: " + e.getMessage());
                }
            } catch (IOException e) {
                System.err.println("IO error occured: " + e.getMessage());
            }
        }
    }
}
