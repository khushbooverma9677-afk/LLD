# Step-by-Step Learning Plan for LLD Interview Practice

## 📌 Current Status

You now have a complete Java project with:
- ✅ **Parking Lot System** - Singleton Pattern
- ✅ **Vending Machine System** - State Pattern
- ✅ **Library Management System** - Repository Pattern
- ✅ **Utility Classes** - Reusable base classes and helpers

---

## 🎯 Phase 1: Master the BASICS (Week 1-2)

### Step 1: Understand Parking Lot System
**File**: `src/main/java/org/example/basics/parkinglot/`

**Concepts to Learn**:
- ✅ Singleton Pattern (Thread-safe implementation)
- ✅ Enum types (VehicleType)
- ✅ State management
- ✅ Object composition

**How to Study**:
1. Read through `ParkingLot.java` - understand the singleton pattern
2. Trace through `ParkingLevel.java` - see how levels contain spots
3. Study `ParkingSpot.java` - simple parking spot logic
4. Review `Vehicle.java` - entity class

**Key Questions to Ask Yourself**:
- Why use Singleton for ParkingLot?
- How would you handle concurrent access?
- What if you needed different pricing for different vehicle types?
- How would you handle multiple parking lots?

**Code Walkthrough**:
```
ParkingLot (Singleton)
  └── ParkingLevel[] (Multiple levels)
      └── ParkingSpot[] (Multiple spots per level)
          └── Vehicle (Currently parked or empty)
```

---

### Step 2: Understand Vending Machine System
**File**: `src/main/java/org/example/basics/vendingmachine/`

**Concepts to Learn**:
- ✅ State Pattern (Different behaviors based on state)
- ✅ State transitions
- ✅ Money handling
- ✅ Inventory management

**How to Study**:
1. Look at `VendingMachineState.java` - all possible states
2. Trace through `VendingMachine.java` - state transitions
3. Study `Product.java` - simple product entity

**State Transitions**:
```
IDLE 
  → insertMoney() → ACCEPTING_MONEY
    → selectProduct() → DISPENSING
                     → RETURNING_CHANGE (if change needed)
                     → IDLE
    → cancelTransaction() → IDLE

IDLE → OUT_OF_ORDER (maintenance)
```

**Key Questions**:
- Why use State Pattern here?
- What states are missing? (e.g., OUT_OF_STOCK, ERROR)
- How would you handle card payments?
- How would you implement concurrent transactions?

---

### Step 3: Understand Library Management System
**File**: `src/main/java/org/example/basics/librarymanagement/`

**Concepts to Learn**:
- ✅ Repository Pattern (Data management)
- ✅ Collections (HashMap for storage)
- ✅ CRUD operations
- ✅ Business logic validation

**How to Study**:
1. Study `Library.java` - the repository/manager
2. Review `Book.java` - simple entity
3. Understand `Patron.java` - user entity with constraints

**Key Operations**:
```
- addBook(Book)
- registerPatron(Patron)
- borrowBook(isbn, patronId)
- returnBook(bookId, patronId)
- searchByTitle(title)
```

**Key Questions**:
- Why use HashMap instead of List?
- How would you handle book reservations?
- What happens if a patron doesn't return a book?
- How would you implement a fine system?

---

## 📋 Run the Demos

To see all three systems in action:

```bash
cd "C:\Users\khushbooo\IdeaProjects\SystemDesign"
java -cp "src/main/java" org.example.Main
```

This will show:
1. Parking lot filling up and emptying
2. Vending machine customers buying and getting change
3. Library patrons borrowing books

---

## 🔄 Phase 2: Implement Features (Week 2 Practice)

For each system, try implementing these features:

### Parking Lot Enhancements
- [ ] Add pricing calculation (different rates per vehicle type)
- [ ] Add payment tracking
- [ ] Implement "near me" feature (find nearest empty spot)
- [ ] Add reservation system
- [ ] Implement concurrent booking without conflicts

**Difficulty**: ⭐⭐⭐

### Vending Machine Enhancements
- [ ] Add MORE states (OUT_OF_STOCK, ERROR, UNDER_MAINTENANCE)
- [ ] Implement card payments
- [ ] Add discount system
- [ ] Implement concurrent transactions safely
- [ ] Add audit logging

**Difficulty**: ⭐⭐⭐

### Library Enhancements
- [ ] Add fine system for late returns
- [ ] Implement book reservations (queue system)
- [ ] Add rating/review system
- [ ] Implement book categories
- [ ] Add librarian role with different permissions

**Difficulty**: ⭐⭐⭐⭐

---

## 🚀 Phase 3: Intermediate Problems (Week 3-4)

After mastering basics, move to intermediate:

### Problem 4: ATM System ⭐⭐⭐
**Concepts**: State Pattern, Transactions, Security

**File to Create**: `src/main/java/org/example/intermediate/atm/`

