package com.suchocki.bookfair.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.suchocki.bookfair.config.exception.WrongConfigFileFormatException;
import com.suchocki.bookfair.entity.Book;
import com.suchocki.bookfair.entity.School;
import com.suchocki.bookfair.entity.User;

//There will be constant values used many times in different classes
public final class Constant {
	private Constant() {
	}

	private static Logger logger = Logger.getLogger(Constant.class.getName());

	// helper map that contains enabled school types options as objects of
	// SchoolType class
	private static final Map<String, SchoolType> schoolTypes = initSchoolTypes();

	// BELOW MAPS CONTAIN OPTIONS TO USE IN JSP FORMS - VALUES AND LABELS

	public static final Map<String, String> SCHOOL_TYPES = initSchoolTypeNamesMap();
	public static final Map<String, String> TOPICS = initTopics();
	public static final Map<Integer, String> SCHOOL_CLASSES = initSchoolClasses();
	public static final Map<String, String> BOOK_STATES = initBookStates();
	public static final Map<Integer, String> PRICE_RANGES = initPriceRanges();

	// BELOW FIELDS CONTAIN VALUES AND LABELS FOR OPTIONS "ALL" - WHEN USER DID NOT
	// DECIDE ABOUT SPECIFIC VALUE

	private static final String UNIVERSAL_STRING_PROPERTY_ALL_VALUE = "all";
	private static final String UNIVERSAL_STRING_PROPERTY_ALL_LABEL = "Wszystkie";

	public static final String ALL_SCHOOL_CLASS_LABEL = "Nie dotyczy/dotyczy wielu/dowolna";
	public static final String ALL_SCHOOL_TYPE_VALUE = UNIVERSAL_STRING_PROPERTY_ALL_VALUE;
	public static final String ALL_SCHOOL_TYPE_LABEL = UNIVERSAL_STRING_PROPERTY_ALL_LABEL;
	public static final String ALL_BOOK_STATE_VALUE = UNIVERSAL_STRING_PROPERTY_ALL_VALUE;
	public static final String ALL_BOOK_STATE_LABEL = UNIVERSAL_STRING_PROPERTY_ALL_LABEL;
	public static final String ALL_TOPIC_VALUE = UNIVERSAL_STRING_PROPERTY_ALL_VALUE;
	public static final String ALL_TOPIC_LABEL = UNIVERSAL_STRING_PROPERTY_ALL_LABEL;
	public static final String ALL_SCHOOL_VALUE = ""; // schools are objects, so we want to store null in db, not school
														// with some value -> "" submitted in form will be parsed to
														// null
	public static final String ALL_SCHOOL_LABEL = UNIVERSAL_STRING_PROPERTY_ALL_LABEL;
	public static final int ALL_PRICE_VALUE = Book.MAX_PRICE;
	public static final String ALL_PRICE_LABEL = "Dowolna";
	public static final int ALL_SCHOOL_CLASS_VALUE = -1;

	// BELOW FIELDS CONTAIN PROPERTIES WIDELY USED IN PROGRAM
	private static final int MAX_SCHOOL_CLASSES_NUMBER = 8;
	public static final int MAX_BOOK_DESCRIPTION_SIZE = 1000;

	// BELOW FIELDS CONTAIN MESSAGES DISPLAYED TO USER IN PROGRAM
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

	// Object used when user is not authenticated (thanks to that, i do not have to
	// use null in such situation)
	public static User ANONYMOUS_USER = new User("anonymous", "anonymous", "anonymous", "anonymous",
			"anonymous@anon.com", new School("anonumous", "anonymous"));

	// BELOW METHODS ARE USED TO OBTAIN OPTION LABEL FROM OPTION VALUE (OPTIONS FROM
	// JSP FORM) STORED IN APPROPRIATE MAP

	public static String getTopic(String key) {
		if (TOPICS == null) {
			logger.warning("No topics found!");
			return "";
		}
		return TOPICS.get(key);
	}

	public static String getSchoolType(String key) {
		if (SCHOOL_TYPES == null) {
			logger.warning("No school types found!");
			return "";
		}
		return SCHOOL_TYPES.get(key);
	}

	public static int getSchoolTypeClassesNumber(String key) { // returns -1 if type not found
		if (schoolTypes != null) {
			SchoolType type = schoolTypes.get(key);
			if (type != null) {
				return schoolTypes.get(key).classes;
			} else {
				logger.warning("No school type found: " + key);
				return -1;
			}
		} else {
			logger.warning("No school types found!");
			return -1;
		}
	}

	public static String getSchoolClass(int key) {
		if (SCHOOL_CLASSES == null) {
			logger.warning("No enabled schoolClasses!");
			return "";
		}
		return SCHOOL_CLASSES.get(key);
	}

