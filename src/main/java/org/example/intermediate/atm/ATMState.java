package org.example.intermediate.atm;

/**
 * Enum for ATM states
 * Demonstrates STATE PATTERN transitions
 */
public enum ATMState {
    IDLE,              // Waiting for card insertion
    READING_CARD,      // Card inserted
    AUTHENTICATING,    // PIN verification
    SELECTING_TRANSACTION,  // User selecting operation
    WITHDRAWING,       // Processing withdrawal
    DEPOSITING,        // Processing deposit
    CHECKING_BALANCE,  // Retrieving balance
    RETURNING_CARD,    // Ejecting card
    OUT_OF_SERVICE     // Machine broken
}

