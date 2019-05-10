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
public class ViewTransfer extends ViewBase {
    public ViewTransfer (Keypad keypad, Screen screen) {
        super(keypad, screen);
    }
    
    public void showCanceled () {
        screen.displayMessageLine("\nCanceling transaction...");
    }
    
    public void showTransferLimit () {
        screen.displayMessageLine("\nTransfer limit exceeded.");
    }
    
    public void showInsufficient () {
        screen.displayMessageLine("\nInsufficient amount of the available balance.");
    }
    
    public void showSucceeded () {
        screen.displayMessageLine("\nYour balance has been transferred successfully.");
    }
    
    public void showFailed () {
        screen.displayMessageLine("\nFailed to perform transfer...");
    }
    
    public int performGetReceiver() {
        screen.displayMessage("\nPlease specify receiver's account number: ");
        return keypad.getInput();
    }
    
    public double performGetAmount() {
        int input;
        screen.displayMessage("\n");
        do {
            screen.displayMessage("Please input the transfer amount in CENTS (or 0 to cancel)[LIMIT: ");
            screen.displayDollarAmount(100);
            screen.displayMessage("]: ");
            input = keypad.getInput();

        } while (input < 0);

        return (double) input / 100;
    }
}
