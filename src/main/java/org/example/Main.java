package org.example;

import org.example.basics.parkinglot.*;
import org.example.basics.vendingmachine.*;
import org.example.basics.librarymanagement.*;
import org.example.utils.Logger;

/**
 * Main class - Demo and Testing for LLD Interview Problems
 * 
 * This demonstrates:
 * 1. Parking Lot System (Singleton Pattern)
 * 2. Vending Machine (State Pattern)
 * 3. Library Management (Repository Pattern)
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("\n" +
                "╔═══════════════════════════════════════════════════╗\n" +
                "║     LLD Interview Practice - System Design        ║\n" +
                "║     Week 1-2: Basic Problems                      ║\n" +
                "╚═══════════════════════════════════════════════════╝\n");
        
        // Test Parking Lot System
        demoParkingLot();
        
        // Test Vending Machine
        demoVendingMachine();
        
        // Test Library Management
        demoLibraryManagement();
        
        System.out.println("\n" +
                "╔═══════════════════════════════════════════════════╗\n" +
                "║           All demos completed!                    ║\n" +
                "║  Next: Study intermediate problems (Week 3-4)     ║\n" +
                "╚═══════════════════════════════════════════════════╝\n");
    }
    
    /**
     * Demo 1: Parking Lot System
     */
    private static void demoParkingLot() {
        Logger.info("========== DEMO 1: PARKING LOT SYSTEM ==========");
        System.out.println("\nConcept: SINGLETON PATTERN");
        System.out.println("Design Patterns: Singleton, Enums");
        System.out.println("Key Topics: Thread-safety, State Management\n");
        
        // Initialize parking lot (2 levels, 3 spots each)
        ParkingLot parkingLot = ParkingLot.getInstance(2, 3);
        
        // Create and park vehicles
        Vehicle car1 = new Vehicle("ABC-123", VehicleType.CAR);
        Vehicle car2 = new Vehicle("XYZ-789", VehicleType.CAR);
        Vehicle truck = new Vehicle("TRK-001", VehicleType.TRUCK);
        Vehicle bike = new Vehicle("BIKE-42", VehicleType.BIKE);
        
        parkingLot.parkVehicle(car1);
        parkingLot.parkVehicle(car2);
        parkingLot.parkVehicle(truck);
        parkingLot.parkVehicle(bike);
        
        parkingLot.displayStatus();
        
        // Unpark a vehicle
        parkingLot.unparkVehicle(0, 0);
        
        parkingLot.displayStatus();
        
        Logger.success("Parking Lot demo completed!\n");
    }
    
    /**
     * Demo 2: Vending Machine
     */
    private static void demoVendingMachine() {
        Logger.info("========== DEMO 2: VENDING MACHINE SYSTEM ==========");
        System.out.println("\nConcept: STATE PATTERN");
        System.out.println("Design Patterns: State, Enum-based State Machine");
        System.out.println("Key Topics: State Transitions, Money Management\n");
        
        VendingMachine machine = new VendingMachine();
        
        // Load products
        machine.loadProduct("A1", "Soda", 1.50, 5);
        machine.loadProduct("A2", "Chips", 1.00, 8);
        machine.loadProduct("B1", "Candy", 0.75, 10);
        
        machine.displayProducts();
        
        // Customer 1: Buy Soda
        Logger.info("Customer 1: Buying Soda");
        machine.insertMoney(2.00);
        machine.displayStatus();
        machine.selectProduct("A1");
        machine.displayStatus();
        
        // Customer 2: Buy Chips
        Logger.info("Customer 2: Buying Chips");
        machine.insertMoney(1.50);
        machine.displayStatus();
        machine.selectProduct("A2");
        machine.displayStatus();
        
        // Customer 3: Insufficient balance
        Logger.info("Customer 3: Insufficient balance");
        machine.insertMoney(0.50);
        machine.selectProduct("A1");
        machine.cancelTransaction();
        
        machine.displayProducts();
        Logger.success("Vending Machine demo completed!\n");
    }
    
    /**
     * Demo 3: Library Management System
     */
    private static void demoLibraryManagement() {
        Logger.info("========== DEMO 3: LIBRARY MANAGEMENT SYSTEM ==========");
        System.out.println("\nConcept: REPOSITORY PATTERN");
        System.out.println("Design Patterns: Singleton, Repository, Collections");
        System.out.println("Key Topics: CRUD Operations, Inventory Management\n");
        
        Library library = Library.getInstance();
        
        // Add books
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "ISBN-001"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "ISBN-002"));
        library.addBook(new Book("1984", "George Orwell", "ISBN-003"));
        library.addBook(new Book("Pride and Prejudice", "Jane Austen", "ISBN-004"));
        
        // Register patrons
        Patron patron1 = new Patron("John Doe", "john@example.com", "555-0001");
        Patron patron2 = new Patron("Jane Smith", "jane@example.com", "555-0002");
        
        library.registerPatron(patron1);
        library.registerPatron(patron2);
        
        library.displayStatus();
        
        // Borrow books
        Logger.info("Borrowing books...");
        library.borrowBook("ISBN-001", patron1.getId());
        library.borrowBook("ISBN-002", patron2.getId());
        library.borrowBook("ISBN-003", patron1.getId());
        
        library.displayStatus();
        
        // Search for books
        library.searchByTitle("Great");
        
        library.displayStatus();
        Logger.success("Library Management demo completed!\n");
    }
}