package org.example.utils;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Utility class to generate unique IDs
 * Uses UUID for random IDs and atomic long for sequential IDs
 */
public class IdGenerator {
    private static final AtomicLong sequentialCounter = new AtomicLong(0);
    
    /**
     * Generates a random unique ID using UUID
     */
    public static String generateId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
    
    /**
     * Generates a sequential unique ID
     */
    public static long generateSequentialId() {
        return sequentialCounter.incrementAndGet();
    }
    
    /**
     * Generates a prefixed ID
     */
    public static String generatePrefixedId(String prefix) {
        return prefix + "_" + generateSequentialId();
    }
    
    /**
     * Resets the sequential counter (useful for testing)
     */
    public static void reset() {
        sequentialCounter.set(0);
    }
}

