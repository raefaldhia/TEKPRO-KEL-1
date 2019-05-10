/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmmaster.controller;
import atmmaster.controller.BankDatabase;
import atmmaster.view.*;

/**
 *
 * @author Rayhan Azka  <rayhan.azka.tif418@polban.ac.id>
 */
public class TransactionController {
    private ViewBalance viewBalance;
    private ViewWithdrawal viewWithdrawal;
    private ViewTransfer viewTransfer;
    private ViewAddAccount viewAddAccount;
    private ViewUnblockAccount viewUnblockAccount; 
    private ViewAddCash viewAddCash;
    private ViewDispenserInquiry viewDispenserInquiry;
    private ViewDeposit viewDeposit;
    private ViewChangePin viewChangePin;
    private ViewATM viewATM;
    private Keypad keypad;
    private Screen screen;
    
    public static final int CANCELED = 0;
    public TransactionController () {
        screen = new Screen(); // create screen
        keypad = new Keypad(); // create keypad
        viewBalance = new ViewBalance(keypad,screen);
        viewWithdrawal = new ViewWithdrawal (keypad,screen);
        viewTransfer = new ViewTransfer(keypad, screen);
        viewAddAccount = new ViewAddAccount(keypad, screen);
        viewUnblockAccount = new ViewUnblockAccount(keypad, screen);
        viewAddCash = new ViewAddCash(keypad, screen);
        viewDispenserInquiry = new ViewDispenserInquiry(keypad, screen);
        viewDeposit = new ViewDeposit(keypad, screen);
        viewChangePin = new ViewChangePin(keypad, screen);
        viewATM = new ViewATM(keypad, screen);
        
    }
    
    
    
    public void balanceInquiry (int accountNumber, BankDatabase bankDatabase) {
      // get the available balance for the account involved
      double availableBalance = 
         bankDatabase.getAvailableBalance(accountNumber);

      // get the total balance for the account involved
      double totalBalance = 
         bankDatabase.getTotalBalance(accountNumber);
      
      viewBalance.showBalance(availableBalance, totalBalance);
    }
    
    public void withdrawal (int accountNumber, BankDatabase bankDatabase , CashDispenser cashDispenser ,boolean isLimited, boolean isDailyLimited, int date) {
        int amount = viewWithdrawal.displayMenuOfAmounts(isLimited, isDailyLimited);
      
      if (amount == CANCELED) {
         viewWithdrawal.showCanceled();
      } else if (bankDatabase.getAvailableBalance(accountNumber) < amount) {
         viewWithdrawal.showInsufficient();
      } else if (cashDispenser.isSufficientCashAvailable(amount) == false) {
         // TODO
      } else if (isDailyLimited && (bankDatabase.getDailyWithdrawal(date, accountNumber) + amount > 1000.00)) {
         viewWithdrawal.showLimit1000();
      } else {
         bankDatabase.withdraw(accountNumber, date, amount);
         cashDispenser.dispenseCash(amount);
         viewWithdrawal.showSucceeded();
      }
    }
    
    public void transfer (int accountNumber, BankDatabase bankDatabase) {
        int receiver = viewTransfer.performGetReceiver();
        double amount = viewTransfer.performGetAmount();
        if (amount == CANCELED) {
            viewTransfer.showCanceled();
        } else if (amount > 100.00) {
            viewTransfer.showTransferLimit();
        } else if (bankDatabase.getAvailableBalance(accountNumber) < amount) {
            viewTransfer.showInsufficient();
        } else if (bankDatabase.transfer(accountNumber, receiver, amount)) {
            viewTransfer.showSucceeded();
        } else {
            viewTransfer.showFailed();
        }
    }
    
    public void addAccount (BankDatabase bankDatabase) {
        viewAddAccount.showAccountForm();
        int accountNumber = viewAddAccount.showIDForm();
        int balance = viewAddAccount.showBalanceForm();

        if (bankDatabase.addAccount(accountNumber, balance)) {
            viewAddAccount.showSucceded();
        } else {
            viewAddAccount.showFailed(accountNumber);
        }
    }
    
    public void unblockAccount (int accountNumber, BankDatabase bankDatabase) {
        int targetAccountId = viewUnblockAccount.showInsertAccNum();
        if (bankDatabase.unblock(accountNumber, targetAccountId)) {
            viewUnblockAccount.showBlocked(targetAccountId);
        } else {
            viewUnblockAccount.showNotBlocked(targetAccountId);
        }
    }
    
    public void addCash (CashDispenser cashDispenser) {
        int count;
        do {
            count = viewAddCash.showInputAmount();
        } while (count < CANCELED);
        if (count == CANCELED) {
            viewAddCash.showCancel();
        } else {
            cashDispenser.addCashCount(count);
            viewAddCash.showSucceeded();
        }
    }
    
    public void dispenserInquiry (CashDispenser cashDispenser) {
        int cashCount = cashDispenser.getCashCount();
        viewDispenserInquiry.showInformation(cashCount);
    }
    
    public void deposit (int accountNumber, DepositSlot depositSlot, BankDatabase bankDatabase) {
        double amount = viewDeposit.promptForDepositAmount();
        if (amount == CANCELED) {
            viewDeposit.showCanceled();
        } else {
            viewDeposit.showInsertDeposit(amount);
            if (depositSlot.isEnvelopeReceived() == true) {
                viewDeposit.showEnvelopeReceived();
                bankDatabase.credit(accountNumber, amount);
            } else {
                // TODO:
            }
        }
    }
    
    public void changePin (boolean combinationStrict, boolean strict, BankDatabase bankDatabase, int accountNumber) {
        outer:
        while (true) {
            int pin = viewChangePin.showInsertPIN();
            if (combinationStrict) {
                boolean digitAvailability[] = new boolean[10];
                String strPin = Integer.toString(pin);
                for (int i = 0; i < strPin.length(); i++) {
                    if (digitAvailability[strPin.charAt(i) - '0'] == false) {
                        digitAvailability[strPin.charAt(i) - '0'] = true;
                    } else {
                        viewChangePin.showContainSameDigit();
                        continue outer;
                    }
                }
            }

            if (strict) {
                for (Integer oldPin : bankDatabase.getPinLog(accountNumber)) {
                    if (oldPin.intValue() == pin) {
                        viewChangePin.showPINSameAsOldOne();
                        continue outer;
                    }
                }
            }
            bankDatabase.setAccountPIN(accountNumber, pin);
            viewChangePin.showPinChanged();
            break;
        }
    }

    /**
     * @return the viewATM
     */
    public ViewATM getViewATM() {
        return viewATM;
    }
}
