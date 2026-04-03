package org.example.intermediate.atm;

import java.util.HashMap;
import java.util.Map;

/**
 * Bank managing all accounts
 * Repository pattern for account management
 */
public class Bank {
    private static Bank instance;
    private Map<String, BankAccount> accounts;
    private Map<String, String> cardPINs; // Card number -> PIN
    
    private Bank() {
        this.accounts = new HashMap<>();
        this.cardPINs = new HashMap<>();
        initializeSampleAccounts();
    }
    
    public static Bank getInstance() {
        if (instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
    
    /**
     * Initialize sample accounts for testing
     */
    private void initializeSampleAccounts() {
        BankAccount account1 = new BankAccount("ACC-001", "John Doe", 5000.0);
        BankAccount account2 = new BankAccount("ACC-002", "Jane Smith", 7500.0);
        
        accounts.put("ACC-001", account1);
        accounts.put("ACC-002", account2);
        
        cardPINs.put("1234567890", "1111");
        cardPINs.put("0987654321", "2222");
    }
    
    /**
     * Authenticate card and PIN
     */
    public BankAccount authenticate(String cardNumber, String pin) {
        if (!cardPINs.containsKey(cardNumber)) {
            return null;
        }
        
        if (!cardPINs.get(cardNumber).equals(pin)) {
            return null;
        }
        
        // For demo, map card to account
        String accountNumber = cardNumber.equals("1234567890") ? "ACC-001" : "ACC-002";
        BankAccount account = accounts.get(accountNumber);
        
        if (account != null && account.isActive()) {
            return account;
        }
        return null;
    }
    
    /**
     * Withdraw from account
     */
    public synchronized boolean withdraw(BankAccount account, double amount) {
        return account.withdraw(amount);
    }
    
    /**
     * Deposit to account
     */
    public synchronized boolean deposit(BankAccount account, double amount) {
        return account.deposit(amount);
    }
}

