# Low-Level Design (LLD) Interview Preparation Guide

## 📚 Core Concepts to Master

### 1. SOLID Principles
- **S**ingle Responsibility Principle (SRP)
- **O**pen/Closed Principle (OCP)
- **L**iskov Substitution Principle (LSP)
- **I**nterface Segregation Principle (ISP)
- **D**ependency Inversion Principle (DIP)

### 2. Core Design Patterns

#### Creational Patterns
- **Singleton**: Single instance of a class
- **Factory**: Object creation abstraction
- **Builder**: Complex object construction
- **Prototype**: Object cloning

#### Structural Patterns
- **Adapter**: Interface compatibility
- **Decorator**: Add responsibilities dynamically
- **Facade**: Simplified interface
- **Proxy**: Control access to object

#### Behavioral Patterns
- **Observer**: One-to-many notification
- **Strategy**: Algorithm encapsulation
- **State**: Object behavior based on state
- **Template Method**: Algorithm skeleton
- **Chain of Responsibility**: Pass request along chain

### 3. OOP Concepts
- Encapsulation, Inheritance, Polymorphism
- Abstraction, Composition over Inheritance
- Interface and Abstract Classes

---

## 📋 Top 15+ LLD Interview Questions

### WEEK 1-2: BASICS (Foundation)
| # | Problem | Difficulty | Key Patterns | Concepts |
|---|---------|-----------|--------------|----------|
| 1 | Parking Lot | ⭐⭐ | Strategy, Factory | Enums, Collections |
| 2 | Vending Machine | ⭐⭐ | State | State Machine, Enums |
| 3 | Library Management | ⭐⭐ | Strategy, Repository | Collections, CRUD |

### WEEK 3-4: INTERMEDIATE (Moderate Complexity)
| # | Problem | Difficulty | Key Patterns | Concepts |
|---|---------|-----------|--------------|----------|
| 4 | ATM System | ⭐⭐⭐ | State, Singleton | Transactions, Security |
| 5 | Rate Limiter | ⭐⭐⭐ | Strategy, Singleton | Algorithms, Concurrency |
| 6 | LRU Cache | ⭐⭐⭐ | Strategy | HashMap, Linked Lists |
| 7 | Hotel Management | ⭐⭐⭐ | Observer, Strategy | Notifications, Booking |

### WEEK 5-7: ADVANCED (Complex Systems)
| # | Problem | Difficulty | Key Patterns | Concepts |
|---|---------|-----------|--------------|----------|
| 8 | Food Delivery | ⭐⭐⭐⭐ | Observer, Strategy | Notifications, Queues |
| 9 | Elevator System | ⭐⭐⭐⭐ | State, Observer | Concurrency, Scheduling |
| 10 | Online Shopping | ⭐⭐⭐⭐ | Strategy, Observer | Cart, Payments, Inventory |
| 11 | Movie Ticket Booking | ⭐⭐⭐⭐ | Strategy, Observer | Concurrency, Reservations |
| 12 | Uber/Ride Sharing | ⭐⭐⭐⭐⭐ | Observer, Strategy | Matching, Real-time updates |
| 13 | Chess | ⭐⭐⭐⭐⭐ | Strategy, State | Game Logic, Move Validation |
| 14 | Split Expense | ⭐⭐⭐ | Strategy | Graph, Complex Calculations |
| 15 | Comment System | ⭐⭐⭐ | Composite, Observer | Nested Structures |

---

## 🔑 Key Implementation Tips

### 1. Start with Clear Requirements
- Read problem statement carefully
- Ask clarifying questions
- List down entities and relationships

### 2. Design Phase
- Create class diagram
- Identify design patterns needed
- Plan use cases

### 3. Implementation Best Practices
- Use meaningful class and method names
- Follow SOLID principles
- Keep classes focused on single responsibility
- Use composition over inheritance
- Leverage interfaces for flexibility

### 4. Testing Considerations
- Write test cases for happy path
- Handle edge cases
- Validate constraints
- Check concurrent access (where applicable)

---

## 📂 Project Structure

```
SystemDesign/
├── src/main/java/org/example/
│   ├── patterns/           # Design pattern templates
│   ├── utils/              # Reusable utilities (ID generators, base classes)
│   ├── basics/             # Week 1-2 problems
│   ├── intermediate/       # Week 3-4 problems
│   ├── advanced/           # Week 5-7 problems
│   └── Main.java           # Test runner
└── LLD_INTERVIEW_GUIDE.md  # This file
```

---

## 🚀 Getting Started

1. **Understand the pattern** - Review design pattern definition
2. **Design the system** - Create entities and relationships
3. **Implement step by step** - Start with core classes
4. **Add features** - Build on top of basic implementation
5. **Handle edge cases** - Consider concurrency, constraints
6. **Review and refactor** - Apply SOLID principles

---

## 💡 Interview Tips

1. **Clarify Requirements** - Always ask for requirements before coding
2. **Think out loud** - Explain your design decisions
3. **Start simple** - Begin with MVP, add features incrementally
4. **Show trade-offs** - Discuss pros/cons of different approaches
5. **Handle edge cases** - Show awareness of potential issues
6. **Write clean code** - Good naming, proper structure, DRY principle
7. **Test your code** - Run through test cases verbally

---

## 📊 Learning Timeline

- **Week 1-2**: Basics (Parking Lot, Vending Machine, Library Management)
- **Week 3-4**: Intermediate (ATM, Rate Limiter, LRU Cache, Hotel)
- **Week 5-7**: Advanced (Food Delivery, Elevator, Shopping, Ticket Booking, Ride Sharing)
- **Week 8**: Revision + Complex problems (Chess, Uber)

