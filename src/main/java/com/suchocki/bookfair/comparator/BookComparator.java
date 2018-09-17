package com.suchocki.bookfair.comparator;

import java.util.Comparator;

import com.suchocki.bookfair.entity.Book;

public abstract class BookComparator implements Comparator<Book> {

	protected BookSortOption relativeSortOption;

	@Override
	public abstract int compare(Book arg0, Book arg1);

	public BookSortOption getRelativeSortOption() {
		return relativeSortOption;
	}

	public BookComparator(BookSortOption relevantSortOption) {
		this.relativeSortOption = relevantSortOption;
	}
}
