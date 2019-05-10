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
public class ViewUnblockAccount extends ViewBase{
    public ViewUnblockAccount (Keypad keypad, Screen screen) {
        super(keypad, screen);
    }
    
    public int showInsertAccNum () {
        screen.displayMessage("Please insert an account number: ");
        return keypad.getInput();
    }
    
    public void showBlocked (int targetAccount) {
        screen.displayMessageLine("Account with number " + targetAccount + " has been unblocked.");
    }
    
    public void showNotBlocked (int targetAccount) {
        screen.displayMessageLine("Unable to unblock an account with number " + targetAccount + ".");
    }
}
