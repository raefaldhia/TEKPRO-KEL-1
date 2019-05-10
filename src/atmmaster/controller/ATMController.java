/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmmaster.controller;
import atmmaster.model.ATM;
import atmmaster.view.*;
/**
 *
 * @author Rayhan Azka  <rayhan.azka.tif418@polban.ac.id>
 */
public class ATMController {
    private ATM ATMModel;
    private ViewATM viewATM;
    private TransactionController transactionController;
    
    // constants corresponding to main menu options
    public static final int BALANCE_INQUIRY             = 1;
    public static final int WITHDRAWAL                  = 2;
    public static final int DEPOSIT                     = 3;
    public static final int CHANGE_PIN                  = 4;
    public static final int TRANSFER                    = 5;
    public static final int EXIT                        = 6;
    public static final int UNBLOCK                     = 7;
    public static final int DISPENSER_INQUIRY           = 8;
    public static final int ADD_DISPENSER               = 9;
    public static final int ADD_ACCOUNT                 = 10;
    public static final int PIN_STRICT_MODE             = 11;
    public static final int PIN_STRICT_COMBINATION_MODE = 12;
    public static final int SET_TANGGAL                 = 13;
    public static final int SET_WITHDRAWAL_LIMIT        = 14;
    public static final int SET_WITHDRAWAL_DAILY_LIMIT  = 15;
    
    public ATMController (ATM ATMModel) {
        this.ATMModel = ATMModel;
        transactionController = new TransactionController();
        viewATM = transactionController.getViewATM();
    }
    
    // start ATM
    public void run() {
        // welcome and authenticate user; perform transactions
        while (true) {
            // loop while user is not yet authenticated
            while (ATMModel.getUserAuthenticated() < BankDatabase.ACCOUNT_AVAILABLE) {
                viewATM.showWelcome();
                authenticateUser(); // authenticate user
            }

            if (ATMModel.getUserAuthenticated() == BankDatabase.ACCOUNT_NEED_RESET) {
                performResetPinOnly();
            } else {
                performTransactions(); // user is now authenticated
            }

            ATMModel.setUserAuthenticated(BankDatabase.ACCOUNT_NOTFOUND); // reset before next ATM session
            ATMModel.setCurrentAccountNumber(0); // reset before next ATM session
            viewATM.showGoodbye();
        }
    }

    // attempts to authenticate user against database
    private void authenticateUser() {
        int accountNumber = viewATM.showInsertAccNum(); // input account number

        if (ATMModel.getBankDatabase().isResetRequired(accountNumber) == true) {
            ATMModel.setUserAuthenticated(BankDatabase.ACCOUNT_NEED_RESET);
        } else {
            int pin = viewATM.showInsertPIN(); // input PIN
            
            // set userAuthenticated to boolean value returned by database
            ATMModel.setUserAuthenticated(ATMModel.getBankDatabase().authenticateUser(accountNumber, pin));    
        }

        // check whether authentication succeeded
        switch (ATMModel.getUserAuthenticated()) {
            case BankDatabase.ACCOUNT_NOTFOUND:
                viewATM.showInvalid();
                break;
            case BankDatabase.ACCOUNT_BLOCKED:
                viewATM.showAccBlocked();
                break;
            case BankDatabase.ACCOUNT_AVAILABLE:
            case BankDatabase.ACCOUNT_NEED_RESET:
                ATMModel.setCurrentAccountNumber(accountNumber); // save user's account #
                break;
        }
    }

    private void performResetPinOnly() {
        transactionController.changePin(ATMModel.isCombinationStrictPin(), ATMModel.isStrictHistoryPin(), ATMModel.getBankDatabase(), ATMModel.getCurrentAccountNumber());
        ATMModel.getBankDatabase().setResetRequired(ATMModel.getCurrentAccountNumber(), false);
    }

