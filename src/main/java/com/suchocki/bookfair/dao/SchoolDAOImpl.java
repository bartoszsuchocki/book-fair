package com.suchocki.bookfair.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suchocki.bookfair.entity.School;

@Repository
public class SchoolDAOImpl implements SchoolDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public School getSchool(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(School.class, id);
	}

	@Override
	public List<School> getAllSchools() {
		Session session = sessionFactory.getCurrentSession();
		Query<School> query = session.createQuery("select s from School s", School.class);
		return query.getResultList();
	}

	@Override
	public void deleteSchool(int id) {
		Session session = sessionFactory.getCurrentSession();
		School book = session.get(School.class, id);
		session.delete(book);
	}

	@Override
	public void saveSchool(School school) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(school);
	}

}
