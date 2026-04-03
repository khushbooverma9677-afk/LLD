# LLD Interview Practice - Complete Guide

## 🎯 Overview

This comprehensive project contains **step-by-step implementation** of the **most asked Low-Level Design (LLD) interview questions** in Java. It's designed to help you prepare for system design interviews at top tech companies like Google, Amazon, Microsoft, Apple, etc.

---

## 📂 Project Structure

```
SystemDesign/
├── 📄 README.md (This file)
├── 📄 LLD_INTERVIEW_GUIDE.md (Problem listing and timeline)
├── 📄 STEP_BY_STEP_GUIDE.md (Detailed learning plan)
├── 📄 DESIGN_PATTERNS_REFERENCE.md (11 key design patterns)
├── 📄 SOLID_PRINCIPLES_GUIDE.md (5 SOLID principles)
│
└── src/main/java/org/example/
    ├── Main.java (Demo runner)
    │
    ├── utils/ (Reusable utilities)
    │   ├── BaseEntity.java
    │   ├── IdGenerator.java
    │   └── Logger.java
    │
    ├── basics/ (Week 1-2: Foundation)
    │   ├── parkinglot/
    │   │   ├── ParkingLot.java (⭐ Singleton Pattern)
    │   │   ├── ParkingLevel.java
    │   │   ├── ParkingSpot.java
    │   │   ├── Vehicle.java
    │   │   └── VehicleType.java
    │   │
    │   ├── vendingmachine/
    │   │   ├── VendingMachine.java (⭐ State Pattern)
    │   │   ├── Product.java
    │   │   └── VendingMachineState.java
    │   │
    │   └── librarymanagement/
    │       ├── Library.java (⭐ Repository Pattern)
    │       ├── Book.java
    │       └── Patron.java
    │
    ├── intermediate/ (Week 3-4: Moderate Complexity)
    │   ├── atm/
    │   │   ├── ATM.java (⭐ State Pattern)
    │   │   ├── ATMState.java
    │   │   ├── BankAccount.java
    │   │   └── Bank.java
    │   │
    │   ├── ratelimiter/
    │   │   ├── RateLimiter.java (⭐ Strategy Pattern)
    │   │   ├── RateLimitingStrategy.java
    │   │   ├── TokenBucketRateLimiter.java
    │   │   └── FixedWindowRateLimiter.java
    │   │
    │   └── lrucache/
    │       └── LRUCache.java (⭐ Data Structure + Algorithm)
    │
    ├── advanced/ (Week 5-7: Complex Systems)
    │   ├── fooddelivery/
    │   ├── elevator/
    │   ├── onlineshopping/
    │   └── etc/
    │
    └── patterns/ (Design pattern templates)
```

---

## 🎓 Learning Path

### **WEEK 1-2: Master the Basics** ⭐⭐

| # | Problem | Pattern | Focus |
|---|---------|---------|-------|
| 1 | **Parking Lot** | Singleton | One instance, thread-safety |
| 2 | **Vending Machine** | State | State transitions, enums |
| 3 | **Library Management** | Repository | Collections, CRUD |

**Duration**: 5-7 days
**Daily Time**: 2-3 hours

**Learning Objectives**:
- ✅ Understand Singleton pattern deeply
- ✅ Implement State Pattern correctly
- ✅ Use Collections (HashMap, List) effectively
- ✅ Write clean, SOLID code
- ✅ Practice thread-safety

---

### **WEEK 3-4: Build Intermediate Skills** ⭐⭐⭐

| # | Problem | Pattern | Focus |
|---|---------|---------|-------|
| 4 | **ATM System** | State | Multiple states, authentication |
| 5 | **Rate Limiter** | Strategy | Algorithm selection, concurrency |
| 6 | **LRU Cache** | Data Structures | HashMap + LinkedList, eviction |

**Duration**: 7-10 days
**Daily Time**: 2-3 hours

**Learning Objectives**:
- ✅ Master Strategy Pattern
- ✅ Handle complex state machines
- ✅ Implement efficient algorithms
- ✅ Understand caching strategies
- ✅ Write thread-safe concurrent code

---

### **WEEK 5-7: Tackle Advanced Problems** ⭐⭐⭐⭐

