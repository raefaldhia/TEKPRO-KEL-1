/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmmaster.view;

/**
 *
 * @author Rayhan Azka  <rayhan.azka.tif418@polban.ac.id>
 */
public class ViewAddAccount extends ViewBase {
    public ViewAddAccount(Keypad keypad, Screen screen) {
        super(keypad, screen);
    }
    
    public void showAccountForm () {
        screen.displayMessageLine("\nAdd Account form:");
    }
    
    public int showIDForm () {
        screen.displayMessage("ID: ");
        return keypad.getInput();
    }
    
    public int showBalanceForm () {
        screen.displayMessage("Balance (in CENTS): ");
        return keypad.getInput();
    }
    
    public void showSucceded () {
        screen.displayMessageLine("\nAccount has been added.");
    }
    
    public void showFailed (int accountNumber) {
        screen.displayMessageLine("\nUnable to add account with ID: " + accountNumber + ".");
    }
}
