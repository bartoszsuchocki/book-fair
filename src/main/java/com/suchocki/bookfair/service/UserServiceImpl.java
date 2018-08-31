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
	private UserDAO userDao;

	@Transactional
	@Override
	public User getUser(String username) {
		return userDao.getUser(username);
	}

	@Transactional
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub

	}

	@Transactional
	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

}
