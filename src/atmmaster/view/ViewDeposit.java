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
public class ViewDeposit extends ViewBase {
    public ViewDeposit(Keypad keypad, Screen screen) {
        super(keypad, screen);
    }
    
    public void showCanceled () {
        screen.displayMessageLine("\nCanceling transaction...");
    }
    
    public void showInsertDeposit (double amount) {
        screen.displayMessage("\nPlease insert a deposit envelope containing ");
        screen.displayDollarAmount(amount);
        screen.displayMessageLine(".");
    }
    
    public void showEnvelopeReceived () {
        screen.displayMessageLine("Your envelope has been received.");
        screen.displayMessageLine("NOTE: The money just deposited will not be available until we verify the amount of any enclosed cash and your checks clear.");
    }
    
    // prompt user to enter a deposit amount in cents 
   public double promptForDepositAmount() {
      int input; // receive input of deposit amount
      screen.displayMessage("\n");
      do {
         // display the prompt
         screen.displayMessage("Please enter a deposit amount in " +
                 "CENTS (or 0 to cancel): ");

         input = keypad.getInput();
      } while (input < 0);

      return (double) input / 100; // return dollar amount
   }
}
