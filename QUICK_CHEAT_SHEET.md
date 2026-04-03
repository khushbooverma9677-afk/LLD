# LLD Interview - Quick Cheat Sheet

## ⚡ Design Patterns at a Glance

### Creational (Object Creation)
| Pattern | Usage | Key Classes |
|---------|-------|-------------|
| **Singleton** | One instance globally | `ParkingLot`, `Library`, `ATM` |
| **Factory** | Create objects dynamically | `VehicleFactory` |
| **Builder** | Complex object construction | `UserBuilder` |

### Structural (Object Composition)
| Pattern | Usage | Key Classes |
|---------|-------|-------------|
| **Strategy** | Swap algorithms at runtime | `RateLimitingStrategy` |
| **Adapter** | Make incompatible interfaces work | `PaymentAdapter` |
| **Decorator** | Add features dynamically | `MilkDecorator` on Coffee |

### Behavioral (Object Interaction)
| Pattern | Usage | Key Classes |
|---------|-------|-------------|
| **State** | Change behavior by state | `VendingMachine`, `ATM` |
| **Observer** | Notify multiple objects | Event listeners |
| **Strategy** | Select algorithm at runtime | `RateLimiter` |

---

## 🎯 SOLID Quick Reference

### S - Single Responsibility
```java
// ❌ BAD
public class User {
    public void save() { } // Too many responsibilities!
    public void sendEmail() { }
    public void generateReport() { }
}

// ✅ GOOD
public class User { } // Just data
public class UserRepository { public void save() {} }
public class EmailService { public void send() {} }
```

### O - Open/Closed
```java
// ✅ GOOD - Open for extension, closed for modification
public interface PaymentStrategy { void pay(double amount); }
public class CreditCard implements PaymentStrategy { }
public class PayPal implements PaymentStrategy { }
// Add new payment type without modifying existing code!
```

### L - Liskov Substitution
```java
// ✅ GOOD - Subclasses can replace parent safely
public interface Animal { void move(); }
public class Bird implements Animal { public void move() { } }
public class Fish implements Animal { public void move() { } }
// Both can be used wherever Animal is expected
```

### I - Interface Segregation
```java
// ✅ GOOD - Specific interfaces
public interface Workable { void work(); }
public interface Eatable { void eat(); }
public class Robot implements Workable { } // Only what it needs!
```

### D - Dependency Inversion
```java
// ✅ GOOD - Depend on abstraction
public class PaymentService {
    private PaymentProcessor processor; // Injected!
    public PaymentService(PaymentProcessor p) { processor = p; }
}
```

---

## 🔑 Key Data Structures

### HashMap vs LinkedList in LRU Cache
```java
Map<K, Node> cache = new HashMap<>(); // Fast lookup - O(1)

Node (in LinkedList) {
    K key;
    V value;
    Node prev, next;  // Track order - O(1) remove
}
// Together: O(1) get, put, evict
```

### ParkingLot Hierarchy
```
ParkingLot (Singleton)
  └── ParkingLevel[] 
      └── ParkingSpot[]
          └── Vehicle
```

---

## 🚦 State Machine Examples

### Vending Machine States
```
IDLE
  ↓
ACCEPTING_MONEY (insertMoney)
  ↓
DISPENSING (selectProduct)
  ↓
RETURNING_CHANGE (if change needed)
  ↓
IDLE
```

### ATM States
```
IDLE → READING_CARD → AUTHENTICATING → SELECTING_TRANSACTION
                                            ↓
                                      ┌─────┼─────┐
                                      ↓     ↓     ↓
                                  WITHDRAW DEPOSIT CHECK_BALANCE
                                      ↓     ↓     ↓
                                      └─────┼─────┘
                                            ↓
                                      RETURNING_CARD
                                            ↓
                                            IDLE
```

---

## 🧵 Thread-Safety Tips

### Synchronized Methods
```java
public synchronized boolean parkVehicle(Vehicle v) {
    // Only one thread at a time
    if (isAvailable) {
        // park the vehicle
        return true;
    }
    return false;
}
```

### Double-Checked Locking (Singleton)
```java
public static Singleton getInstance() {
    if (instance == null) {  // First check (fast)
        synchronized (Singleton.class) {
            if (instance == null) {  // Second check (safe)
                instance = new Singleton();
            }
        }
    }
    return instance;
}
```

### Collections
```java
// Thread-safe
Map<String, Book> books = new ConcurrentHashMap<>();

// Not thread-safe
Map<String, Book> books = new HashMap<>(); // Must synchronize manually
```

---

## 💡 Common Interview Patterns

### Repository Pattern (Data Access)
```java
public class Library {
    private Map<String, Book> books; // In-memory storage
    
    public void addBook(Book book) { books.put(...); }
    public Book findBook(String id) { return books.get(id); }
}
```

