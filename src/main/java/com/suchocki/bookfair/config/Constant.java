package com.suchocki.bookfair.config;

import com.suchocki.bookfair.entity.Book;

//There will be constant values used many times in different classes
public final class Constant {
	private Constant() {
	}

	public static final int MAX_BOOK_DESCRIPTION_SIZE = 1000;
	public static final int ALL_SCHOOL_CLASS_TYPE = -1;
	// public static final String NOT_CERTAIN_TOPIC_BOOK_TYPE = "Dowolny";

	public static final String REQUIRED_FIELD_MESSAGE = "Pole wymagane";

	public static final String TOO_LARGE_DESCRIPTION_MESSAGE = tooLargeDescriptionMessage();
	public static final String INCORRECT_PRICE_MESSAGE = incorrectPriceMessage();
	public static final String INCORRECT_CURRENT_PASSWORD_MSG = "B³êdne aktualne has³o!";
	public static final String DIFFERENT_NEW_PASSWORDS_MSG = "Pola z nowymi has³ami zawieraj¹ ró¿ne wartoœci!";
	public static final String EMPTY_FIELDS_MSG = "Proszê wype³niæ wszystkie pola!";
	public static final String PASSWORD_EDITED_MSG = "Has³o zmienione!";

	public static final String BOOK_EDITED_MSG = "Ksiazka zaktualizowana!"; // na razie serwer ne obs³uguje polskich
																			// znaków w url
	public static final String BOOK_DELETED_MSG = "Ksi¹¿ka usuniêta!";
	public static final String DELETE_CONFIRMATION_MSG = "Czy na pewno chcesz usun¹æ ksi¹¿kê?";

	private static String incorrectPriceMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cena musi zawieraæ siê w przedziale od ");
		builder.append(Book.MIN_PRICE);
		builder.append(" do ");
		builder.append(Book.MAX_PRICE);
		builder.append("!");
		return builder.toString();
	}

	private static String tooLargeDescriptionMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append("Za d³ugi opis. Maksymalna d³ugoœæ to ");
		builder.append(MAX_BOOK_DESCRIPTION_SIZE);
		builder.append(" znaków!");
		return builder.toString();
	}
}
