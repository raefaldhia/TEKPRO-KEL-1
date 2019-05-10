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
public class ViewDispenserInquiry extends ViewBase {
    public ViewDispenserInquiry (Keypad keypad, Screen screen) {
        super(keypad, screen);
    }
    
    public void showInformation (int cashCount) {
        screen.displayMessageLine("\nCash Dispenser Information:");
        screen.displayMessageLine(cashCount + " sheets of $20 bills");
    }
}
