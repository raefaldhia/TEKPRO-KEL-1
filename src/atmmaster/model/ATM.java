package atmmaster.model;

import atmmaster.controller.CashDispenser;
import atmmaster.controller.DepositSlot;
import atmmaster.controller.RangeValidation;
import atmmaster.view.*;
import atmmaster.controller.BankDatabase;

public class ATM {
    private int date;
    private RangeValidation tanggalValidator;
    private RangeValidation stateValidator;

    private int userAuthenticated; // whether user is authenticated
    private int currentAccountNumber; // current user's account number
    private CashDispenser cashDispenser; // ATM's cash dispenser
    private DepositSlot depositSlot;
    private BankDatabase bankDatabase; // account information database

    private boolean strictHistoryPin;
    private boolean combinationStrictPin;
    private boolean isWithdrawalLimited;
    private boolean isDailyWithdrawalLimited;

    // no-argument ATM constructor initializes instance variables
    public ATM() {
        userAuthenticated = BankDatabase.ACCOUNT_NOTFOUND; // user is not authenticated to start
        currentAccountNumber = 0; // no current account number to start
        
        cashDispenser = new CashDispenser(); // create cash dispenser
        bankDatabase = new BankDatabase(); // create acct info database
        depositSlot = new DepositSlot();

        strictHistoryPin = true;
        date = 10;
        tanggalValidator = new RangeValidation(0, 31);
        stateValidator = new RangeValidation(0, 1);

        isWithdrawalLimited = true;
        isDailyWithdrawalLimited = true;
        combinationStrictPin = true;
    }

    /**
     * @return the date
     */
    public int getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * @return the tanggalValidator
     */
    public RangeValidation getTanggalValidator() {
        return tanggalValidator;
    }

    /**
     * @param tanggalValidator the tanggalValidator to set
     */
    public void setTanggalValidator(RangeValidation tanggalValidator) {
        this.tanggalValidator = tanggalValidator;
    }

    /**
     * @return the stateValidator
     */
    public RangeValidation getStateValidator() {
        return stateValidator;
    }

    /**
     * @param stateValidator the stateValidator to set
     */
    public void setStateValidator(RangeValidation stateValidator) {
        this.stateValidator = stateValidator;
    }

    /**
     * @return the userAuthenticated
     */
    public int getUserAuthenticated() {
        return userAuthenticated;
    }

    /**
     * @param userAuthenticated the userAuthenticated to set
     */
    public void setUserAuthenticated(int userAuthenticated) {
        this.userAuthenticated = userAuthenticated;
    }

    /**
     * @return the currentAccountNumber
     */
    public int getCurrentAccountNumber() {
        return currentAccountNumber;
    }

    /**
     * @param currentAccountNumber the currentAccountNumber to set
     */
    public void setCurrentAccountNumber(int currentAccountNumber) {
        this.currentAccountNumber = currentAccountNumber;
    }

    /**
     * @return the cashDispenser
     */
    public CashDispenser getCashDispenser() {
        return cashDispenser;
    }

    /**
     * @param cashDispenser the cashDispenser to set
     */
    public void setCashDispenser(CashDispenser cashDispenser) {
        this.cashDispenser = cashDispenser;
    }

    /**
     * @return the depositSlot
     */
    public DepositSlot getDepositSlot() {
        return depositSlot;
    }

    /**
     * @param depositSlot the depositSlot to set
     */
    public void setDepositSlot(DepositSlot depositSlot) {
        this.depositSlot = depositSlot;
    }

    /**
     * @return the bankDatabase
     */
    public BankDatabase getBankDatabase() {
        return bankDatabase;
    }

    /**
     * @param bankDatabase the bankDatabase to set
     */
    public void setBankDatabase(BankDatabase bankDatabase) {
        this.bankDatabase = bankDatabase;
    }

    /**
     * @return the strictHistoryPin
     */
    public boolean isStrictHistoryPin() {
        return strictHistoryPin;
    }

    /**
     * @param strictHistoryPin the strictHistoryPin to set
     */
    public void setStrictHistoryPin(boolean strictHistoryPin) {
        this.strictHistoryPin = strictHistoryPin;
    }

    /**
     * @return the combinationStrictPin
     */
    public boolean isCombinationStrictPin() {
        return combinationStrictPin;
    }

    /**
     * @param combinationStrictPin the combinationStrictPin to set
     */
    public void setCombinationStrictPin(boolean combinationStrictPin) {
        this.combinationStrictPin = combinationStrictPin;
    }

    /**
     * @return the isWithdrawalLimited
     */
    public boolean isIsWithdrawalLimited() {
        return isWithdrawalLimited;
    }

    /**
     * @param isWithdrawalLimited the isWithdrawalLimited to set
     */
    public void setIsWithdrawalLimited(boolean isWithdrawalLimited) {
        this.isWithdrawalLimited = isWithdrawalLimited;
    }

    /**
     * @return the isDailyWithdrawalLimited
     */
    public boolean isIsDailyWithdrawalLimited() {
        return isDailyWithdrawalLimited;
    }

    /**
     * @param isDailyWithdrawalLimited the isDailyWithdrawalLimited to set
     */
    public void setIsDailyWithdrawalLimited(boolean isDailyWithdrawalLimited) {
        this.isDailyWithdrawalLimited = isDailyWithdrawalLimited;
    }
}