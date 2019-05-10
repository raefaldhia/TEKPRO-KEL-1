package atmmaster.controller;
import atmmaster.model.Account;
import java.util.*;

public class BankDatabase {
    //private Account[] accounts; // array of Accounts
    //private ArrayList<Account> accounts;
    private HashSet<Account> accounts;
    public static final int ACCOUNT_NOTFOUND  = 0;
    public static final int ACCOUNT_BLOCKED   = 1;
    public static final int ACCOUNT_AVAILABLE = 2;
    public static final int ACCOUNT_NEED_RESET = 3;

    public BankDatabase() {
        accounts = new HashSet<Account>();

        accounts.add(new Account(12345, 54321, 2000.0, 2200.0, false));
        accounts.add(new Account(8765, 5678, 200.0, 200.0, false));
        accounts.add(new Account(0, 12345, 2000.0, 2000.0, true));
    }

    private Account getAccount(int accountNumber) {
        for (Account i : accounts) {
            if (i.getAccountNumber() == accountNumber) {
                return i;
            }
        }
        return null; // if no matching account was found, return null
    }

    public int authenticateUser(int userAccountNumber, int userPIN) {
        // attempt to retrieve the account with the account number
        Account userAccount = getAccount(userAccountNumber);
        // if account exists, return result of Account method validatePIN
        
        if (userAccount != null) {
            if (userAccount.getPin() == userPIN) {
                if (userAccount.isBlocked()) {
                    return ACCOUNT_BLOCKED;
                }
                if (userAccount.isResetRequired()) {
                    return ACCOUNT_NEED_RESET;
                }
                userAccount.setTryCount(0);
                return ACCOUNT_AVAILABLE;
            }
            userAccount.setTryCount(userAccount.getTryCount() + 1);
        }
        return ACCOUNT_NOTFOUND; // account number not found, so return false
    }

    public double getAvailableBalance(int userAccountNumber) {
        return getAccount(userAccountNumber).getAvailableBalance();
    }
    
    public void setAvailableBalance (int accountNumber, double availableBalance) {
        getAccount(accountNumber).setAvailableBalance(availableBalance);
    }

    public double getTotalBalance(int userAccountNumber) {
        return getAccount(userAccountNumber).getTotalBalance();
    }
    
    public void setTotalBalance (int accountNumber, double totalBalance) {
        getAccount(accountNumber).setTotalBalance(totalBalance);
    }

    public void credit(int userAccountNumber, double amount) {
        setAvailableBalance(userAccountNumber, getAvailableBalance(userAccountNumber) + amount);
        setTotalBalance(userAccountNumber, getTotalBalance(userAccountNumber) + amount);
        
    }

    public double getDailyWithdrawal(int date, int userAccountNumber) {
        return getAccount(userAccountNumber).getDailyWithdrawal()[date - 1];
    }
    
    public void withdraw(int userAccountNumber, int date, double amount) {
        addDailyWithdrawal(userAccountNumber, date, amount);
        debit(userAccountNumber, amount);
    }

    public void debit(int userAccountNumber, double amount) {
        setAvailableBalance(userAccountNumber, getAvailableBalance(userAccountNumber) - amount);
        setTotalBalance(userAccountNumber, getTotalBalance(userAccountNumber) - amount);
    }

    public void changePin(int userAccountNumber, int newPin) {
        getAccount(userAccountNumber).setPin(newPin);
    }

    public boolean transfer(int userAccountNumber, int receiverAccountNumber, double amount) {
        if (userAccountNumber == receiverAccountNumber ||
            getAccount(userAccountNumber) == null ||
            getAccount(receiverAccountNumber) == null) {
            return false;
        }
        debit(userAccountNumber, amount);
        credit(receiverAccountNumber, amount);
        return true;
    }

    public boolean isAdmin(int userAccountNumber) {
        return getAccount(userAccountNumber).isAdmin();
    }

    public boolean unblock(int userAccountNumber, int targetAccountNumber) {
        Account account = getAccount(targetAccountNumber);
        if (account != null) {
            account.unblock();
            return true;
        }
        return false;
    }

    public boolean addAccount(int theAccountNumber, double theBalance) {
        return accounts.add(new Account(theAccountNumber, theBalance / 100));
    }
    
    public boolean isResetRequired(int theAccountNumber) {
        Account account = getAccount(theAccountNumber);
        if (account != null) {
            return account.isResetRequired();
        }
        return false;
    }

    public void setResetRequired(int theAccountNumber, boolean state) {
        Account account = getAccount(theAccountNumber);
        account.setResetRequired();
    }

    public ArrayList<Integer> getPinLog(int theAccountNumber) {
        return getAccount(theAccountNumber).getPinLog();
    }
    
    public Account validatePIN (int accountNumber, int userPIN) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            if (userPIN == account.getPin()) {
                account.setTryCount(0);
                return account;
            }
            account.setTryCount(account.getTryCount() + 1);
            //return account;
        }
        return null;
    }
    
    public void setAccountPIN (int accountNumber, int pin) {
        Account account = getAccount(accountNumber);
        if (account.getPinLog().size() >= 3) {
            account.getPinLog().remove(0);
        }
        account.getPinLog().add(new Integer(pin));
        account.setPin(pin);
    }
    
    public void addDailyWithdrawal(int accountNumber, int date, double withdrawal) {
       getAccount(accountNumber).getDailyWithdrawal()[date-1] += withdrawal; 
   }
    
   
}