**Key Classes**:
- ATM (Singleton)
- Account (user's bank account)
- Transaction (record of each operation)
- ATMState (Enum: IDLE, READING_CARD, AUTHENTICATING, etc.)

**Operations**:
- insertCard()
- authenticate(pin)
- withdraw(amount)
- deposit(amount)
- checkBalance()

---

### Problem 5: Rate Limiter ⭐⭐⭐
**Concepts**: Algorithm Design, Thread-safety, Concurrency

**Algorithms to implement**:
- Token Bucket
- Sliding Window
- Leaky Bucket

---

### Problem 6: LRU Cache ⭐⭐⭐
**Concepts**: HashMap + LinkedList, Cache eviction, Thread-safety

**Key Methods**:
- get(key)
- put(key, value)
- evict() - remove least recently used

---

## 💡 Learning Strategy

### For Each Problem:
1. **Understand Requirements** (5 min)
   - Read problem carefully
   - List all entities
   - Define relationships

2. **Design Phase** (10 min)
   - Draw class diagram
   - Identify design patterns
   - List key methods

3. **Implementation** (30 min)
   - Code the core classes
   - Write method logic
   - Handle edge cases

4. **Testing** (15 min)
   - Write test scenarios
   - Check boundary conditions
   - Verify state transitions

5. **Optimization** (10 min)
   - Review code quality
   - Apply SOLID principles
   - Consider concurrency

---

## 🎓 Design Patterns Quick Reference

### Creational Patterns
```java
// SINGLETON
public class Singleton {
    private static Singleton instance;
    private Singleton() {}
    public static synchronized Singleton getInstance() {
        if(instance == null) instance = new Singleton();
        return instance;
    }
}

// FACTORY
public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType type) {
        return new Vehicle(type);
    }
}
```

### Structural Patterns
```java
// DECORATOR - Add functionality dynamically
public interface Component { }
public class ConcreteComponent implements Component { }
public class Decorator implements Component {
    private Component component;
}
```

### Behavioral Patterns
```java
// STATE - Behavior changes based on state
public interface State {
    void handle(Context context);
}

// OBSERVER - One-to-many notification
public interface Observer {
    void update(Subject subject);
}
```

---

## 📚 SOLID Principles Checklist

Before submitting your code, verify:

### Single Responsibility ✓
- Each class has ONE reason to change
- ParkingLot manages lots, not payments

### Open/Closed ✓
- Classes should be open for extension, closed for modification
- Use interfaces to extend behavior

### Liskov Substitution ✓
- Subtypes can replace supertypes
- A VehicleType.CAR should work everywhere Vehicle is expected

### Interface Segregation ✓
- Don't force clients to depend on interfaces they don't use
- Parking lot doesn't need to implement payment methods

### Dependency Inversion ✓
- Depend on abstractions, not concrete classes
- Use interfaces and abstract classes

---

## ⏰ Weekly Schedule

### Week 1
- **Day 1-2**: Parking Lot System (understand, implement, enhance)
- **Day 3-4**: Vending Machine System (understand, implement, enhance)
- **Day 5-7**: Library Management System (understand, implement, enhance)

### Week 2
- **Day 1-2**: Implement 2-3 enhancements for each system
- **Day 3-4**: Code review - apply SOLID principles
- **Day 5-7**: Prepare for intermediate problems

### Week 3-4
- **Day 1-5**: Implement ATM System
- **Day 6-10**: Implement Rate Limiter
- **Day 11-14**: Implement LRU Cache

---

## 🎯 Interview Tips

1. **Ask Questions**: Clarify requirements before coding
2. **Think Out Loud**: Explain your design decisions
3. **Start Simple**: MVP first, then add features
4. **Handle Edge Cases**: What if X goes wrong?
5. **Code Quality**: Clean, readable, well-structured code
6. **Test Cases**: Verify your logic works
7. **Discuss Trade-offs**: Why this design over that?

---

## 📁 Project Structure (Final)

```
SystemDesign/
├── src/main/java/org/example/
│   ├── Main.java (Entry point with demos)
│   ├── patterns/
│   │   ├── SingletonPattern.java
│   │   ├── StatePattern.java
│   │   ├── ObserverPattern.java
│   │   └── FactoryPattern.java
│   ├── utils/
│   │   ├── BaseEntity.java
│   │   ├── IdGenerator.java
│   │   └── Logger.java
│   ├── basics/
│   │   ├── parkinglot/
│   │   │   ├── ParkingLot.java
│   │   │   ├── ParkingLevel.java
│   │   │   ├── ParkingSpot.java
│   │   │   ├── Vehicle.java
│   │   │   └── VehicleType.java
│   │   ├── vendingmachine/
│   │   │   ├── VendingMachine.java
│   │   │   ├── Product.java
│   │   │   └── VendingMachineState.java
│   │   └── librarymanagement/
│   │       ├── Library.java
│   │       ├── Book.java
│   │       └── Patron.java
│   ├── intermediate/
│   │   ├── atm/
│   │   ├── ratelimiter/
│   │   └── lrucache/
│   └── advanced/
│       ├── fooddelivery/
│       ├── elevator/
│       ├── onlineshopping/
│       └── etc/
└── LLD_INTERVIEW_GUIDE.md
```

---

## ✨ Next Steps

1. **Review the three basic systems** - Run Main.java and understand the flow
2. **Study one system at a time** - Read code, understand patterns, trace execution
3. **Implement enhancements** - Add features to each system
4. **Move to intermediate** - Start with ATM system
5. **Practice regularly** - 2-3 hours daily, 5 days a week

---

## 🔗 Resources

- **Design Patterns**: Study each pattern thoroughly
- **SOLID Principles**: Apply them to every problem
- **Concurrency**: Learn about threads, synchronized, locks
- **Data Structures**: HashMap, LinkedList, Queue, etc.

Good luck with your LLD interview preparation! 🚀