### Strategy Pattern (Algorithm Selection)
```java
public interface Strategy { void execute(); }

public class Context {
    private Strategy strategy;
    
    public void setStrategy(Strategy s) { strategy = s; }
    public void run() { strategy.execute(); }
}
```

### Observer Pattern (Notifications)
```java
public interface Observer { void update(String message); }

public class Subject {
    List<Observer> observers = new ArrayList<>();
    
    public void notifyAll(String msg) {
        for (Observer o : observers) o.update(msg);
    }
}
```

---

## 📊 Complexity Analysis

### Parking Lot
- Park vehicle: O(1)
- Find available spot: O(n) where n = total spots
- Display status: O(n)

### LRU Cache
- Get: O(1)
- Put: O(1)
- Evict: O(1)
- Space: O(capacity)

### Rate Limiter - Token Bucket
- Allow request: O(1)
- Space: O(users)

### Rate Limiter - Fixed Window
- Allow request: O(1)
- Space: O(users)

---

## 🎬 Interview Walkthrough

### Problem: Design a Parking Lot (45 min)

**Minute 0-5: Requirements**
- How many levels?
- Types of vehicles?
- How to find spots?
- Pricing?

**Minute 5-10: Design**
- Draw class diagram
- List entities: ParkingLot, Level, Spot, Vehicle
- Identify patterns: Singleton for ParkingLot
- Design methods

**Minute 10-35: Implementation**
```java
public class ParkingLot {
    private static ParkingLot instance; // Singleton
    private ParkingLevel[] levels;
    
    public boolean parkVehicle(Vehicle v) { /* ... */ }
    public boolean unparkVehicle(...) { /* ... */ }
}
```

**Minute 35-40: Edge Cases**
- What if parking lot is full?
- What if vehicle type not found?
- How to handle concurrent access?

**Minute 40-45: Optimization/Discussion**
- How to find nearest empty spot faster?
- How to add pricing?
- How to add reservations?

---

## ✅ Code Quality Checklist

Before submitting code:
- [ ] Meaningful class and method names
- [ ] No magic numbers (use constants)
- [ ] Single Responsibility Principle
- [ ] Open/Closed Principle
- [ ] Comments for complex logic
- [ ] Proper error handling
- [ ] Thread-safe where needed
- [ ] No unnecessary coupling
- [ ] Follows Java conventions
- [ ] Testable (easy to verify logic)

---

## 🚀 Quick Problem Guide

### Parking Lot
- **Pattern**: Singleton
- **Key**: Multiple levels with spots
- **Complexity**: O(1) park, O(n) find spot
- **Thread-safe**: Yes (synchronized)

### Vending Machine
- **Pattern**: State Machine
- **Key**: State transitions, money handling
- **Complexity**: O(n) find product
- **Thread-safe**: No (single-threaded machine)

### Library Management
- **Pattern**: Repository
- **Key**: Book and patron management
- **Complexity**: O(1) add/borrow, O(n) search
- **Thread-safe**: Partially (HashMap not safe)

### ATM
- **Pattern**: State Machine + Repository
- **Key**: Authentication, transactions
- **Complexity**: O(1) operations
- **Thread-safe**: Yes (account synchronized)

### Rate Limiter
- **Pattern**: Strategy
- **Key**: Different limiting algorithms
- **Complexity**: O(1) allow/reject
- **Thread-safe**: Yes (synchronized)

### LRU Cache
- **Pattern**: HashMap + LinkedList
- **Key**: O(1) get/put/evict
- **Complexity**: O(1) all operations
- **Thread-safe**: Yes (synchronized)

---

## 🎓 Study Tips

1. **Read Code First** - Understand before writing
2. **Trace Execution** - Follow the flow mentally
3. **Ask Questions** - Why this design?
4. **Implement Again** - From scratch, without looking
5. **Add Features** - Extend the system
6. **Discuss Design** - Explain trade-offs

---

## 📝 Common Interview Questions

### General
- "What design patterns are you using?"
- "Why did you choose this pattern?"
- "How would you handle 1000x more users?"
- "What are the bottlenecks?"

### Code Specific
- "What happens if parking lot is full?"
- "How is thread-safety ensured?"
- "How would you persist this data?"
- "What if a user doesn't return a book?"

### Optimization
- "Can you make this faster?"
- "Can you use less memory?"
- "How would you scale this?"
- "What about failure scenarios?"

---

## 🎯 Remember

✅ **Do**:
- Ask clarifying questions
- Start with simple solution
- Handle edge cases
- Write clean code
- Explain your reasoning
- Use design patterns appropriately
- Test your logic

❌ **Don't**:
- Assume requirements
- Over-engineer
- Use complex patterns unnecessarily
- Skip error handling
- Ignore edge cases
- Write spaghetti code

---

**Good luck! You've got this! 🚀**

