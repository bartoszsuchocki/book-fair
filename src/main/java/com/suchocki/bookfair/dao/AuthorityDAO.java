package com.suchocki.bookfair.dao;

import java.util.List;

import com.suchocki.bookfair.entity.Authority;

public interface AuthorityDAO {
	public Authority getAuthority(String name);

	public List<Authority> getAllAuthorities();

	public void deleteAuthority(String name);

	public void saveAuthority(Authority authority);

}
