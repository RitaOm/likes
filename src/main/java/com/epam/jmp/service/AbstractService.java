package com.epam.jmp.service;

import com.epam.jmp.util.DateGenerator;
import com.epam.jmp.util.TextGenerator;

import com.epam.jmp.dao.impl.UserDAO;

public class AbstractService {

	protected UserDAO userDAO;
	protected TextGenerator textGenerator;
	protected DateGenerator dateGenerator;
	
	
	public AbstractService(){
		userDAO = new UserDAO();
		textGenerator = new TextGenerator();
		dateGenerator = new DateGenerator();
	}
	
}
