package com.suchocki.bookfair.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.CharacterEncodingFilter;

@Component
public class EncodingFilter extends CharacterEncodingFilter {

	public EncodingFilter() {
		setEncoding("UTF-8");
		setForceEncoding(true);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("FILTRUJÊ!!!");
		super.doFilterInternal(request, response, filterChain);
	}
}
