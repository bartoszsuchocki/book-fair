package com.suchocki.bookfair.dao;

public class BookWithoutOwnerSavingException extends Exception {
	public BookWithoutOwnerSavingException() {
		super("You are probably trying to save book which either does not have an owner or its owner is not stored in database!");
	}
}
