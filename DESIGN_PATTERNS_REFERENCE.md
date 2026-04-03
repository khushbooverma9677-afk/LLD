# Design Patterns Quick Reference for LLD

## 🏗️ Creational Patterns

### 1. Singleton Pattern
**Use Case**: Ensure only ONE instance of a class exists (ParkingLot, Library, ATM)

```java
public class ParkingLot {
    private static ParkingLot instance;
    
    private ParkingLot() { } // Private constructor
    
    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }
}
```

**When to Use**:
- Database connections
- Logging
- Configuration managers
- Caches
- Thread pools

**Thread-Safe Version (Double-Checked Locking)**:
```java
public static ParkingLot getInstance() {
    if (instance == null) {
        synchronized (ParkingLot.class) {
            if (instance == null) {
                instance = new ParkingLot();
            }
        }
    }
    return instance;
}
```

---

### 2. Factory Pattern
**Use Case**: Create objects without specifying exact classes

```java
public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType type, String licensePlate) {
        switch(type) {
            case CAR:
                return new Car(licensePlate);
            case BIKE:
                return new Bike(licensePlate);
            case TRUCK:
                return new Truck(licensePlate);
            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }
}

// Usage
Vehicle vehicle = VehicleFactory.createVehicle(VehicleType.CAR, "ABC-123");
```

**When to Use**:
- Object creation is complex
- Want to hide creation logic
- Need to support multiple subclasses

---

### 3. Builder Pattern
**Use Case**: Create complex objects step by step

```java
public class User {
    private String name;
    private String email;
    private int age;
    
    public static class Builder {
        private String name;
        private String email;
        private int age;
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public Builder age(int age) {
            this.age = age;
            return this;
        }
        
        public User build() {
            return new User(this);
        }
    }
    
    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
    }
}

// Usage
User user = new User.Builder()
    .name("John")
    .email("john@example.com")
    .age(30)
    .build();
```

**When to Use**:
- Objects with many optional parameters
- Immutable objects
- Complex initialization

---

## 🔗 Structural Patterns

### 4. Decorator Pattern
**Use Case**: Add functionality to objects dynamically

```java
public interface Coffee {
    double getCost();
    String getDescription();
}

public class SimpleCoffee implements Coffee {
    public double getCost() { return 1.0; }
    public String getDescription() { return "Simple Coffee"; }
}

public class MilkDecorator implements Coffee {
    private Coffee coffee;
    
    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
    
    public double getCost() {
        return coffee.getCost() + 0.5;
    }
    
    public String getDescription() {
        return coffee.getDescription() + " + Milk";
    }
}

// Usage
Coffee coffee = new SimpleCoffee();
coffee = new MilkDecorator(coffee);
coffee = new SugarDecorator(coffee);
// Cost: 1.0 + 0.5 + 0.3 = 1.8
```

**When to Use**:
- Add features without modifying original class
- Alternative to inheritance
- Combine multiple features

---

### 5. Adapter Pattern
**Use Case**: Make incompatible interfaces work together

```java
public interface PaymentGateway {
    void pay(double amount);
}

public class OldPaymentSystem {
    public void processPayment(double amt) {
        // Old implementation
    }
}

public class PaymentAdapter implements PaymentGateway {
    private OldPaymentSystem oldSystem;
    
    public PaymentAdapter(OldPaymentSystem oldSystem) {
        this.oldSystem = oldSystem;
    }
    
    public void pay(double amount) {
        oldSystem.processPayment(amount);
    }
}

// Usage
PaymentGateway gateway = new PaymentAdapter(new OldPaymentSystem());
gateway.pay(100.0);
```

---

### 6. Facade Pattern
**Use Case**: Provide simplified interface to complex subsystem

```java
public class BankingFacade {
    private Account account;
    private Notifications notif;
    private Security security;
    
    public boolean withdraw(String pin, double amount) {
        if (!security.validatePin(pin)) return false;
        if (!account.hasSufficientFunds(amount)) return false;
        
        account.deduct(amount);
        notif.sendSMS("Withdrawn: " + amount);
        return true;
    }
}

// Simple interface for client
BankingFacade banking = new BankingFacade();
banking.withdraw("1234", 1000.0);
```

---

## 🎯 Behavioral Patterns

### 7. State Pattern ⭐
**Use Case**: Change behavior based on state (VendingMachine example)

```java
public enum VendingMachineState {
    IDLE,
    ACCEPTING_MONEY,
    DISPENSING,
    RETURNING_CHANGE
}

public class VendingMachine {
    private VendingMachineState state = VendingMachineState.IDLE;
    private double balance = 0;
    
    public void insertMoney(double amount) {
        if (state == VendingMachineState.OUT_OF_ORDER) {
            return;
        }
        state = VendingMachineState.ACCEPTING_MONEY;
        balance += amount;
    }
    
    public void selectProduct(String code) {
        if (state != VendingMachineState.ACCEPTING_MONEY) {
            return;
        }
        // Check balance, dispense, etc.
        state = VendingMachineState.IDLE;
    }
}

// State Transitions:
// IDLE -> ACCEPTING_MONEY -> DISPENSING -> RETURNING_CHANGE -> IDLE
```

**Key Points**:
- Encapsulate state-specific behavior
- Avoid large if-else chains
- Each state has clear entry/exit conditions

---

### 8. Observer Pattern
**Use Case**: Notify multiple objects about state changes

```java
public interface Observer {
    void update(String message);
}

public class Subject {
    private List<Observer> observers = new ArrayList<>();
    
    public void attach(Observer observer) {
        observers.add(observer);
    }
    
    public void notifyAll(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

public class EmailObserver implements Observer {
    public void update(String message) {
        System.out.println("Sending email: " + message);
    }
}

public class SMSObserver implements Observer {
    public void update(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

// Usage
Subject subject = new Subject();
subject.attach(new EmailObserver());
subject.attach(new SMSObserver());
subject.notifyAll("Order placed!");
// Outputs both email and SMS notifications
```

