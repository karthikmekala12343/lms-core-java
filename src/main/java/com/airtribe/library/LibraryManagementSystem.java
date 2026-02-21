package com.airtribe.library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.airtribe.library.book.Book;
import com.airtribe.library.branch.LibraryBranch;
import com.airtribe.library.inventory.Library;
import com.airtribe.library.patron.Patron;
import com.airtribe.library.recommendation.HistoryBasedRecommendation;
import com.airtribe.library.search.AuthorSearch;

/**
 * Main class to demonstrate the Library Management System.
 */
public class LibraryManagementSystem {
    private static final Logger logger = LoggerFactory.getLogger(LibraryManagementSystem.class);

    public static void main(String[] args) {
        Library library = new Library();

        // Add books
        Book book1 = new Book("1984", "George Orwell", "1234567890", 1949);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "0987654321", 1960);
        Book book3 = new Book("Animal Farm", "George Orwell", "1122334455", 1945);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Add patrons
        Patron patron1 = new Patron("P001", "Alice");
        Patron patron2 = new Patron("P002", "Bob");
        library.addPatron(patron1);
        library.addPatron(patron2);

        // Search books
        System.out.println("Books by George Orwell:");
        library.searchBooks("George Orwell", new AuthorSearch()).forEach(System.out::println);

        // Checkout
        library.checkoutBook(book1, patron1);
        library.checkoutBook(book2, patron2);

        // Reserve
        library.reserveBook(book1, patron2);

        // Return
        library.returnBook(book1, patron1);

        // Recommend
        System.out.println("Recommendations for Alice:");
        library.recommendBooks(patron1, new HistoryBasedRecommendation()).forEach(System.out::println);

        // Branches
        LibraryBranch branch1 = new LibraryBranch("Main Branch");
        LibraryBranch branch2 = new LibraryBranch("Downtown Branch");
        library.addBranch(branch1);
        library.addBranch(branch2);
        branch1.addBook(book3);
        library.transferBook(book3, branch1, branch2);

        logger.info("Library system demo completed.");
    }
}