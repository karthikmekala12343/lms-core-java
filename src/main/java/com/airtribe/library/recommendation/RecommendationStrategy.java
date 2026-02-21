package com.airtribe.library.recommendation;

import java.util.List;

import com.airtribe.library.book.Book;
import com.airtribe.library.patron.Patron;

/**
 * Strategy interface for recommending books.
 */
public interface RecommendationStrategy {
    
    /**
     * Recommend.
     *
     * @param patron the patron
     * @param allBooks the all books
     * @return the list
     */
    List<Book> recommend(Patron patron, List<Book> allBooks);
}