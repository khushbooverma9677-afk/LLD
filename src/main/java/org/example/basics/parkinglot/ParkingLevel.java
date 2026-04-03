package org.example.basics.parkinglot;

import org.example.utils.BaseEntity;

/**
 * ParkingLevel represents a level/floor in the parking lot
 * Contains multiple parking spots
 */
public class ParkingLevel extends BaseEntity {
    private int level;
    private ParkingSpot[] spots;
    private int availableSpots;
    
    public ParkingLevel(int level, int totalSpots) {
        super();
        this.level = level;
        this.spots = new ParkingSpot[totalSpots];
        this.availableSpots = totalSpots;
        
        // Initialize all spots
        for (int i = 0; i < totalSpots; i++) {
            spots[i] = new ParkingSpot(i, this);
        }
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getAvailableSpots() {
        return availableSpots;
    }
    
    public int getTotalSpots() {
        return spots.length;
    }
    
    /**
     * Find an available parking spot
     */
    public ParkingSpot findAvailableSpot() {
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable()) {
                return spot;
            }
        }
        return null;
    }
    
    /**
     * Park a vehicle on this level
     */
    public synchronized boolean parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = findAvailableSpot();
        if (spot == null) {
            return false;
        }
        if (spot.parkVehicle(vehicle)) {
            availableSpots--;
            return true;
        }
        return false;
    }
    
    /**
     * Unpark a vehicle from a specific spot
     */
    public synchronized boolean unparkVehicle(int spotNumber) {
        if (spotNumber < 0 || spotNumber >= spots.length) {
            return false;
        }
        if (spots[spotNumber].unParkVehicle() != null) {
            availableSpots++;
            return true;
        }
        return false;
    }
    
    /**
     * Display the level status
     */
    public void displayStatus() {
        System.out.println("=== Level " + level + " ===");
        for (ParkingSpot spot : spots) {
            System.out.println(spot);
        }
    }
}

