package com.suchocki.bookfair.comparator;

import java.util.HashMap;
import java.util.Map;

import com.suchocki.bookfair.entity.Book;

public class BookComparatorProvider {

	private BookComparatorProvider() {
	}

	private static BookComparator bookByTitleComparator = new BookComparator(BookSortOption.BY_TITLE) {
		@Override
		public int compare(Book b1, Book b2) {
			return b1.getTitle().toLowerCase().compareTo(b2.getTitle().toLowerCase());
		}
	};
	private static BookComparator bookByTitleDescendingComparator = new BookComparator(BookSortOption.BY_TITLE_DESC) {
		@Override
		public int compare(Book b1, Book b2) {
			return -(b1.getTitle().toLowerCase().compareTo(b2.getTitle().toLowerCase()));
		}
	};
	private static BookComparator bookByPriceComparator = new BookComparator(BookSortOption.BY_PRICE) {
		@Override
		public int compare(Book b1, Book b2) {
			return b1.getPrice().compareTo(b2.getPrice());
		}
	};
	private static BookComparator bookByPriceDescendingComparator = new BookComparator(BookSortOption.BY_PRICE_DESC) {
		@Override
		public int compare(Book b1, Book b2) {
			return -(b1.getPrice().compareTo(b2.getPrice()));
		}
	};

	private static Map<BookSortOption, BookComparator> comparators = createComparatorsMap();

	private static Map<BookSortOption, BookComparator> createComparatorsMap() {
		Map<BookSortOption, BookComparator> comparators = new HashMap<>();
		addComparatorToMap(comparators, bookByTitleComparator);
		addComparatorToMap(comparators, bookByTitleDescendingComparator);
		addComparatorToMap(comparators, bookByPriceComparator);
		addComparatorToMap(comparators, bookByPriceDescendingComparator);
		return comparators; // in fact, this function could be void, but it does this strange return to
							// elegantly initialize comparators (in static way)
	}

	private static void addComparatorToMap(Map<BookSortOption, BookComparator> map, BookComparator comparator) {
		map.put(comparator.getRelativeSortOption(), comparator);
	}

	public static BookComparator getBookComparator(BookSortOption sortOption) {
		BookComparator resultComparator = comparators.get(sortOption);
		if (resultComparator == null) {
			resultComparator = bookByTitleComparator;
		}
		return resultComparator;
	}

}
