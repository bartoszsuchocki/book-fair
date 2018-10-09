package com.suchocki.bookfair.test.daotest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
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

	private static List<User> sampleUsers;

	@BeforeClass
	public static void initializeSampleUsers() {
		sampleUsers = new ArrayList<>();
		sampleUsers.add(new User("user1", "user1pswd", "Adam", "Kowal", "user1@gmail.com", "Kochanowski"));
		sampleUsers.add(new User("user2", "user2pswd", "Damian", "Belka", "user2@gmail.com", "Reytan"));
		sampleUsers.add(new User("user3", "user3pswd", "Szymon", "Polak", "user3@gmail.com", "Kochanowski"));
	}

	@Test
	@Transactional
	@Rollback
	public void saveUserTest() {
		User user = sampleUsers.get(0);
		userDAO.saveUser(user);

		List<User> storedUsers = userDAO.getUsers();

		assertNotNull(storedUsers);
		assertTrue(storedUsers.size() == 1);
		assertTrue(storedUsers.get(0).equals(user));
	}

	@Test
	@Transactional
	@Rollback
	public void deleteUserTest() {
		for (User u : sampleUsers) {
			userDAO.saveUser(u);
		}
		User userToDelete = sampleUsers.get(0);
		userDAO.deleteUser(userToDelete.getUsername());

		List<User> storedUsers = userDAO.getUsers();
		User shouldBeNullUser = userDAO.getUser(sampleUsers.get(0).getUsername());

		assertTrue(storedUsers.size() == (sampleUsers.size() - 1));
		assertNull(shouldBeNullUser);
	}

	@Test
	@Transactional
	@Rollback
	public void getUserTest() {
		User userToSave = sampleUsers.get(1);
		
		userDAO.saveUser(userToSave);
		User storedUser = userDAO.getUser(userToSave.getUsername());

		assertNotNull(storedUser);
		assertTrue(userToSave.equals(storedUser));
	}

}