    // display the main menu and perform transactions
    private void performTransactions() {
        // local variable to store transaction currently being processed
        boolean userExited = false; // user has not chosen to exit

        // loop while user has not chosen option to exit system
        while (!userExited) {
            // show main menu and get user selection
            int mainMenuSelection = viewATM.displayMainMenu(ATMModel.getDate(), ATMModel.getBankDatabase().isAdmin(ATMModel.getCurrentAccountNumber()));

            // decide how to proceed based on user's menu selection
            switch (mainMenuSelection) {
                // user chose to perform one of three transaction types
                case BALANCE_INQUIRY:
                    transactionController.balanceInquiry(ATMModel.getCurrentAccountNumber(), ATMModel.getBankDatabase());
                    break;
                case WITHDRAWAL:
                    transactionController.withdrawal(ATMModel.getCurrentAccountNumber(), ATMModel.getBankDatabase(), ATMModel.getCashDispenser(), ATMModel.isIsWithdrawalLimited(), ATMModel.isIsDailyWithdrawalLimited(), ATMModel.getDate());
                    break;
                case DEPOSIT:
                    transactionController.deposit(ATMModel.getCurrentAccountNumber(), ATMModel.getDepositSlot(), ATMModel.getBankDatabase());
                    break;
                case CHANGE_PIN:
                    transactionController.changePin(ATMModel.isCombinationStrictPin(), ATMModel.isStrictHistoryPin(), ATMModel.getBankDatabase(), ATMModel.getCurrentAccountNumber());
                    break;
                case TRANSFER:
                    transactionController.transfer(ATMModel.getCurrentAccountNumber(), ATMModel.getBankDatabase());
                    break;
                case EXIT: // user chose to terminate session
                    viewATM.showExit();
                    userExited = true; // this ATM session should end
                    break;
                case UNBLOCK:
                    if (ATMModel.getBankDatabase().isAdmin(ATMModel.getCurrentAccountNumber())) {
                        transactionController.unblockAccount(ATMModel.getCurrentAccountNumber(), ATMModel.getBankDatabase());
                        break;
                    }
                case DISPENSER_INQUIRY:
                    if (ATMModel.getBankDatabase().isAdmin(ATMModel.getCurrentAccountNumber())) {
                        transactionController.dispenserInquiry(ATMModel.getCashDispenser());
                        break;
                    }
                case ADD_DISPENSER:
                    if (ATMModel.getBankDatabase().isAdmin(ATMModel.getCurrentAccountNumber())) {
                        transactionController.addCash(ATMModel.getCashDispenser());
                        break;
                    }
                case ADD_ACCOUNT:
                    if (ATMModel.getBankDatabase().isAdmin(ATMModel.getCurrentAccountNumber())) {
                        transactionController.addAccount(ATMModel.getBankDatabase());
                        break;
                    }
                case PIN_STRICT_MODE:
                case PIN_STRICT_COMBINATION_MODE:
                case SET_TANGGAL:
                case SET_WITHDRAWAL_LIMIT:
                case SET_WITHDRAWAL_DAILY_LIMIT:
                    if (ATMModel.getBankDatabase().isAdmin(ATMModel.getCurrentAccountNumber())) {
                        displayATMMenu(mainMenuSelection);
                        break;
                    }
                default: //
                    viewATM.showInvalidSelection();
                    break;
            }
        }
    }

    private void displayATMMenu(int type) {
        int input;
        switch (type) {
            case PIN_STRICT_MODE:
                do {
                    input = viewATM.showPinStrictMode();
                } while (!ATMModel.getStateValidator().isValid(input));
                ATMModel.setStrictHistoryPin((input == 1) ? (true) : (false));

                break;
            case PIN_STRICT_COMBINATION_MODE:
                do {
                    input = viewATM.showPinStrictCombinationMode();
                } while (!ATMModel.getStateValidator().isValid(input));
                ATMModel.setCombinationStrictPin((input == 1) ? (true) : (false));

                break;
            case SET_TANGGAL:
                do {
                    input = viewATM.showSetDate();
                } while (!ATMModel.getTanggalValidator().isValid(input));

                if (input != 0) {
                    ATMModel.setDate(input);
                    viewATM.showDateChanged();
                }
                break;
            case SET_WITHDRAWAL_LIMIT:
                do {
                    input = viewATM.showSetWithdrawalLimit();
                } while (!ATMModel.getStateValidator().isValid(input));
                ATMModel.setIsWithdrawalLimited((input == 1) ? (true) : (false));

                break;
            case SET_WITHDRAWAL_DAILY_LIMIT:
                do {
                    input = viewATM.showSetWithdrawalDailyLimit();
                } while (!ATMModel.getStateValidator().isValid(input));
                ATMModel.setIsDailyWithdrawalLimited((input == 1) ? (true) : (false));
                break;
        }
    }
}
