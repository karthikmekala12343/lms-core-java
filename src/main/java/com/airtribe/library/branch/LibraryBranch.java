package com.airtribe.library.branch;

import java.util.ArrayList;
import java.util.List;

import com.airtribe.library.book.Book;

/**
 * Represents a library branch.
 */
public class LibraryBranch {
    private String name;
    private List<Book> books;

    public LibraryBranch(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void transferBook(Book book, LibraryBranch toBranch) {
        if (books.contains(book)) {
            books.remove(book);
            toBranch.addBook(book);
        }
    }
}