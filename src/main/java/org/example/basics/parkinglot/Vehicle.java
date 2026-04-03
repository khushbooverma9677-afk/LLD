package org.example.basics.parkinglot;

import org.example.utils.BaseEntity;

/**
 * Vehicle class representing a vehicle in the parking system
 */
public class Vehicle extends BaseEntity {
    private String licensePlate;
    private VehicleType type;
    
    public Vehicle(String licensePlate, VehicleType type) {
        super();
        this.licensePlate = licensePlate;
        this.type = type;
    }
    
    public String getLicensePlate() {
        return licensePlate;
    }
    
    public VehicleType getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", type=" + type +
                '}';
    }
}