**When to Use**:
- Event-driven systems
- Notifications
- Real-time updates

---

### 9. Strategy Pattern
**Use Case**: Select algorithm at runtime

```java
public interface PaymentStrategy {
    boolean pay(double amount);
}

public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    public boolean pay(double amount) { /* ... */ }
}

public class PayPalPayment implements PaymentStrategy {
    private String email;
    public boolean pay(double amount) { /* ... */ }
}

public class Order {
    private double amount;
    private PaymentStrategy strategy;
    
    public void setPaymentMethod(PaymentStrategy strategy) {
        this.strategy = strategy;
    }
    
    public boolean checkout() {
        return strategy.pay(amount);
    }
}

// Usage
Order order = new Order(100);
order.setPaymentMethod(new CreditCardPayment("1234-5678-90"));
order.checkout();
```

---

### 10. Template Method Pattern
**Use Case**: Define algorithm skeleton, let subclasses fill details

```java
public abstract class DataProcessor {
    
    // Template method - defines the skeleton
    public final void processData(String filename) {
        String data = readFile(filename);
        data = validateData(data);
        data = transformData(data);
        saveData(data);
    }
    
    // Steps to be implemented by subclasses
    protected abstract String validateData(String data);
    protected abstract String transformData(String data);
    
    // Common implementation
    protected String readFile(String filename) {
        // Read file
    }
    
    protected void saveData(String data) {
        // Save to database
    }
}

public class CSVProcessor extends DataProcessor {
    protected String validateData(String data) { /* CSV validation */ }
    protected String transformData(String data) { /* CSV transformation */ }
}
```

---

### 11. Chain of Responsibility
**Use Case**: Pass request along a chain of handlers

```java
public abstract class RequestHandler {
    protected RequestHandler nextHandler;
    
    public void setNextHandler(RequestHandler handler) {
        this.nextHandler = handler;
    }
    
    public void handleRequest(Request request) {
        if (canHandle(request)) {
            process(request);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
    
    protected abstract boolean canHandle(Request request);
    protected abstract void process(Request request);
}

// Example: Approval chain
public class ManagerApproval extends RequestHandler {
    protected boolean canHandle(Request request) {
        return request.getAmount() <= 10000;
    }
    protected void process(Request request) { /* Approve */ }
}

public class DirectorApproval extends RequestHandler {
    protected boolean canHandle(Request request) {
        return request.getAmount() <= 50000;
    }
    protected void process(Request request) { /* Approve */ }
}
```

---

## 📊 Pattern Selection Guide

| Problem | Pattern | Use Case |
|---------|---------|----------|
| Parking Lot | Singleton | Single instance of system |
| Vending Machine | State | Behavior based on states |
| Library | Repository | Data management |
| ATM | State + Strategy | States + multiple payment types |
| Rate Limiter | Strategy | Different limiting algorithms |
| Food Delivery | Observer | Notify users of order status |
| Elevator | State + Observer | Multiple elevators, state changes |
| Cache | Decorator | Wrap data access |
| Payment | Strategy | Multiple payment methods |
| Notification | Observer | Multiple channels (email, SMS, push) |

---

## 🔑 SOLID Principles vs Patterns

### Single Responsibility (S)
- Each class has ONE job
- ParkingLot manages parking, not payments
- Achieved by: Factory, Strategy, Adapter

### Open/Closed (O)
- Open for extension, closed for modification
- Add new PaymentStrategy without changing Order
- Achieved by: Strategy, Decorator, Observer

### Liskov Substitution (L)
- Subclasses can replace parent classes
- Any PaymentStrategy can substitute another
- Achieved by: Factory, Strategy, Template Method

### Interface Segregation (I)
- Don't force unused methods
- PaymentInterface vs TransactionInterface
- Achieved by: Adapter, Facade, Interface design

### Dependency Inversion (D)
- Depend on abstractions, not concrete classes
- Constructor injection of dependencies
- Achieved by: Dependency Injection, Factory, Strategy

---

## ⚡ Common Mistakes to Avoid

### ❌ **Mistake 1**: Overuse Singleton
```java
// BAD
public class UserManager { 
    private static UserManager instance; // Don't make everything singleton!
}

// GOOD
public class UserService { 
    private UserRepository repo; // Dependency injection instead
}
```

### ❌ **Mistake 2**: Mixing Concerns
```java
// BAD
public class User {
    public void save() { /* Database logic */ }
    public void sendEmail() { /* Email logic */ }
    public void validate() { /* Validation logic */ }
}

// GOOD - Separate responsibilities
public class User { /* Just data */ }
public class UserRepository { /* Save/load */ }
public class EmailService { /* Send emails */ }
public class UserValidator { /* Validate */ }
```

### ❌ **Mistake 3**: Not Handling Edge Cases
```java
// BAD
public void withdraw(double amount) {
    balance -= amount; // What if amount > balance?
}

// GOOD
public boolean withdraw(double amount) {
    if (amount <= 0 || amount > balance) return false;
    balance -= amount;
    return true;
}
```

---

## 🎓 Practice Exercises

1. **Parking Lot**: Add different pricing strategies using Strategy Pattern
2. **Vending Machine**: Add more states and handle errors gracefully
3. **Library**: Add reservation system with Observer Pattern
4. **ATM**: Implement different withdrawal strategies (fast, secure, etc.)
5. **Cache**: Implement with Decorator Pattern for logging

---

**Happy Coding! 🚀**

