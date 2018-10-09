package com.suchocki.bookfair.test.daotest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.suchocki.bookfair.dao.UserDAO;
import com.suchocki.bookfair.entity.User;
import com.suchocki.bookfair.test.config.TestConfig;

@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDAOTest {
	
	@Autowired
	private UserDAO userDAO;
	
	@Test
	@Transactional
	@Rollback
	public void saveUserTest() {
		User user = new User("adamk", "password", "Adam", "Kowalski", "adam@gmail.com", "Kochanowski");
		
		userDAO.saveUser(user);
		
		//userDAO.getUsers()
		
		
	}
	
}
