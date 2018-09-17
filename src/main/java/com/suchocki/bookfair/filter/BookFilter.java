package com.suchocki.bookfair.filter;

import org.springframework.stereotype.Component;

import com.suchocki.bookfair.comparator.BookComparator;
import com.suchocki.bookfair.comparator.BookComparatorProvider;
import com.suchocki.bookfair.comparator.BookSortOption;
import com.suchocki.bookfair.entity.Book;

//Helper class that enables retrieving bookComparator outside of Book object when parsing view-books constraints (chosen by app user)
//Thanks to this class, we do not need to use spring:bind tag in "view-books.jsp" instead of spring form tag.
@Component
public class BookFilter {
	private Book desiredBook;
	private BookComparator bookComparator;

	public BookFilter() {
		bookComparator = BookComparatorProvider.getBookComparator(BookSortOption.BY_TITLE);
	}

	public Book getDesiredBook() {
		return desiredBook;
	}

	public void setDesiredBook(Book desiredBook) {
		this.desiredBook = desiredBook;
	}

	public BookComparator getBookComparator() {
		return bookComparator;
	}

	public void setBookComparator(BookComparator bookComparator) {
		this.bookComparator = bookComparator;
	}

}
