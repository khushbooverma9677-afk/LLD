package org.example.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Simple logger utility for debugging and logging during interviews
 */
public class Logger {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public static void info(String message) {
        System.out.println("[INFO] " + getCurrentTime() + " - " + message);
    }
    
    public static void debug(String message) {
        System.out.println("[DEBUG] " + getCurrentTime() + " - " + message);
    }
    
    public static void error(String message) {
        System.err.println("[ERROR] " + getCurrentTime() + " - " + message);
    }
    
    public static void warn(String message) {
        System.out.println("[WARN] " + getCurrentTime() + " - " + message);
    }
    
    public static void success(String message) {
        System.out.println("[SUCCESS] " + getCurrentTime() + " - " + message);
    }
    
    private static String getCurrentTime() {
        return LocalDateTime.now().format(FORMATTER);
    }
}

