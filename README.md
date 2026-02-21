# Library Management System

This is a Java-based Library Management System that demonstrates Object-Oriented Programming (OOP) concepts, SOLID principles, and design patterns.

## Features

### Core Requirements
- **Book Management**: Add, remove, update books with title, author, ISBN, and publication year.
- **Search Functionality**: Search books by title, author, or ISBN using Strategy pattern.
- **Patron Management**: Add patrons and track their borrowing history.
- **Lending Process**: Checkout and return books, track loans.
- **Inventory Management**: Track available and borrowed books.

### Optional Extensions
- **Reservation System**: Reserve unavailable books and get notifications when available.
- **Recommendation System**: Recommend books based on borrowing history using Strategy pattern.
- **Multi-branch Support**: Manage multiple library branches and transfer books between them.

## Design Patterns Used
- **Strategy Pattern**: For search (TitleSearch, AuthorSearch, IsbnSearch) and recommendations (HistoryBasedRecommendation).
- **Observer Pattern**: Implicit in reservation notifications (logging notifications).

## SOLID Principles
- **Single Responsibility**: Each class has one responsibility (e.g., Book for data, Library for management).
- **Open-Closed**: Extensible with new search strategies, recommendations without modifying existing code.
- **Liskov Substitution**: Subclasses can replace base classes.
- **Interface Segregation**: Interfaces are specific (SearchStrategy, RecommendationStrategy).
- **Dependency Inversion**: High-level modules depend on abstractions.

## Class Diagram
- View docs/class-diagram.png

## How to Run

1. Ensure Java 8+ and Maven are installed.
4. Run `mvn compile` to compile.
5. Run `mvn exec:java -Dexec.mainClass="com.library.LibraryManagementSystem"` to run the demo.

## Dependencies
- SLF4J for logging API
- Logback for logging implementation
