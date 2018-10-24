package com.suchocki.bookfair.config.exception;

public class WrongConfigFileFormatException extends RuntimeException {
	private final static String MSG = "Incorrect config file! ";

	public WrongConfigFileFormatException() {
		super(MSG);
	}

	public WrongConfigFileFormatException(String fileName) {
		super(MSG + fileName);
	}
}
