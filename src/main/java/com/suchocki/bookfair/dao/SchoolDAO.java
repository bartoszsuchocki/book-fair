package com.suchocki.bookfair.dao;

import java.util.List;

import com.suchocki.bookfair.entity.School;

public interface SchoolDAO {
	public School getSchool(int id);

	public List<School> getAllSchools();

	public void deleteSchool(int id);

	public void saveSchool(School school);
}
