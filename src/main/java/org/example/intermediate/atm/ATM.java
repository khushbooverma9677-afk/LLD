package org.example.intermediate.atm;

import org.example.utils.Logger;

/**
 * ATM (Automated Teller Machine) System
 * 
 * Key Features:
 * - Multiple states (IDLE, AUTHENTICATING, WITHDRAWING, etc.)
 * - Card insertion and PIN verification
 * - Withdraw and deposit operations
 * - Balance inquiry
 * - Transaction logging
 * 
 * Design Pattern: STATE PATTERN
 * The machine behavior changes based on its current state
 */
public class ATM {
    private static ATM instance;
    private ATMState currentState;
    private BankAccount currentAccount;
    private String insertedCard;
    private double balance;
    private static final double WITHDRAW_LIMIT = 10000.0;
    private static final int PIN_ATTEMPTS = 3;
    private int attemptCount;
    
    private ATM() {
        this.currentState = ATMState.IDLE;
        this.currentAccount = null;
        this.insertedCard = null;
        this.balance = 0.0;
        this.attemptCount = 0;
        Logger.info("ATM initialized in IDLE state");
    }
    
    /**
     * Singleton pattern
     */
    public static ATM getInstance() {
        if (instance == null) {
            synchronized (ATM.class) {
                if (instance == null) {
                    instance = new ATM();
                }
            }
        }
        return instance;
    }
    
    /**
     * Insert card into ATM
     */
    public void insertCard(String cardNumber) {
        if (currentState != ATMState.IDLE) {
            Logger.error("Invalid state for card insertion");
            return;
        }
        
        this.insertedCard = cardNumber;
        currentState = ATMState.READING_CARD;
        Logger.info("Card inserted: " + cardNumber.substring(0, 4) + "***");
    }
    
    /**
     * Authenticate with PIN
     */
    public boolean authenticate(String pin) {
        if (currentState != ATMState.READING_CARD) {
            Logger.error("Invalid state for authentication");
            return false;
        }
        
        currentState = ATMState.AUTHENTICATING;
        Bank bank = Bank.getInstance();
        
        BankAccount account = bank.authenticate(insertedCard, pin);
        
        if (account != null) {
            currentAccount = account;
            this.balance = account.getBalance();
            attemptCount = 0;
            currentState = ATMState.SELECTING_TRANSACTION;
            Logger.success("Authentication successful!");
            return true;
        }
        
        attemptCount++;
        if (attemptCount >= PIN_ATTEMPTS) {
            currentState = ATMState.RETURNING_CARD;
            Logger.error("Card blocked after " + PIN_ATTEMPTS + " failed attempts");
            ejectCard();
        } else {
            currentState = ATMState.READING_CARD;
            Logger.error("Invalid PIN. Attempts remaining: " + (PIN_ATTEMPTS - attemptCount));
        }
        return false;
    }
    
    /**
     * Withdraw amount from account
     */
    public boolean withdraw(double amount) {
        if (currentState != ATMState.SELECTING_TRANSACTION) {
            Logger.error("Invalid state for withdrawal");
            return false;
        }
        
        if (amount <= 0 || amount > WITHDRAW_LIMIT) {
            Logger.error("Invalid withdrawal amount. Limit: $" + WITHDRAW_LIMIT);
            return false;
        }
        
        if (amount > balance) {
            Logger.error("Insufficient balance. Available: $" + balance);
            return false;
        }
        
        currentState = ATMState.WITHDRAWING;
        Bank bank = Bank.getInstance();
        
        if (bank.withdraw(currentAccount, amount)) {
            balance -= amount;
            Logger.success("Withdrawal successful! Amount: $" + amount);
            Logger.info("Remaining balance: $" + balance);
            currentState = ATMState.SELECTING_TRANSACTION;
            return true;
        }
        
        Logger.error("Withdrawal failed");
        currentState = ATMState.SELECTING_TRANSACTION;
        return false;
    }
    
    /**
     * Deposit amount to account
     */
    public boolean deposit(double amount) {
        if (currentState != ATMState.SELECTING_TRANSACTION) {
            Logger.error("Invalid state for deposit");
            return false;
        }
        
        if (amount <= 0) {
            Logger.error("Invalid deposit amount");
            return false;
        }
        
        currentState = ATMState.DEPOSITING;
        Bank bank = Bank.getInstance();
        
        if (bank.deposit(currentAccount, amount)) {
            balance += amount;
            Logger.success("Deposit successful! Amount: $" + amount);
            Logger.info("Updated balance: $" + balance);
            currentState = ATMState.SELECTING_TRANSACTION;
            return true;
        }
        
        Logger.error("Deposit failed");
        currentState = ATMState.SELECTING_TRANSACTION;
        return false;
    }
    
    /**
     * Check account balance
     */
    public void checkBalance() {
        if (currentState != ATMState.SELECTING_TRANSACTION) {
            Logger.error("Invalid state for balance inquiry");
            return;
        }
        
        currentState = ATMState.CHECKING_BALANCE;
        Logger.info("Account: " + currentAccount.getAccountNumber());
        Logger.info("Holder: " + currentAccount.getAccountHolder());
        Logger.info("Balance: $" + currentAccount.getBalance());
        currentState = ATMState.SELECTING_TRANSACTION;
    }
    
    /**
     * Eject card
     */
    public void ejectCard() {
        if (currentState == ATMState.IDLE) {
            Logger.warn("No card to eject");
            return;
        }
        
        currentState = ATMState.RETURNING_CARD;
        Logger.info("Card ejected: " + insertedCard.substring(0, 4) + "***");
        
        // Reset state
        currentAccount = null;
        insertedCard = null;
        balance = 0.0;
        attemptCount = 0;
        currentState = ATMState.IDLE;
    }
    
    /**
     * Display ATM status
     */
    public void displayStatus() {
        System.out.println("\n========== ATM STATUS ==========");
        System.out.println("Current State: " + currentState);
        if (currentAccount != null) {
            System.out.println("Account: " + currentAccount.getAccountNumber());
            System.out.println("Balance: $" + currentAccount.getBalance());
        }
        System.out.println("================================\n");
    }
    
    public ATMState getCurrentState() {
        return currentState;
    }
    
    public BankAccount getCurrentAccount() {
        return currentAccount;
    }
}

