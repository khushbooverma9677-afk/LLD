package org.example.basics.parkinglot;

/**
 * Enum for different vehicle types in the parking lot
 */
public enum VehicleType {
    BIKE(1),
    CAR(1),
    TRUCK(2);
    
    private final int spotsRequired;
    
    VehicleType(int spotsRequired) {
        this.spotsRequired = spotsRequired;
    }
    
    public int getSpotsRequired() {
        return spotsRequired;
    }
}

