package org.example.basics.vendingmachine;

/**
 * Enum for different states of the vending machine
 * This demonstrates the State Pattern
 */
public enum VendingMachineState {
    IDLE,           // Waiting for user input
    ACCEPTING_MONEY, // Accepting coins/notes
    DISPENSING,     // Dispensing product
    RETURNING_CHANGE, // Returning change
    OUT_OF_ORDER    // Machine is broken
}

