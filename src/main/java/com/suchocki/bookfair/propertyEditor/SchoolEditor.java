package com.suchocki.bookfair.propertyEditor;

import java.beans.PropertyEditorSupport;
import java.util.logging.Logger;

import com.suchocki.bookfair.entity.School;

public class SchoolEditor extends PropertyEditorSupport {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		System.out.println("SchoolEditor: textToParse: " + text);
		School school = new School();

		int semiColonPosition = text.indexOf(";");
		int linePosition = text.indexOf("|");

		if (semiColonPosition == -1 || linePosition == -1) {
			this.setValue(null);
			logger.warning("School object not parsed properly!");
			return;
		}

		try {
			int id = Integer.parseInt(text.substring(0, semiColonPosition));
			String name = text.substring(text.indexOf(";") + 1, text.indexOf("|"));
			String address = text.substring(linePosition + 1);
			school.setId(id);
			school.setName(name);
			school.setAddress(address);
		} catch (NumberFormatException e) {
			school = null;
			logger.warning("School object not parsed properly!");
		}

		this.setValue(school);
	}

	@Override
	public String getAsText() {
		System.out.println("SchoolEditor.getAsText(): getValue(): " + getValue());
		School school = (School) getValue();
		if (school == null) {
			return "null";
		}
		return school.convertableForm();
	}
}
