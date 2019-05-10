/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmmaster.view;

/**
 *
 * @author FATHARANI
 */
public class ViewATM extends ViewBase {
    public ViewATM(Keypad keypad, Screen screen) {
        super(keypad, screen);
    }
    
    public void showWelcome () {
        screen.displayMessageLine("\nWelcome!");
    }
    
    public void showGoodbye () {
        screen.displayMessageLine("\nThank you! Goodbye!");
    }
    
    public int showInsertAccNum () {
        screen.displayMessage("\nPlease enter your account number: ");
        return keypad.getInput(); // input account number
    }
    
    public int showInsertPIN () {
        screen.displayMessage("\nEnter your PIN: "); // prompt for PIN
        return keypad.getInput(); // input PIN
    }
    
    public void showInvalid () {
        screen.displayMessageLine(
                        "Invalid account number or PIN. Please try again.");
    }
    
    public void showAccBlocked () {
        screen.displayMessageLine("Your account is blocked.");
    }
    
    public void showExit () {
        screen.displayMessageLine("\nExiting the system...");
    }
    
    public void showInvalidSelection () {
        screen.displayMessageLine(
                            "\nYou did not enter a valid selection. Try again.");
    }
    
    // display the main menu and return an input selection
    public int displayMainMenu(int date, boolean isAdmin) {
        screen.displayMessageLine("\nCurrent date: " + date);
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Change pin");
        screen.displayMessageLine("5 - Transfer");
        screen.displayMessageLine("6 - Exit");

        if (isAdmin) {
            screen.displayMessageLine("\nAdmin Menu:");
            screen.displayMessageLine("7 - [Account] Unblock");
            screen.displayMessageLine("8 - [Cash Dispenser] View cash");
            screen.displayMessageLine("9 - [Cash Dispenser] Add cash");
            screen.displayMessageLine("10 - [Account] Add");
            screen.displayMessageLine("11 - [PIN] set strict history mode");
            screen.displayMessageLine("12 - [PIN] set strict combination mode");    
            screen.displayMessageLine("13 - [Date] set current date");
            screen.displayMessageLine("14 - [Withdrawal] set limit to $300.00");
            screen.displayMessageLine("15 - [Withdrawal] set daily limit $1000.00");
        }
        screen.displayMessage("\nEnter a choice: ");
        return keypad.getInput(); // return user's selection
    }
    
    public int showPinStrictMode () {
        screen.displayMessage("\n[PIN] Set strict history mode to [0/1]: ");
        return keypad.getInput();
    }
    
    public int showPinStrictCombinationMode () {
        screen.displayMessage("\n[PIN] Set strict combination mode to [0/1]: ");
        return keypad.getInput();
    }
    
    public int showSetDate () {
        screen.displayMessage("\n[Date] Set value (or 0 to cancel)[LIMIT: 1-31]: ");
        return keypad.getInput();
    }
    
    public void showDateChanged () {
        screen.displayMessageLine("\nTanggal berhasil diubah.");
    } 
    
    public int showSetWithdrawalLimit () {
        screen.displayMessage("\n[Withdrawal] Set limit $300.00 enabled [0/1]: ");
        return keypad.getInput();
    }
    
    public int showSetWithdrawalDailyLimit () {
        screen.displayMessage("\n[Withdrawal] Set daily limit $1000.00 enabled [0/1]: ");
        return keypad.getInput();
    }
}
