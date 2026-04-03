package org.example.basics.parkinglot;

import org.example.utils.Logger;

/**
 * ParkingLot is the main class that manages the entire parking system
 * Uses Singleton pattern to ensure only one instance exists
 * 
 * Key Features:
 * - Multiple parking levels
 * - Park and unpark vehicles
 * - Display availability
 * - Track parked vehicles
 */
public class ParkingLot {
    private static ParkingLot instance;
    private ParkingLevel[] levels;
    
    private ParkingLot(int numLevels, int spotsPerLevel) {
        this.levels = new ParkingLevel[numLevels];
        for (int i = 0; i < numLevels; i++) {
            levels[i] = new ParkingLevel(i, spotsPerLevel);
        }
        Logger.info("ParkingLot initialized with " + numLevels + " levels and " + spotsPerLevel + " spots per level");
    }
    
    /**
     * Singleton pattern - get instance
     */
    public static ParkingLot getInstance(int numLevels, int spotsPerLevel) {
        if (instance == null) {
            synchronized (ParkingLot.class) {
                if (instance == null) {
                    instance = new ParkingLot(numLevels, spotsPerLevel);
                }
            }
        }
        return instance;
    }
    
    /**
     * Get existing instance
     */
    public static ParkingLot getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ParkingLot not initialized. Call getInstance(numLevels, spotsPerLevel) first.");
        }
        return instance;
    }
    
    /**
     * Park a vehicle in the parking lot
     */
    public synchronized boolean parkVehicle(Vehicle vehicle) {
        Logger.info("Attempting to park vehicle: " + vehicle.getLicensePlate());
        
        // Try to park on each level
        for (ParkingLevel level : levels) {
            if (level.parkVehicle(vehicle)) {
                Logger.success("Vehicle parked successfully: " + vehicle.getLicensePlate());
                return true;
            }
        }
        
        Logger.warn("No available spots for vehicle: " + vehicle.getLicensePlate());
        return false;
    }
    
    /**
     * Unpark a vehicle from a specific level and spot
     */
    public synchronized boolean unparkVehicle(int levelNumber, int spotNumber) {
        if (levelNumber < 0 || levelNumber >= levels.length) {
            Logger.error("Invalid level number: " + levelNumber);
            return false;
        }
        
        boolean result = levels[levelNumber].unparkVehicle(spotNumber);
        if (result) {
            Logger.success("Vehicle unparked from level " + levelNumber + ", spot " + spotNumber);
        } else {
            Logger.error("Failed to unpark vehicle from level " + levelNumber + ", spot " + spotNumber);
        }
        return result;
    }
    
    /**
     * Get total available spots in the entire parking lot
     */
    public int getTotalAvailableSpots() {
        int total = 0;
        for (ParkingLevel level : levels) {
            total += level.getAvailableSpots();
        }
        return total;
    }
    
    /**
     * Get total capacity of the parking lot
     */
    public int getTotalCapacity() {
        int total = 0;
        for (ParkingLevel level : levels) {
            total += level.getTotalSpots();
        }
        return total;
    }
    
    /**
     * Display parking lot status
     */
    public void displayStatus() {
        System.out.println("\n========== PARKING LOT STATUS ==========");
        System.out.println("Total Capacity: " + getTotalCapacity());
        System.out.println("Available Spots: " + getTotalAvailableSpots());
        System.out.println("Occupied Spots: " + (getTotalCapacity() - getTotalAvailableSpots()));
        System.out.println("========================================\n");
        
        for (ParkingLevel level : levels) {
            level.displayStatus();
        }
    }
}

