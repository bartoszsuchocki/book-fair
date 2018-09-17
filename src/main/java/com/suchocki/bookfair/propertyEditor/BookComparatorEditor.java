package com.suchocki.bookfair.propertyEditor;

import java.beans.PropertyEditorSupport;
import java.util.Comparator;

import com.suchocki.bookfair.comparator.BookComparator;
import com.suchocki.bookfair.comparator.BookComparatorProvider;
import com.suchocki.bookfair.comparator.BookSortOption;
import com.suchocki.bookfair.entity.Book;

public class BookComparatorEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Comparator<Book> bookComparator = null;
		for (BookSortOption option : BookSortOption.values()) {
			if (option.toString().equals(text)) {
				bookComparator = BookComparatorProvider.getBookComparator(option);
			}
		}
		this.setValue(bookComparator);
	}

	@Override
	public String getAsText() {
		System.out.println("BookComparatorEditor.getAsText(): getValue(): " + getValue());
		if (getValue() != null) {
			System.out.println("BookComparatorEditor.getAsText().getValue.getClass() " + getValue().getClass());
		}
		BookComparator bookComparator = (BookComparator) getValue();
		if (bookComparator == null) {
			return "null";
		}
		return bookComparator.getRelativeSortOption().toString();
	}
}
