package com.suchocki.bookfair.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchocki.bookfair.dao.AuthorityDAO;
import com.suchocki.bookfair.entity.Authority;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityDAO authorityDAO;

	@Transactional
	@Override
	public Authority getAuthority(String name) {
		return authorityDAO.getAuthority(name);
	}

	@Transactional
	@Override
	public List<Authority> getAllAuthoritiesList() {
		return authorityDAO.getAllAuthorities();
	}

	@Transactional
	@Override
	public Map<String, Authority> getAllAuthoritiesMap() {
		return listToMap(authorityDAO.getAllAuthorities());
	}

	@Transactional
	@Override
	public void deleteAuthority(String name) {
		authorityDAO.deleteAuthority(name);
	}

	@Transactional
	@Override
	public void saveAuthority(Authority authority) {
		authorityDAO.saveAuthority(authority);
	}

	private Map<String, Authority> listToMap(List<Authority> authorityList) {
		Map<String, Authority> authorityMap = new HashMap<>();
		for (Authority a : authorityList) {
			authorityMap.put(a.getName(), a);
		}
		return authorityMap;
	}

}
