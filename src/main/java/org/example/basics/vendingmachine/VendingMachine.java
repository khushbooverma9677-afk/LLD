package org.example.basics.vendingmachine;

import org.example.utils.Logger;

/**
 * Main Vending Machine class implementing State Pattern
 * 
 * Key Features:
 * - Multiple states (IDLE, ACCEPTING_MONEY, DISPENSING, RETURNING_CHANGE)
 * - Product inventory management
 * - Payment handling
 * - Change calculation
 * 
 * Design Pattern: STATE PATTERN
 * The machine behavior changes based on its current state
 */
public class VendingMachine {
    private VendingMachineState currentState;
    private Product[] inventory;
    private double balance;
    private static final int MAX_PRODUCTS = 20;
    
    public VendingMachine() {
        this.currentState = VendingMachineState.IDLE;
        this.inventory = new Product[MAX_PRODUCTS];
        this.balance = 0.0;
        Logger.info("Vending Machine initialized in IDLE state");
    }
    
    public VendingMachineState getCurrentState() {
        return currentState;
    }
    
    public double getBalance() {
        return balance;
    }
    
    /**
     * Load product into the vending machine
     */
    public void loadProduct(String code, String name, double price, int quantity) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                inventory[i] = new Product(code, name, price, quantity);
                Logger.info("Loaded product: " + name + " at position " + i);
                return;
            }
        }
        Logger.error("Vending machine is full!");
    }
    
    /**
     * Insert money into the machine
     */
    public void insertMoney(double amount) {
        if (currentState == VendingMachineState.OUT_OF_ORDER) {
            Logger.error("Machine is out of order!");
            return;
        }
        
        currentState = VendingMachineState.ACCEPTING_MONEY;
        balance += amount;
        Logger.info("Money inserted: $" + amount + ". Current balance: $" + balance);
    }
    
    /**
     * Select a product
     */
    public void selectProduct(String code) {
        if (currentState == VendingMachineState.OUT_OF_ORDER) {
            Logger.error("Machine is out of order!");
            return;
        }
        
        if (currentState != VendingMachineState.ACCEPTING_MONEY && currentState != VendingMachineState.IDLE) {
            Logger.error("Invalid state for product selection!");
            return;
        }
        
        // Find product
        Product product = findProduct(code);
        if (product == null) {
            Logger.error("Product not found: " + code);
            return;
        }
        
        if (!product.isAvailable()) {
            Logger.error("Product out of stock: " + product.getName());
            return;
        }
        
        if (balance < product.getPrice()) {
            Logger.error("Insufficient balance. Need: $" + product.getPrice() + ", Have: $" + balance);
            return;
        }
        
        // Dispense product
        dispenseProduct(product);
    }
    
    /**
     * Dispense the selected product
     */
    private void dispenseProduct(Product product) {
        currentState = VendingMachineState.DISPENSING;
        Logger.info("Dispensing: " + product.getName());
        
        product.decreaseQuantity();
        balance -= product.getPrice();
        
        Logger.success(product.getName() + " dispensed!");
        
        // Return change if any
        if (balance > 0) {
            returnChange();
        } else {
            currentState = VendingMachineState.IDLE;
        }
    }
    
    /**
     * Return change to the user
     */
    private void returnChange() {
        currentState = VendingMachineState.RETURNING_CHANGE;
        Logger.info("Returning change: $" + String.format("%.2f", balance));
        balance = 0.0;
        currentState = VendingMachineState.IDLE;
        Logger.success("Machine ready for next customer");
    }
    
    /**
     * Cancel transaction and return balance
     */
    public void cancelTransaction() {
        if (balance > 0) {
            Logger.info("Transaction cancelled. Returning: $" + String.format("%.2f", balance));
            balance = 0.0;
        }
        currentState = VendingMachineState.IDLE;
    }
    
    /**
     * Find product by code
     */
    private Product findProduct(String code) {
        for (Product product : inventory) {
            if (product != null && product.getCode().equals(code)) {
                return product;
            }
        }
        return null;
    }
    
    /**
     * Display all available products
     */
    public void displayProducts() {
        System.out.println("\n========== VENDING MACHINE PRODUCTS ==========");
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].isAvailable()) {
                System.out.println(i + ": " + inventory[i]);
            }
        }
        System.out.println("=============================================\n");
    }
    
    /**
     * Get machine status
     */
    public void displayStatus() {
        System.out.println("\n========== VENDING MACHINE STATUS ==========");
        System.out.println("State: " + currentState);
        System.out.println("Balance: $" + String.format("%.2f", balance));
        System.out.println("===========================================\n");
    }
}

