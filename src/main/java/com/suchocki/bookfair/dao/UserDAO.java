package com.suchocki.bookfair.dao;

import java.util.List;

import com.suchocki.bookfair.entity.User;

public interface UserDAO {
	public User getUser(String username);
	public List<User> getUsers();
	public void deleteUser(String username);
	public void saveUser(User user);
}
