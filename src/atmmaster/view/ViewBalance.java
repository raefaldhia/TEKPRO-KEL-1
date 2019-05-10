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
public class ViewBalance extends ViewBase {
    public ViewBalance (Keypad keypad, Screen screen) {
        super(keypad, screen);
    }
    
    public void showBalance (double availBalance, double totalBalance) {
        // display the balance information on the screen
        screen.displayMessageLine("\nBalance Information:");
        screen.displayMessage(" - Available balance: "); 
        screen.displayDollarAmount(availBalance);
        screen.displayMessage("\n - Total balance:     ");
        screen.displayDollarAmount(totalBalance);
        screen.displayMessageLine("");
    }
}
