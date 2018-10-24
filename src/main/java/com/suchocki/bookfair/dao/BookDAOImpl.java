package com.suchocki.bookfair.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suchocki.bookfair.config.Constant;
import com.suchocki.bookfair.entity.Book;

@Repository
public class BookDAOImpl implements BookDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Book getBook(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Book.class, id);
	}

	@Override
	public List<Book> getAllBooks() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("select b from Book b", Book.class).getResultList();
	}

	@Override
	public List<Book> getUserBooks(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query<Book> query = session.createQuery("select b from Book b where b.owner.username=:username", Book.class);
		query.setParameter("username", username);

		return query.getResultList();
	}

	@Override
	public List<Book> getUserOrderedBooks(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query<Book> query = session.createQuery("select b from Book b where b.purchaser.username=:username",
				Book.class);
		query.setParameter("username", username);

		return query.getResultList();
	}

	@Override
	public void deleteBook(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Book b where b.id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public void deleteBook(Book book) {
		deleteBook(book.getId());
	}

	@Override
	public void saveBook(Book book) {

		if (book.getOwner() == null) {
			throw new BookWithoutOwnerSavingException();
		}

		Session session = sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(book);
		} catch (ConstraintViolationException e) {
			throw new BookWithoutOwnerSavingException();
		}
	}

	@Override
	public List<Book> getMatchingBooks(Book criteriaBook) {
		Session session = sessionFactory.getCurrentSession();

		final int nullReceptionSchoolId = -1;
		Query<Book> query = session
				.createQuery("select b from Book b where b.title like :title and b.price <= :price and (b.condition"
						+ " = :condition or :condition = :allCondition)"
						+ " and (b.schoolType = :schoolType or :schoolType=:allSchoolType) and (b.schoolClass = :schoolClass or :schoolClass = "
						+ Constant.ALL_SCHOOL_CLASS_VALUE
						+ ") and (b.topic=:topic or :topic = :allTopics) and (b.owner.school.id = :receptionSchoolId or :receptionSchoolId = "
						+ nullReceptionSchoolId + ")", Book.class);

		query.setParameter("title", (criteriaBook.getTitle() != null) ? "%" + criteriaBook.getTitle() + "%" : "%");
		query.setParameter("price", (criteriaBook.getPrice() != null) ? criteriaBook.getPrice() : Book.MAX_PRICE);

		query.setParameter("condition", criteriaBook.getCondition());
		query.setParameter("allCondition", Constant.ALL_BOOK_STATE_VALUE);

		query.setParameter("schoolType", criteriaBook.getSchoolType());
		query.setParameter("allSchoolType", Constant.ALL_SCHOOL_TYPE_VALUE);

		query.setParameter("schoolClass", criteriaBook.getSchoolClass());

		query.setParameter("topic", criteriaBook.getTopic());
		query.setParameter("allTopics", Constant.ALL_TOPIC_VALUE);

		query.setParameter("receptionSchoolId",
				(criteriaBook.getOwner().getSchool()) != null ? criteriaBook.getOwner().getSchool().getId()
						: nullReceptionSchoolId);

		System.out.println("BookDAOImpl: receptionSchool:" + criteriaBook.getOwner().getSchool());

		List<Book> resultList = query.getResultList();
		System.out.println("getMatchingBooks(): result list: " + resultList);
		return resultList;
	}

}
