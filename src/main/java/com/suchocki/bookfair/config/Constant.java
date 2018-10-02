package com.suchocki.bookfair.config;

//There will be constant values used many times in different classes
public final class Constant {
	private Constant() {}
	
	public static final String REQUIRED_FIELD_MESSAGE = "Pole wymagane";
	public static final int ALL_SCHOOL_CLASS_TYPE = -1;

	public static final String INCORRECT_CURRENT_PASSWORD_MSG = "B��dne aktualne has�o!";
	public static final String DIFFERENT_NEW_PASSWORDS_MSG = "Pola z nowymi has�ami zawieraj� r�ne warto�ci!";
	public static final String EMPTY_FIELDS_MSG = "Prosz� wype�ni� wszystkie pola!";
	public static final String PASSWORD_EDITED_MSG = "Has�o zmienione!";

	public static final String BOOK_EDITED_MSG = "Ksiazka zaktualizowana!"; // na razie serwer ne obs�uguje polskich
																			// znak�w w url
	public static final String BOOK_DELETED_MSG = "Ksi��ka usuni�ta!";

	public static final String DELETE_CONFIRMATION_MSG = "Czy na pewno chcesz usun�� ksi��k�?";

}
