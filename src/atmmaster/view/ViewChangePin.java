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
public class ViewChangePin extends ViewBase {
    public ViewChangePin (Keypad keypad, Screen screen) {
        super(keypad, screen);
    }
    
    public int showInsertPIN () {
        screen.displayMessage("\nPlease insert your new PIN: ");
        return keypad.getInput();
    }
    
    public void showContainSameDigit () {
        screen.displayMessageLine("\nThe pin cannot contain the same digit.");
    }
    
    public void showPINSameAsOldOne () {
        screen.displayMessageLine("\nThe PIN cannot be the same as the old one.");
    }
    
    public void showPinChanged () {
        screen.displayMessageLine("\nYour PIN has been changed.");
    }
}
