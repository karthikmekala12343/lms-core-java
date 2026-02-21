package com.airtribe.library.recommendation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.airtribe.library.book.Book;
import com.airtribe.library.patron.Patron;

/**
 * Recommendation strategy based on borrowing history.
 * Recommends books by authors the patron has borrowed before.
 */
public class HistoryBasedRecommendation implements RecommendationStrategy {
    
    /**
     * Recommend.
     *
     * @param patron the patron
     * @param allBooks the all books
     * @return the list
     */
    @Override
    public List<Book> recommend(Patron patron, List<Book> allBooks) {
        Set<String> borrowedAuthors = patron.getBorrowingHistory().stream()
                .map(title -> allBooks.stream()
                        .filter(book -> book.getTitle().equals(title))
                        .findFirst()
                        .map(Book::getAuthor)
                        .orElse(""))
                .filter(author -> !author.isEmpty())
                .collect(Collectors.toSet());

        return allBooks.stream()
                .filter(book -> borrowedAuthors.contains(book.getAuthor()) && book.isAvailable())
                .distinct()
                .collect(Collectors.toList());
    }
}