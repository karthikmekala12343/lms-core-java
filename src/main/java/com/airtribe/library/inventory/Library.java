package com.airtribe.library.inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.airtribe.library.book.Book;
import com.airtribe.library.branch.LibraryBranch;
import com.airtribe.library.lending.Loan;
import com.airtribe.library.lending.Reservation;
import com.airtribe.library.patron.Patron;
import com.airtribe.library.recommendation.RecommendationStrategy;
import com.airtribe.library.search.SearchStrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main library management system.
 * Follows SOLID principles: Single responsibility (manage books, patrons, loans),
 * Open-closed (extensible with strategies), etc.
 */
public class Library {
    private static final Logger logger = LoggerFactory.getLogger(Library.class);

    private List<Book> books;
    private List<Patron> patrons;
    private List<Loan> loans;
    private List<Reservation> reservations;
    private List<LibraryBranch> branches;

    public Library() {
        this.books = new ArrayList<>();
        this.patrons = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.branches = new ArrayList<>();
    }

    // Book management
    public void addBook(Book book) {
        books.add(book);
        logger.info("Book added: {}", book.getTitle());
    }

    public void removeBook(Book book) {
        books.remove(book);
        logger.info("Book removed: {}", book.getTitle());
    }

    public void updateBook(Book oldBook, Book newBook) {
        int index = books.indexOf(oldBook);
        if (index != -1) {
            books.set(index, newBook);
            logger.info("Book updated: {} to {}", oldBook.getTitle(), newBook.getTitle());
        }
    }

    public List<Book> searchBooks(String query, SearchStrategy strategy) {
        return strategy.search(books, query);
    }

    // Patron management
    public void addPatron(Patron patron) {
        patrons.add(patron);
        logger.info("Patron added: {}", patron.getName());
    }

    public void updatePatron(Patron oldPatron, Patron newPatron) {
        int index = patrons.indexOf(oldPatron);
        if (index != -1) {
            patrons.set(index, newPatron);
            logger.info("Patron updated: {} to {}", oldPatron.getName(), newPatron.getName());
        }
    }

    // Lending process
    public boolean checkoutBook(Book book, Patron patron) {
        if (book.isAvailable() && patrons.contains(patron)) {
            book.setAvailable(false);
            patron.getBorrowedBooks().add(book);
            Loan loan = new Loan(book, patron, LocalDate.now(), LocalDate.now().plusDays(14));
            loans.add(loan);
            logger.info("Book checked out: {} by {}", book.getTitle(), patron.getName());
            return true;
        }
        logger.warn("Checkout failed for book: {} by {}", book.getTitle(), patron.getName());
        return false;
    }

    public void returnBook(Book book, Patron patron) {
        if (patron.getBorrowedBooks().contains(book)) {
            book.setAvailable(true);
            patron.getBorrowedBooks().remove(book);
            patron.getBorrowingHistory().add(book.getTitle());
            loans.stream()
                    .filter(loan -> loan.getBook().equals(book) && loan.getPatron().equals(patron) && loan.getReturnDate() == null)
                    .findFirst()
                    .ifPresent(loan -> loan.setReturnDate(LocalDate.now()));
            logger.info("Book returned: {} by {}", book.getTitle(), patron.getName());
            // Notify reservations
            notifyReservations(book);
        }
    }

    // Reservation system
    public void reserveBook(Book book, Patron patron) {
        if (!book.isAvailable() && !reservations.stream().anyMatch(r -> r.getBook().equals(book) && r.getPatron().equals(patron))) {
            reservations.add(new Reservation(book, patron, LocalDate.now()));
            logger.info("Book reserved: {} by {}", book.getTitle(), patron.getName());
        }
    }

    private void notifyReservations(Book book) {
        List<Reservation> relevantReservations = reservations.stream()
                .filter(r -> r.getBook().equals(book))
                .collect(Collectors.toList());
        for (Reservation res : relevantReservations) {
            logger.info("Notification: Book {} is now available for patron {}", book.getTitle(), res.getPatron().getName());
            // In real system, send email or something
        }
        reservations.removeAll(relevantReservations);
    }

    // Recommendations
    public List<Book> recommendBooks(Patron patron, RecommendationStrategy strategy) {
        return strategy.recommend(patron, books);
    }

    // Multi-branch
    public void addBranch(LibraryBranch branch) {
        branches.add(branch);
    }

    public void transferBook(Book book, LibraryBranch fromBranch, LibraryBranch toBranch) {
        fromBranch.transferBook(book, toBranch);
        logger.info("Book transferred: {} from {} to {}", book.getTitle(), fromBranch.getName(), toBranch.getName());
    }

    // Getters for inventory
    public List<Book> getAvailableBooks() {
        return books.stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    public List<Book> getBorrowedBooks() {
        return books.stream().filter(book -> !book.isAvailable()).collect(Collectors.toList());
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Patron> getPatrons() {
        return patrons;
    }

    public List<Loan> getLoans() {
        return loans;
    }
}