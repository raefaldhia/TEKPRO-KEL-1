package atmmaster.model;
import java.util.ArrayList;

public class Account implements Comparable<Account> {
   private int accountNumber; // account number
   private int pin; // PIN for authentication
   private double availableBalance; // funds available for withdrawal
   private double totalBalance; // funds available & pending deposits
   private int tryCount;
   private boolean admin;
   private ArrayList<Integer> pinLog;
   private double dailyWithdrawal[];

   // Account constructor initializes attributes
   public Account(int theAccountNumber, int thePIN, 
      double theAvailableBalance, double theTotalBalance, boolean _isAdmin) {
      pinLog = new ArrayList<Integer>();
      pinLog.add(new Integer(thePIN));
      accountNumber = theAccountNumber;

      availableBalance = theAvailableBalance;
      totalBalance = theTotalBalance;
      tryCount = 0;
      admin = _isAdmin;
      dailyWithdrawal = new double[31];

      setPin(thePIN);
   }

   public Account(int theAccountNumber, double theBalance) {
      pinLog = new ArrayList<Integer>();
      accountNumber = theAccountNumber;
      setPin(0);
      availableBalance = theBalance;
      totalBalance = theBalance;
      tryCount = 0;
      admin = false;
      dailyWithdrawal = new double[31];
   }
   
   // returns available balance
   public double getAvailableBalance() {
      return availableBalance;
   } 

   // returns the total balance
   public double getTotalBalance() {
      return totalBalance;
   }
   
   public int getAccountNumber() {
      return accountNumber;  
   }

   public ArrayList<Integer> getPinLog() {
      return pinLog;
   }

   public void setPin(int newPin) {
      this.pin = newPin;
   }

   public int getPin() {
      return pin;
   }

   public boolean isAdmin() {
      return admin;
   }
   
    public void setAdmin(boolean admin) {
      this.admin = admin;
   }

   public boolean isResetRequired() {
      return (pin == 0);
   }

   public void setResetRequired() {
      pin = 0;
   }
   
   public void setTotalBalance(double totalBalance) {
      this.totalBalance = totalBalance;
   }
   
   public void setAvailableBalance(double availableBalance) {
      this.availableBalance = availableBalance;
   }
   
   public int getTryCount() {
      return tryCount;
   }
    
   public void setTryCount(int tryCount) {
      this.tryCount = tryCount;
   }
   
   public double[] getDailyWithdrawal() {
      return dailyWithdrawal;
   }

   public void setDailyWithdrawal(double[] dailyWithdrawal) {
      this.dailyWithdrawal = dailyWithdrawal;
   }
   
   public boolean isBlocked () {
       return (tryCount >= 3);
   }
   
   public void unblock() {
      tryCount = 0;
   }

    @Override
    public int compareTo(Account t) {
        return accountNumber - t.accountNumber;
    }
}