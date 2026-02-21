package com.airtribe.library.patron;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.airtribe.library.book.Book;

/**
 * Represents a patron (library member).
 */
public class Patron {
	private String id;
	private String name;
	private List<Book> borrowedBooks;
	private List<String> borrowingHistory;

	public Patron(String id, String name) {
		this.id = id;
		this.name = name;
		this.borrowedBooks = new ArrayList<>();
		this.borrowingHistory = new ArrayList<>();
	}

	// Getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBorrowedBooks() {
		return borrowedBooks;
	}

	public List<String> getBorrowingHistory() {
		return borrowingHistory;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Patron patron = (Patron) o;
		return Objects.equals(id, patron.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Patron{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", borrowedBooks=" + borrowedBooks.size()
				+ ", borrowingHistory=" + borrowingHistory.size() + '}';
	}
}