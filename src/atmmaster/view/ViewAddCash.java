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
public class ViewAddCash extends ViewBase {
    public ViewAddCash (Keypad keypad, Screen screen) {
        super(keypad, screen);
    }
    
    public int showInputAmount () {
        screen.displayMessage("\nPlease enter the amount of $20.00 bills (or 0 to cancel): ");
        return keypad.getInput();
    }
    
    public void showCancel () {
        screen.displayMessageLine("\nCanceling...");
    }
    
    public void showSucceeded () {
        screen.displayMessageLine("\nCash has been added.");
    }
}
