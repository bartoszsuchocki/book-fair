package com.suchocki.bookfair.comparator;

public enum BookSortOption {
	BY_TITLE("Po tytule rosnaco"),BY_TITLE_DESC("Po tytule malejaco"), BY_PRICE("Po cenie rosnaco"), BY_PRICE_DESC("Po cenie malejaco");
	private String textRepresentation; // this will enable adding multi-language support in the future

	private BookSortOption(String textRepresentation) {
		this.textRepresentation = textRepresentation;
	}

	public void setTextRepresentation(String textRepresentation) {
		this.textRepresentation = textRepresentation;
	}

	public String getTextRepresentation() {
		return textRepresentation;
	}

	@Override
	public String toString() {
		return textRepresentation;
	}
}
