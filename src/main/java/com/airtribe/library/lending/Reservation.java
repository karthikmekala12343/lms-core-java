package com.airtribe.library.lending;

import java.time.LocalDate;
import java.util.Objects;

import com.airtribe.library.book.Book;
import com.airtribe.library.patron.Patron;

/**
 * Represents a reservation of a book by a patron.
 */
public class Reservation {
	private Book book;
	private Patron patron;
	private LocalDate reservationDate;

	public Reservation(Book book, Patron patron, LocalDate reservationDate) {
		this.book = book;
		this.patron = patron;
		this.reservationDate = reservationDate;
	}

	// Getters
	public Book getBook() {
		return book;
	}

	public Patron getPatron() {
		return patron;
	}

	public LocalDate getReservationDate() {
		return reservationDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Reservation that = (Reservation) o;
		return Objects.equals(book, that.book) && Objects.equals(patron, that.patron);
	}

	@Override
	public int hashCode() {
		return Objects.hash(book, patron);
	}

	@Override
	public String toString() {
		return "Reservation{" + "book=" + book.getTitle() + ", patron=" + patron.getName() + ", reservationDate="
				+ reservationDate + '}';
	}
}