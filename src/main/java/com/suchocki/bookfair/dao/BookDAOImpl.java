package com.suchocki.bookfair.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suchocki.bookfair.entity.Book;

@Repository
public class BookDAOImpl implements BookDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Book getBook(int id) {
		// TODO Auto-generated method stub
		System.out.println("getBook not implemented yet!");
		return null;
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		System.out.println("getAllBooks not implemented yet!");
		return null;
	}

	@Override
	public List<Book> getUserBooks(String username) {

		Session session = sessionFactory.getCurrentSession();
		Query<Book> query = session.createQuery("select b from Book b where b.owner.username=:username", Book.class);
		query.setParameter("username", username);
		
		return query.getResultList();
	}

	@Override
	public void deleteBook(int id) {
		System.out.println("deleteBook not implemented yet!");
	}

	@Override
	public void saveBook(Book book) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(book);
	}

}
