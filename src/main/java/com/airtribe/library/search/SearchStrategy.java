package com.airtribe.library.search;

import java.util.List;

import com.airtribe.library.book.Book;

/**
 * Strategy interface for searching books.
 */
public interface SearchStrategy {
    
    /**
     * Search.
     *
     * @param books the books
     * @param query the query
     * @return the list
     */
    List<Book> search(List<Book> books, String query);
}