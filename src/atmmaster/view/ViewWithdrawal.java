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
public class ViewWithdrawal extends ViewBase {
    public ViewWithdrawal (Keypad keypad, Screen screen) {
        super(keypad, screen);
    }
    
    public void showCanceled () {
        screen.displayMessageLine("\nCanceling transaction...");
    }
    
    public void showInsufficient () {
        screen.displayMessageLine("\nInsufficient funds in your account. Please choose a smaller amount.");
    }
    
    public void showLimit1000 () {
        screen.displayMessageLine("\nUnable to complete the transaction, daily limit $1000.00 reached.");
    }
    
    public void showSucceeded () {
        screen.displayMessageLine("\nYour cash has been dispensed. Please take your cash now.");
    }
    
    // display a menu of withdrawal amounts and the option to cancel;
   // return the amount or 0 if the user chooses to cancel
   public int displayMenuOfAmounts(boolean isLimited, boolean isDailyLimited) {
      // array of amounts to correspond to menu numbers
      int[] amounts = {0, 20, 40, 60, 100, 200};

      // loop while no valid choice has been made
      while (true) {
         // display the withdrawal menu
         screen.displayMessageLine("\nWithdrawal Menu:");
         screen.displayMessageLine("1 - $20");
         screen.displayMessageLine("2 - $40");
         screen.displayMessageLine("3 - $60");
         screen.displayMessageLine("4 - $100");
         screen.displayMessageLine("5 - $200");
         screen.displayMessageLine("6 - Other");
         screen.displayMessageLine("7 - Cancel transaction");
         screen.displayMessage("\nChoose a withdrawal amount: ");

         int input = keypad.getInput(); // get user input through keypad
         // determine how to proceed based on the input value
         switch (input) {
            case 1: // if the user chose a withdrawal amount 
            case 2: // (i.e., chose option 1, 2, 3, 4 or 5), return the
            case 3: // corresponding amount from amounts array
            case 4:
            case 5:
               return amounts[input];
            case 6:
               screen.displayMessage("\n");
               do {
                  screen.displayMessage("Please specify the amount of withdrawal in CENTS (or 0 to cancel)");
                  if (isLimited) {
                     screen.displayMessage("[LIMIT: $300.00]");                     
                  }
                  screen.displayMessage(": ");
                  input = keypad.getInput();
               } while ((input < 0) || ((isLimited) ? (input > 30000) : (false)));
               
               return (input / 100);
            case 7: // the user chose to cancel
               return 0;
            default: // the user did not enter a value from 1-6
               screen.displayMessageLine(
                  "\nInvalid selection. Try again.");
         } 
      }
   }
}
