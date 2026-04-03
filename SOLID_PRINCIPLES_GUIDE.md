# SOLID Principles - Complete Guide for LLD

## Overview
SOLID is a set of 5 design principles that help create maintainable, scalable, and flexible code.

---

## 1️⃣ Single Responsibility Principle (SRP)

**Definition**: A class should have ONE and ONLY ONE reason to change.

### ❌ Bad Example
```java
public class User {
    private String name;
    private String email;
    
    // Too many responsibilities!
    public void save() { /* Save to database */ }
    public void sendEmail() { /* Send email */ }
    public void generateReport() { /* Generate PDF */ }
    public void logActivity() { /* Log to file */ }
    public void validateEmail() { /* Validation */ }
}
```

**Problems**:
- Hard to test (need database, email, file system mocks)
- Changes in any responsibility affect the class
- Violates single purpose principle

### ✅ Good Example
```java
// Responsibility 1: Represent a user
public class User {
    private String name;
    private String email;
    
    public String getName() { return name; }
    public String getEmail() { return email; }
}

// Responsibility 2: Persist user
public class UserRepository {
    public void save(User user) { /* Database operations */ }
}

// Responsibility 3: Send emails
public class EmailService {
    public void sendWelcomeEmail(User user) { /* Email logic */ }
}

// Responsibility 4: Generate reports
public class ReportGenerator {
    public void generateUserReport(List<User> users) { /* PDF logic */ }
}

// Responsibility 5: Logging
public class ActivityLogger {
    public void log(User user, String action) { /* Log logic */ }
}
```

### Real Example from Our Project
```java
// GOOD - ParkingLot only manages parking
public class ParkingLot {
    public boolean parkVehicle(Vehicle vehicle) { /* Parking logic */ }
    public boolean unparkVehicle(int level, int spot) { /* Unparking logic */ }
    public int getTotalAvailableSpots() { /* Calculate spots */ }
}

// Separate class for payments (if needed)
public class ParkingPayment {
    public void calculateFee(Vehicle vehicle, long duration) { /* Price */ }
}

// Separate class for notifications
public class ParkingNotification {
    public void notifySlotFull() { /* Send alerts */ }
}
```

### Benefits
✅ Easy to test
✅ Easy to understand
✅ Easy to modify
✅ Reusable code
✅ Less coupling

---

## 2️⃣ Open/Closed Principle (OCP)

**Definition**: Software entities should be **OPEN for extension** but **CLOSED for modification**.

### ❌ Bad Example - Violates OCP
```java
public class PaymentProcessor {
    public void processPayment(String type, double amount) {
        if (type.equals("CREDIT_CARD")) {
            // Credit card logic
        } else if (type.equals("PAYPAL")) {
            // PayPal logic
        } else if (type.equals("BITCOIN")) {
            // Bitcoin logic
        }
        // Need to modify this class every time we add a new payment method!
    }
}
```

**Problem**: Adding a new payment method requires modifying existing code (CLOSED for modification)

### ✅ Good Example - Follows OCP
```java
// Create an abstraction
public interface PaymentStrategy {
    void pay(double amount);
}

// Implement different strategies
public class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paying " + amount + " via Credit Card");
    }
}

public class PayPalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paying " + amount + " via PayPal");
    }
}

public class BitcoinPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paying " + amount + " via Bitcoin");
    }
}

// Payment processor - CLOSED for modification
public class PaymentProcessor {
    private PaymentStrategy strategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void processPayment(double amount) {
        strategy.pay(amount); // No if-else needed!
    }
}

// Usage
PaymentProcessor processor = new PaymentProcessor();
processor.setPaymentStrategy(new CreditCardPayment());
processor.processPayment(100.0);

// Add new payment method WITHOUT modifying PaymentProcessor!
processor.setPaymentStrategy(new GooglePayPayment());
```

### Real Example from Our Project
```java
// ✅ VendingMachine is closed for modification regarding product types
// We just add new products without changing the machine logic
public class VendingMachine {
    private Product[] inventory;
    
    public void selectProduct(String code) {
        Product product = findProduct(code);
        // Works for ANY product type without modification
        dispenseProduct(product);
    }
}
```

### How to Achieve OCP
1. **Use Abstraction**: Create interfaces/abstract classes
2. **Use Polymorphism**: Let subclasses implement behavior
3. **Use Composition**: Inject dependencies
4. **Use Design Patterns**: Strategy, Decorator, Template Method

---

## 3️⃣ Liskov Substitution Principle (LSP)

**Definition**: Objects of a superclass should be replaceable with objects of its subclasses without breaking the application.

### ❌ Bad Example - Violates LSP
```java
public class Bird {
    public void fly() {
        System.out.println("Flying...");
    }
}

public class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}

// This violates LSP!
public void makeBirdFly(Bird bird) {
    bird.fly(); // Crashes if bird is Penguin!
}
```

