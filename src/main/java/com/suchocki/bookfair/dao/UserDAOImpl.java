package com.suchocki.bookfair.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suchocki.bookfair.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
