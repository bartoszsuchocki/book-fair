package com.suchocki.bookfair.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suchocki.bookfair.dao.UserDAO;
import com.suchocki.bookfair.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDao;
	
	@Transactional
	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub

	}

	@Transactional
	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

}
