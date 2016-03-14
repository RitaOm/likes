package com.epam.jmp.service.impl;

import java.sql.Date;
import java.sql.SQLException;

import com.epam.jmp.util.Properties;

import com.epam.jmp.entity.User;
import com.epam.jmp.service.AbstractService;
import com.epam.jmp.service.Context;
import com.epam.jmp.service.ICreateService;

public class CreateRandomService extends AbstractService implements ICreateService{

	@Override
	public void createUser(Context x) {
		User user = new User();
		user.setName(textGenerator.generateName());
		user.setSurname(textGenerator.generateName());
		java.util.Date date = new java.util.Date();
		user.setBirthdate(new Date(date.getTime()));
		try {
			userDAO.create(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createFriendship(Context x) {
		
	}

	@Override
	public void createPost(Context x) {
		
	}

	@Override
	public void createLike(Context x) {
		
	}

	@Override
	public void createAll(Context x) {
		int usersQuantity = Properties.getNumber("usersQuantity");
		for (int i = 0; i<usersQuantity; i++){
			createUser(null);
		}
	}
}