*(Coming soon - You'll implement these yourself!)*

- Food Delivery System (Observer, Strategy)
- Elevator System (State, Observer)
- Online Shopping (Strategy, Observer)
- Ride Sharing (Observer, Strategy)
- Chess Game (State, Strategy)

---

## 🚀 Getting Started

### Step 1: Review Documentation

Start by reading the guides in order:

1. **LLD_INTERVIEW_GUIDE.md** - Overview of all problems
2. **DESIGN_PATTERNS_REFERENCE.md** - Learn the 11 patterns
3. **SOLID_PRINCIPLES_GUIDE.md** - Learn SOLID principles
4. **STEP_BY_STEP_GUIDE.md** - Detailed learning plan

```bash
cd C:\Users\khushbooo\IdeaProjects\SystemDesign
# Open these .md files in your IDE/editor
```

### Step 2: Understand Basic Systems

**Day 1**: Study Parking Lot System
- Read `ParkingLot.java` thoroughly
- Understand Singleton pattern
- Trace through the code logic
- Ask yourself the key questions

**Day 2**: Study Vending Machine
- Understand all states
- Trace state transitions
- Understand money handling
- See how enums are used

**Day 3**: Study Library Management
- See how to use HashMap
- Understand CRUD operations
- See business logic validation
- Notice use of interfaces

### Step 3: Implement Enhancements

For each system, try implementing:

**Parking Lot**:
- Add pricing calculation
- Add reservation system
- Handle concurrent bookings

**Vending Machine**:
- Add more states (ERROR, OUT_OF_STOCK)
- Add card payments
- Add discount system

**Library**:
- Add fine system
- Add reservation queue
- Add rating system

### Step 4: Move to Intermediate

Once comfortable with basics:

1. Study ATM System
2. Study Rate Limiter
3. Study LRU Cache
4. Practice implementing variations

---

## 📖 Design Patterns Quick Reference

### Patterns Used in This Project

| Pattern | Where | Why |
|---------|-------|-----|
| **Singleton** | ParkingLot, Library, ATM, Bank | One global instance needed |
| **State** | VendingMachine, ATM | Behavior changes with state |
| **Strategy** | RateLimiter | Interchangeable algorithms |
| **Repository** | Library | Data management abstraction |
| **Factory** | Could extend all systems | Object creation |
| **Observer** | Needed for notifications | Event-driven systems |
| **Decorator** | Could extend caching | Add functionality dynamically |

**Learn More**: Read `DESIGN_PATTERNS_REFERENCE.md` for detailed examples of all 11 patterns!

---

## 🎯 SOLID Principles Checklist

Before writing code, remember:

- [ ] **S**ingle Responsibility - Each class has ONE job
- [ ] **O**pen/Closed - Open for extension, closed for modification
- [ ] **L**iskov Substitution - Subclasses can replace parent classes
- [ ] **I**nterface Segregation - Small, focused interfaces
- [ ] **D**ependency Inversion - Depend on abstractions, not concrete classes

**Learn More**: Read `SOLID_PRINCIPLES_GUIDE.md` for deep dive!

---

## 💻 Running the Code

### Compile and Run

```bash
# Navigate to project directory
cd C:\Users\khushbooo\IdeaProjects\SystemDesign

# Compile using javac
javac -d bin src/main/java/org/example/*.java \
              src/main/java/org/example/utils/*.java \
              src/main/java/org/example/basics/parkinglot/*.java \
              src/main/java/org/example/basics/vendingmachine/*.java \
              src/main/java/org/example/basics/librarymanagement/*.java \
              src/main/java/org/example/intermediate/atm/*.java \
              src/main/java/org/example/intermediate/ratelimiter/*.java \
              src/main/java/org/example/intermediate/lrucache/*.java

# Run the main demo
java -cp bin org.example.Main
```

### Or Simply Run from IDE

- Open in IntelliJ IDEA / VS Code / Eclipse
- Right-click Main.java
- Select "Run Main.main()"

---

## 🧪 Testing Your Understanding

### For Each System, Ask Yourself:

**Conceptual Questions**:
1. What design pattern is used and why?
2. What are the key entities and relationships?
3. How do objects communicate?
4. What are the edge cases?

**Implementation Questions**:
1. Why is this method synchronized?
2. What would happen if I removed this check?
3. How would I add a new feature?
4. Is this SOLID? Why or why not?

**Interview Questions**:
1. How would you scale this system?
2. What if you had 1000x more users?
3. How would you add persistence?
4. How would you handle failures?

---

## 📋 Common Interview Scenarios

### Scenario 1: "Design a Parking Lot"
- **Time**: 45 minutes
- **Steps**: Requirements → Design → Code → Optimize
- **Expected**: Clean code + design patterns + SOLID

**Your Advantage**: You have a complete working solution to reference!

### Scenario 2: "Implement a Rate Limiter"
- **Time**: 60 minutes
- **Algorithms**: Token Bucket, Sliding Window, Fixed Window
- **Expected**: Algorithm explanation + implementation

**Your Advantage**: You've implemented multiple strategies!

### Scenario 3: "Design an LRU Cache"
- **Time**: 45 minutes
- **Data Structures**: HashMap + Doubly Linked List
- **Expected**: O(1) get/put operations

**Your Advantage**: You have the optimal implementation ready!

---

## 🎬 What to Do Next

### Option A: Complete All Basics
1. Thoroughly understand Parking Lot
2. Thoroughly understand Vending Machine
3. Thoroughly understand Library
4. Implement 2-3 enhancements for each

### Option B: Move to Intermediate
1. If you feel confident with basics
2. Study ATM System
3. Study Rate Limiter
4. Study LRU Cache

### Option C: Deep Dive on One System
1. Pick Parking Lot
2. Read code multiple times
3. Implement from scratch
4. Add 5+ features
5. Make it production-ready

---

## 🏆 Interview Success Tips

### Before Interview
1. ✅ Study this project thoroughly
2. ✅ Implement everything from scratch (multiple times!)
3. ✅ Practice explaining your code
4. ✅ Discuss trade-offs and design decisions
5. ✅ Write clean, readable code

### During Interview
1. ✅ **Ask clarifying questions** - Don't assume
2. ✅ **Think out loud** - Explain your approach
3. ✅ **Start simple** - MVP first, add features incrementally
4. ✅ **Show design patterns** - Use them appropriately
5. ✅ **Handle edge cases** - Show awareness
6. ✅ **Discuss trade-offs** - Why this design over that?
7. ✅ **Test your code** - Walk through examples

### Code Quality
- Use meaningful names
- Write comments for complex logic
- Follow SOLID principles
- Handle exceptions
- Consider thread-safety
- Make code testable

---

## 📚 Additional Resources

### Java Concepts to Master
- Collections (HashMap, ArrayList, LinkedList)
- Threads & Concurrency (synchronized, locks)
- Interfaces & Abstraction
- Enums
- Anonymous inner classes

### Design Concepts
- OOP principles
- Design patterns (11 in this project)
- SOLID principles (5 in this project)
- Algorithm complexity (Big O)

### Practice
- Implement each system from scratch
- Modify systems with new requirements
- Combine patterns (e.g., Singleton + Observer)
- Optimize for different scenarios

---

## ❓ FAQ

### Q: How long does this take to master?
**A**: 4-8 weeks with 2-3 hours daily. More if you implement from scratch.

### Q: Should I memorize the code?
**A**: No! Understand the logic and design patterns. Code should follow naturally.

### Q: Can I use this in interviews?
**A**: NO! Use this to learn, then implement from scratch in interviews.

### Q: What if I get stuck?
**A**: Review the guides, trace the code, ask yourself the key questions.

### Q: How do I extend this?
**A**: Read the enhancement suggestions in STEP_BY_STEP_GUIDE.md

---

## 🎉 You're Ready!

You now have:

✅ Complete implementation of 6 LLD systems
✅ Design Patterns reference guide
✅ SOLID principles guide
✅ Step-by-step learning plan
✅ Real-world code examples
✅ Interview tips and scenarios

**Start with basics, progress to intermediate, tackle advanced problems, and you'll be ready for any LLD interview! 🚀**

---

## 📞 Summary

- **Phase 1 (Week 1-2)**: Parking Lot, Vending Machine, Library
- **Phase 2 (Week 3-4)**: ATM, Rate Limiter, LRU Cache
- **Phase 3 (Week 5-7)**: Advanced systems (you implement!)

Each system teaches you:
- Design patterns
- SOLID principles
- Concurrency handling
- Algorithm design
- Clean code practices

**Good luck with your LLD interview preparation! You've got this! 💪**

#   L L D  
 