**Problem**: You can't substitute Penguin for Bird safely

### ✅ Good Example - Follows LSP
```java
public interface Animal {
    void move();
}

public class FlyingBird implements Animal {
    public void move() {
        System.out.println("Flying...");
    }
}

public class Penguin implements Animal {
    public void move() {
        System.out.println("Swimming...");
    }
}

// Now we can safely use any Animal
public void makeAnimalMove(Animal animal) {
    animal.move(); // Works for any animal!
}
```

### Real Example from Our Project
```java
// ✅ Any VehicleType can be parked without issues
public abstract class Vehicle {
    protected String licensePlate;
    protected VehicleType type;
    
    // Works for CAR, BIKE, TRUCK equally
    public VehicleType getType() { return type; }
}

public class ParkingLot {
    public void parkVehicle(Vehicle vehicle) {
        // Works with CAR, BIKE, TRUCK - no special handling needed
        findAndParkSpot(vehicle);
    }
}
```

### How to Achieve LSP
1. **Don't override methods** in a way that breaks the contract
2. **Return same or more specific types** (not less specific)
3. **Don't throw unexpected exceptions**
4. **Ensure substitutability** in all scenarios

---

## 4️⃣ Interface Segregation Principle (ISP)

**Definition**: Many client-specific interfaces are better than one general-purpose interface. Clients should not be forced to depend on interfaces they don't use.

### ❌ Bad Example - Violates ISP
```java
// Fat interface with too many methods
public interface Worker {
    void work();
    void eat();
    void sleep();
    void manageTeam();
    void reportToManager();
}

// Robot must implement methods it doesn't use!
public class Robot implements Worker {
    public void work() { /* Working */ }
    public void eat() { throw new UnsupportedOperationException(); }
    public void sleep() { throw new UnsupportedOperationException(); }
    public void manageTeam() { throw new UnsupportedOperationException(); }
    public void reportToManager() { throw new UnsupportedOperationException(); }
}
```

**Problem**: Robot is forced to implement methods it doesn't need

### ✅ Good Example - Follows ISP
```java
// Segregate into specific interfaces
public interface Worker {
    void work();
}

public interface Eater {
    void eat();
}

public interface Sleeper {
    void sleep();
}

public interface Manager {
    void manageTeam();
    void reportToManager();
}

// Robot only implements what it needs
public class Robot implements Worker {
    public void work() { /* Working */ }
}

// Human implements all relevant interfaces
public class Human implements Worker, Eater, Sleeper, Manager {
    public void work() { /* Work */ }
    public void eat() { /* Eat */ }
    public void sleep() { /* Sleep */ }
    public void manageTeam() { /* Manage */ }
    public void reportToManager() { /* Report */ }
}
```

### Real Example from Our Project
```java
// ✅ Specific interfaces instead of fat interface
public interface Parkable {
    boolean park(Vehicle vehicle);
    boolean unpark(int spotNumber);
}

public interface ParkingNotification {
    void notifySlotFull();
    void notifyVacancyAvailable();
}

// ParkingLot implements only relevant interface
public class ParkingLot implements Parkable {
    // No need to implement notification methods here
}
```

### How to Achieve ISP
1. **Break large interfaces** into smaller ones
2. **Clients depend on what they use** only
3. **Use role-based interfaces**
4. **Don't force implementations** of unused methods

---

## 5️⃣ Dependency Inversion Principle (DIP)

**Definition**: 
- High-level modules should not depend on low-level modules
- Both should depend on abstractions
- Abstractions should not depend on details
- Details should depend on abstractions

### ❌ Bad Example - Violates DIP
```java
// High-level class depends on low-level implementations
public class PaymentService {
    private CreditCardProcessor creditCardProcessor = new CreditCardProcessor();
    private PayPalProcessor paypalProcessor = new PayPalProcessor();
    private BitcoinProcessor bitcoinProcessor = new BitcoinProcessor();
    
    public void pay(String method, double amount) {
        if (method.equals("CC")) {
            creditCardProcessor.process(amount);
        } else if (method.equals("PP")) {
            paypalProcessor.process(amount);
        }
        // Tightly coupled to concrete implementations!
    }
}
```

**Problems**:
- Can't easily add new payment methods
- Hard to test (need real implementations)
- High-level module depends on low-level modules

### ✅ Good Example - Follows DIP
```java
// Define abstraction
public interface PaymentProcessor {
    boolean process(double amount);
}

// Concrete implementations
public class CreditCardProcessor implements PaymentProcessor {
    public boolean process(double amount) { /* ... */ }
}

public class PayPalProcessor implements PaymentProcessor {
    public boolean process(double amount) { /* ... */ }
}

// High-level module depends on abstraction (not concrete classes)
public class PaymentService {
    private PaymentProcessor processor;
    
    // Inject dependency (not create it)
    public PaymentService(PaymentProcessor processor) {
        this.processor = processor;
    }
    
    public void pay(double amount) {
        processor.process(amount);
    }
}

// Usage - loose coupling!
PaymentService service = new PaymentService(new CreditCardProcessor());
service.pay(100.0);

// Can easily switch implementation
service = new PaymentService(new PayPalProcessor());
```

