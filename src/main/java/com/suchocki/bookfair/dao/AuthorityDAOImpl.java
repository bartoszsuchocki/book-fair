package com.suchocki.bookfair.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suchocki.bookfair.entity.Authority;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Authority getAuthority(String name) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Authority.class, name);
	}

	@Override
	public List<Authority> getAllAuthorities() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Authority", Authority.class).getResultList();
	}

	@Override
	public void deleteAuthority(String name) {
		Session session = sessionFactory.getCurrentSession();
		Authority authority = session.get(Authority.class, name);
		session.delete(authority);
	}

	@Override
	public void saveAuthority(Authority authority) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(authority);
	}

}
