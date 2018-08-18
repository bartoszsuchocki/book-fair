package com.suchocki.bookfair.dao;

import java.util.List;

import com.suchocki.bookfair.entity.Authority;

public interface AuthorityDAO {
	public Authority getAuthority(int id);

	public List<Authority> getAllAuthorities();

	public void deleteAuthority(int id);

	public void saveAuthority(Authority authority);

}
