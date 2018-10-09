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
	public void deleteUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		User user = getUser(username);
		if(user == null) {
			return;
		}
		session.delete(user);
	}

	@Override
	public void saveUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("Saving user. User's authorities: " + user.getAuthorities());
		session.saveOrUpdate(user);
	}

	@Override
	public User getUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(User.class, username);
	}

	@Override
	public List<User> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("select u from User u", User.class).getResultList();
	}

}
