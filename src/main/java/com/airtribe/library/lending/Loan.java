package com.airtribe.library.lending;

import java.time.LocalDate;
import java.util.Objects;

import com.airtribe.library.book.Book;
import com.airtribe.library.patron.Patron;

/**
 * Represents a loan of a book to a patron.
 */
public class Loan {
    private Book book;
    private Patron patron;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Loan(Book book, Patron patron, LocalDate checkoutDate, LocalDate dueDate) {
        this.book = book;
        this.patron = patron;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }

    // Getters and setters
    public Book getBook() {
        return book;
    }

    public Patron getPatron() {
        return patron;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(book, loan.book) && Objects.equals(patron, loan.patron) && Objects.equals(checkoutDate, loan.checkoutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, patron, checkoutDate);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "book=" + book.getTitle() +
                ", patron=" + patron.getName() +
                ", checkoutDate=" + checkoutDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}