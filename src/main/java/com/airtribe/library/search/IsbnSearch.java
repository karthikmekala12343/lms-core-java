package com.airtribe.library.search;

import java.util.List;
import java.util.stream.Collectors;

import com.airtribe.library.book.Book;

/**
 * Search strategy for searching books by ISBN.
 */
public class IsbnSearch implements SearchStrategy {
	
	/**
	 * Search.
	 *
	 * @param books the books
	 * @param query the query
	 * @return the list
	 */
	@Override
	public List<Book> search(List<Book> books, String query) {
		return books.stream().filter(book -> book.getIsbn().equalsIgnoreCase(query)).collect(Collectors.toList());
	}
}