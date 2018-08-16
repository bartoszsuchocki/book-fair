package com.suchocki.bookfair.service;

import java.util.List;

import com.suchocki.bookfair.entity.User;

public interface UserService {
	public User getUser(int id);
	public List<User> getUsers();
	public void deleteUser(int id);
	public void saveUser(User user);
}
