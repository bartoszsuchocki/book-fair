package com.suchocki.bookfair.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(book);
	}

	@Override
	public List<Book> getMatchingBooks(Book criteriaBook) {
		Session session = sessionFactory.getCurrentSession();

		Query<Book> query = session.createQuery("select b from Book b where b.title like :title and b.price <= :price"
				+ " and b.condition like :condition and b.schoolType like :schoolType and b.schoolClass = :schoolClass"
				+ " and b.topic like :topic", Book.class);

		query.setParameter("title", (criteriaBook.getTitle() != null) ? "%" + criteriaBook.getTitle() + "%" : "%");
		query.setParameter("price", (criteriaBook.getPrice() != null) ? criteriaBook.getPrice() : Book.MAX_PRICE);
		query.setParameter("condition",
				(criteriaBook.getCondition() != null) ? "%" + criteriaBook.getCondition() + "%" : "%");
		query.setParameter("schoolType",
				(criteriaBook.getSchoolType() != null) ? "%" + criteriaBook.getSchoolType() + "%" : "%");
		query.setParameter("schoolClass", (criteriaBook.getSchoolClass() != null) ? criteriaBook.getSchoolClass()
				: Constant.ALL_SCHOOL_CLASS_TYPE);
		query.setParameter("topic", (criteriaBook.getTopic() != null) ? "%" + criteriaBook.getTopic() + "%" : "%");

		System.out.println("BookDaoImpl.getMatchingBooks(): query: " + query.getQueryString());

//		// Tymczasowe rozwi¹zanie:
		query = session
				.createQuery("select b from Book b where b.title like :title and b.price <= :price and b.condition"
						+ " like :condition and b.schoolType like :schoolType and (b.schoolClass = :schoolClass or :schoolClass = "
						+ Constant.ALL_SCHOOL_CLASS_TYPE + ") and b.topic like :topic", Book.class); // ten 'or'
																										// odpowiada za
																										// wyœwitlenie
		// wszystkich klas w przypadku wartoœci
		// allschoolclasstype
		query.setParameter("title", (criteriaBook.getTitle() != null) ? "%" + criteriaBook.getTitle() + "%" : "%");
		query.setParameter("price", (criteriaBook.getPrice() != null) ? criteriaBook.getPrice() : Book.MAX_PRICE);
		query.setParameter("condition",
				(criteriaBook.getCondition() != null) ? "%" + criteriaBook.getCondition() + "%" : "%");
		query.setParameter("schoolType",
				(criteriaBook.getSchoolType() != null) ? "%" + criteriaBook.getSchoolType() + "%" : "%");
		query.setParameter("schoolClass", (criteriaBook.getSchoolClass() != null) ? criteriaBook.getSchoolClass()
				: Constant.ALL_SCHOOL_CLASS_TYPE);
		query.setParameter("topic", (criteriaBook.getTopic() != null) ? "%" + criteriaBook.getTopic() + "%" : "%");

		// query.setParameter("school", (criteriaBook.getOwner().getSchool()!= null) ?
		// criteriaBook.getOwner().getSchool()
		// : Constant.ALL_SCHOOL_CLASS_TYPE);

		List<Book> resultList = query.getResultList();
		System.out.println("getMatchingBooks(): result list: " + resultList);
		return resultList;
	}

}
