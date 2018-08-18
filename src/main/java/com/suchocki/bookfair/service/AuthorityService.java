package com.suchocki.bookfair.service;

import java.util.List;
import java.util.Map;

import com.suchocki.bookfair.entity.Authority;

public interface AuthorityService {
	public Authority getAuthority(int id);

	public List<Authority> getAllAuthoritiesList();

	Map<String, Authority> getAllAuthoritiesMap();

	public void deleteAuthority(int id);

	public void saveAuthority(Authority authority);
}
