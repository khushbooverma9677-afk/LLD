package org.example.basics.parkinglot;

import org.example.utils.BaseEntity;

/**
 * ParkingSpot represents a single spot in the parking lot
 * Each spot can be occupied by a vehicle or empty
 */
public class ParkingSpot extends BaseEntity {
    private int spotNumber;
    private Vehicle parkedVehicle;
    private boolean isAvailable;
    private ParkingLevel level;
    
    public ParkingSpot(int spotNumber, ParkingLevel level) {
        super();
        this.spotNumber = spotNumber;
        this.level = level;
        this.isAvailable = true;
        this.parkedVehicle = null;
    }
    
    public int getSpotNumber() {
        return spotNumber;
    }
    
    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public ParkingLevel getLevel() {
        return level;
    }
    
    /**
     * Park a vehicle in this spot
     */
    public synchronized boolean parkVehicle(Vehicle vehicle) {
        if (!isAvailable) {
            return false;
        }
        this.parkedVehicle = vehicle;
        this.isAvailable = false;
        return true;
    }
    
    /**
     * Remove vehicle from this spot
     */
    public synchronized Vehicle unParkVehicle() {
        if (isAvailable) {
            return null;
        }
        Vehicle vehicle = this.parkedVehicle;
        this.parkedVehicle = null;
        this.isAvailable = true;
        return vehicle;
    }
    
    @Override
    public String toString() {
        return "ParkingSpot{" +
                "spotNumber=" + spotNumber +
                ", isAvailable=" + isAvailable +
                ", parkedVehicle=" + (parkedVehicle != null ? parkedVehicle.getLicensePlate() : "EMPTY") +
                '}';
    }
}

