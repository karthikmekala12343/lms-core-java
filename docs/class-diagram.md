classDiagram

%% =========================
%% Core Entities
%% =========================

    class Book {
        -String title
        -String author
        -String isbn
        -int publicationYear
        -boolean available
        +Book(String, String, String, int)
        +getTitle()
        +setTitle(String)
        +getAuthor()
        +setAuthor(String)
        +getIsbn()
        +setIsbn(String)
        +getPublicationYear()
        +setPublicationYear(int)
        +isAvailable()
        +setAvailable(boolean)
        +equals(Object)
        +hashCode()
        +toString()
    }

    class LibraryBranch {
        -String name
        -List~Book~ books
        +LibraryBranch(String)
        +getName()
        +getBooks()
        +addBook(Book)
        +removeBook(Book)
        +transferBook(Book, LibraryBranch)
    }

    class Library {
        -List~Book~ books
        -List~Patron~ patrons
        -List~Loan~ loans
        -List~Reservation~ reservations
        -List~LibraryBranch~ branches
        +Library()
        +addBook(Book)
        +removeBook(Book)
        +updateBook(Book, Book)
        +searchBooks(String, SearchStrategy)
        +addPatron(Patron)
        +updatePatron(Patron, Patron)
        +checkoutBook(Book, Patron)
        +returnBook(Book, Patron)
        +reserveBook(Book, Patron)
        +recommendBooks(Patron, RecommendationStrategy)
        +addBranch(LibraryBranch)
        +transferBook(Book, LibraryBranch, LibraryBranch)
        +getAvailableBooks()
        +getBorrowedBooks()
        +getBooks()
        +getPatrons()
        +getLoans()
    }

    class Loan {
        -Book book
        -Patron patron
        -LocalDate checkoutDate
        -LocalDate dueDate
        -LocalDate returnDate
        +Loan(Book, Patron, LocalDate, LocalDate)
        +getBook()
        +getPatron()
        +getCheckoutDate()
        +getDueDate()
        +getReturnDate()
        +setReturnDate(LocalDate)
        +equals(Object)
        +hashCode()
        +toString()
    }

    class Reservation {
        -Book book
        -Patron patron
        -LocalDate reservationDate
        +Reservation(Book, Patron, LocalDate)
        +getBook()
        +getPatron()
        +getReservationDate()
        +equals(Object)
        +hashCode()
        +toString()
    }

    class Patron {
        -String id
        -String name
        -List~Book~ borrowedBooks
        -List~String~ borrowingHistory
        +Patron(String, String)
        +getId()
        +setId(String)
        +getName()
        +setName(String)
        +getBorrowedBooks()
        +getBorrowingHistory()
        +equals(Object)
        +hashCode()
        +toString()
    }

    class RecommendationStrategy {
        <<interface>>
        +recommend(Patron, List~Book~)
    }

    class HistoryBasedRecommendation {
        +recommend(Patron, List~Book~)
    }

    class SearchStrategy {
        <<interface>>
        +search(List~Book~, String)
    }

    class AuthorSearch {
        +search(List~Book~, String)
    }

    class IsbnSearch {
        +search(List~Book~, String)
    }

    class TitleSearch {
        +search(List~Book~, String)
    }

%% =========================
%% Relationships
%% =========================
    Library --> Book : manages *
    Library --> Patron : manages *
    Library --> Loan : manages *
    Library --> Reservation : manages *
    Library --> LibraryBranch : manages *
    LibraryBranch --> Book : contains *
    Loan --> Book : references
    Loan --> Patron : references
    Reservation --> Book : references
    Reservation --> Patron : references
    Patron --> Book : borrows *
    Library --> SearchStrategy : uses
    Library --> RecommendationStrategy : uses
    SearchStrategy <|.. AuthorSearch : implements
    SearchStrategy <|.. IsbnSearch : implements
    SearchStrategy <|.. TitleSearch : implements
    RecommendationStrategy <|.. HistoryBasedRecommendation : implements