### Real Example from Our Project
```java
// ✅ Library doesn't depend on HashMap implementation
public class Library {
    // Depends on abstraction (Collection interface)
    private Map<String, Book> books;
    
    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
    }
}

// Can swap HashMap for ConcurrentHashMap without breaking Library
private Map<String, Book> books = new ConcurrentHashMap<>();
```

### Dependency Injection Methods

**Constructor Injection** (Recommended)
```java
public class OrderService {
    private PaymentProcessor payment;
    
    public OrderService(PaymentProcessor payment) {
        this.payment = payment;
    }
}
```

**Setter Injection**
```java
public class OrderService {
    private PaymentProcessor payment;
    
    public void setPaymentProcessor(PaymentProcessor payment) {
        this.payment = payment;
    }
}
```

**Interface Injection**
```java
public interface PaymentInjector {
    void injectPayment(PaymentProcessor payment);
}

public class OrderService implements PaymentInjector {
    private PaymentProcessor payment;
    
    public void injectPayment(PaymentProcessor payment) {
        this.payment = payment;
    }
}
```

---

## 🎓 SOLID Checklist

Before submitting code, verify:

- [ ] **Single Responsibility**: Each class has ONE reason to change
- [ ] **Open/Closed**: Classes are open for extension, closed for modification
- [ ] **Liskov Substitution**: Subclasses can replace parent classes safely
- [ ] **Interface Segregation**: Interfaces are specific, not fat
- [ ] **Dependency Inversion**: Depend on abstractions, not concrete classes

---

## 📊 Principle Application Matrix

| Principle | Pattern | Implementation |
|-----------|---------|-----------------|
| SRP | Single responsibility | Each class does one thing |
| OCP | Open/Closed | Use abstraction + polymorphism |
| LSP | Liskov | Proper inheritance hierarchy |
| ISP | Interface Segregation | Multiple specific interfaces |
| DIP | Dependency Injection | Inject dependencies |

---

## 🔄 Real Project Example: Parking Lot

### Analyze Against SOLID

```java
// ✅ SINGLE RESPONSIBILITY
// - ParkingLot manages parking only
// - ParkingLevel manages a level
// - ParkingSpot manages a spot
// Each class has one reason to change

// ✅ OPEN/CLOSED
// - Add new VehicleType without modifying ParkingLot
// - Add new features (pricing, notifications) as extensions

// ✅ LISKOV SUBSTITUTION
// - Any VehicleType works with ParkingLot
// - No special handling needed for different vehicle types

// ✅ INTERFACE SEGREGATION
// - ParkingLot doesn't implement unnecessary interfaces
// - Separate concerns (parking, payment, notification)

// ✅ DEPENDENCY INVERSION
// - ParkingLot depends on abstraction (Vehicle interface)
// - Not on concrete Vehicle implementations
```

---

## 🚀 Anti-Patterns to Avoid

### ❌ God Class
```java
// DON'T DO THIS
public class User {
    // 100+ methods doing everything
    public void save() { }
    public void delete() { }
    public void sendEmail() { }
    public void generateReport() { }
    // ... 96 more methods
}
```

### ❌ Rigid Inheritance
```java
// DON'T DO THIS
public class Vehicle { }
public class Car extends Vehicle { }
public class Bicycle extends Vehicle { }
public class Boat extends Vehicle { }
// Boat shouldn't inherit from Vehicle!
```

### ❌ Tight Coupling
```java
// DON'T DO THIS
public class PaymentService {
    private CreditCardProcessor cc = new CreditCardProcessor();
    private PayPalProcessor pp = new PayPalProcessor();
    // Can't change implementations easily
}
```

### ✅ Do This Instead
```java
// Use composition + dependency injection
public class PaymentService {
    private PaymentProcessor processor;
    
    public PaymentService(PaymentProcessor processor) {
        this.processor = processor;
    }
}
```

---

## 📚 Key Takeaways

1. **SRP**: One responsibility per class
2. **OCP**: Extend via abstraction, don't modify
3. **LSP**: Subclasses must be truly substitutable
4. **ISP**: Small, specific interfaces
5. **DIP**: Depend on abstractions, inject dependencies

**Remember**: SOLID principles lead to:
- ✅ More maintainable code
- ✅ Easier testing
- ✅ Better code reuse
- ✅ Reduced bugs
- ✅ Happier team members!

---

**Now you're ready to write SOLID code! 🎉**

