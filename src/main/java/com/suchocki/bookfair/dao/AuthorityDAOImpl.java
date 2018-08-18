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
	public Authority getAuthority(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Authority> getAllAuthorities() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Authority",Authority.class).getResultList();
	}

	@Override
	public void deleteAuthority(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveAuthority(Authority authority) {
		// TODO Auto-generated method stub

	}

}
