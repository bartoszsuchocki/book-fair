package com.suchocki.bookfair.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchocki.bookfair.dao.SchoolDAO;
import com.suchocki.bookfair.entity.School;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolDAO schoolDAO;

	@Transactional
	@Override
	public School getSchool(int id) {
		return schoolDAO.getSchool(id);
	}

	@Transactional
	@Override
	public List<School> getAllSchools() {
		return schoolDAO.getAllSchools();
	}

	@Transactional
	@Override
	public void deleteSchool(int id) {
		schoolDAO.deleteSchool(id);
	}

	@Transactional
	@Override
	public void saveSchool(School school) {
		schoolDAO.saveSchool(school);
	}

}
