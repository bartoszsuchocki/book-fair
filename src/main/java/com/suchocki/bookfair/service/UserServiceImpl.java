package com.suchocki.bookfair.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchocki.bookfair.dao.UserDAO;
import com.suchocki.bookfair.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Transactional
	@Override
	public User getUser(String username) {
		return userDAO.getUser(username);
	}

	@Transactional
	@Override
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Transactional
	@Override
	public void deleteUser(String username) {
		userDAO.deleteUser(username);
	}

	@Transactional
	@Override
	public void saveUser(User user) {
		userDAO.saveUser(user);
	}

}