	public static String getBookState(String key) {
		return BOOK_STATES.get(key);
	}

	// BELOW METHODS ARE RESPONSIBLE FOR INITIALIZING/BUILDING ABOVE FIELDS/MAPS

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

	private static Map<String, String> initTopics() {
		String line;
		Map<String, String> topics = new LinkedHashMap<>();

		// topics.put(ALL_TOPIC_VALUE, ALL_TOPIC_LABEL);

		try {
			InputStreamReader isr = new InputStreamReader(Constant.class.getResourceAsStream("/topics"));
			BufferedReader bufferedReader = new BufferedReader(isr);
			while ((line = bufferedReader.readLine()) != null) {
				int separatorIndex = line.indexOf("-");
				String name = line.substring(0, separatorIndex);
				String value = line.substring(separatorIndex + 1);
				topics.put(name, value);
			}
			bufferedReader.close();
		} catch (IOException e) {
			logger.warning(e.getMessage());
		}
		return topics;
	}

	private static Map<String, SchoolType> initSchoolTypes() {
		String line;
		Map<String, SchoolType> schoolTypes = new HashMap<>();
		// schoolTypes.put(ALL_SCHOOL_TYPE_VALUE, new SchoolType(ALL_SCHOOL_TYPE_VALUE,
		// ALL_SCHOOL_TYPE_LABEL, 0));
		try {
			InputStreamReader isr = new InputStreamReader(Constant.class.getResourceAsStream("/school-types"));
			BufferedReader bufferedReader = new BufferedReader(isr);
			while ((line = bufferedReader.readLine()) != null) {
				int dashIndex = line.indexOf("-");
				int semicolonIndex = line.indexOf(";");
				String key = line.substring(0, dashIndex);
				String value = line.substring(dashIndex + 1, semicolonIndex);
				int classes;
				try {
					classes = Integer.parseInt(line.substring(semicolonIndex + 1));
				} catch (NumberFormatException e) {
					throw new WrongConfigFileFormatException("school-types");
				}
				schoolTypes.put(key, new SchoolType(key, value, classes));
			}
			bufferedReader.close();
		} catch (IOException e) {
			logger.warning(e.getMessage());
		}
		return schoolTypes;
	}

	private static Map<String, String> initSchoolTypeNamesMap() {
		Map<String, String> schoolTypeNames = new HashMap<>();
		if (schoolTypes == null) {
			logger.warning("No school types found!");
		}
		for (SchoolType schoolType : schoolTypes.values()) {
			schoolTypeNames.put(schoolType.typeKey, schoolType.typeName);
		}
		return schoolTypeNames;
	}

	private static Map<Integer, String> initSchoolClasses() {
		Map<Integer, String> schoolClasses = new LinkedHashMap<>();
		schoolClasses.put(ALL_SCHOOL_CLASS_VALUE, ALL_SCHOOL_CLASS_LABEL);
		for (int i = 1; i <= MAX_SCHOOL_CLASSES_NUMBER; i++) {
			schoolClasses.put(i, String.valueOf(i));
		}
		return schoolClasses;
	}

	private static Map<String, String> initBookStates() {
		String line;
		Map<String, String> bookStates = new LinkedHashMap<>();
		// bookStates.put(ALL_BOOK_STATE_VALUE, ALL_BOOK_STATE_LABEL);
		try {
			InputStreamReader isr = new InputStreamReader(Constant.class.getResourceAsStream("/book-states"));
			BufferedReader bufferedReader = new BufferedReader(isr);
			while ((line = bufferedReader.readLine()) != null) {
				int dashIndex = line.indexOf("-");
				String key = line.substring(0, dashIndex);
				String value = line.substring(dashIndex + 1);
				bookStates.put(key, value);
			}
			bufferedReader.close();
		} catch (IOException e) {
			logger.warning(e.getMessage());
		}
		return bookStates;
	}

	private static Map<Integer, String> initPriceRanges() {
		Map<Integer, String> priceRanges = new LinkedHashMap<>();
		int step = 20;
		int lastPriceBoundary = 70;
		int price = lastPriceBoundary;

		priceRanges.put(ALL_PRICE_VALUE, ALL_PRICE_LABEL);
		while (price >= 0) {
			priceRanges.put(price, "Do " + price + " z³");
			price -= step;
		}

		return priceRanges;
	}

	// helper class allowing to store not only schooltype options' value and label,
	// but also number of class in concrete schooltype
	private static class SchoolType {
		String typeKey;
		String typeName;
		int classes;

		public SchoolType(String typeKey, String typeName, int classes) {
			this.typeKey = typeKey;
			this.typeName = typeName;
			this.classes = classes;
		}
	}

}
