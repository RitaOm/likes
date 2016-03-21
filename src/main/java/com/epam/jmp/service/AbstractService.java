package com.epam.jmp.service;

import com.epam.jmp.util.DateGenerator;
import com.epam.jmp.util.TextGenerator;

import com.epam.jmp.dao.impl.*;

public class AbstractService {

	protected UserDAO userDAO;
	protected FriendshipDAO friendshipDAO;
	protected PostDAO postDAO;
	protected LikeDAO likeDAO;
	protected TextGenerator textGenerator;
	protected DateGenerator dateGenerator;

	
	public AbstractService(){
		userDAO = new UserDAO();
		friendshipDAO = new FriendshipDAO();
		postDAO = new PostDAO();
		likeDAO = new LikeDAO();
		textGenerator = new TextGenerator();
		dateGenerator = new DateGenerator();
	}
	
}
