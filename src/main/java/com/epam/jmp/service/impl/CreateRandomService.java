package com.epam.jmp.service.impl;

import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import com.epam.jmp.util.Properties;
import com.epam.jmp.entity.*;
import com.epam.jmp.service.*;

public class CreateRandomService extends AbstractService implements
		ICreateService {

	private Random rand;

	public CreateRandomService() {
		super();
		rand = new Random();
	}

	@Override
	public void createUser(Context x) {
		User user = new User();
		String name = textGenerator.generateName();
		String surname = textGenerator.generateName();
		Date date = dateGenerator.generateBirthDate();
		user.setName(name);
		user.setSurname(surname);
		user.setBirthdate(date);
		try {
			userDAO.create(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createFriendship(Context x) {
		try {
			List<Integer> UserIdsList = userDAO.getIdsList(-1);
			if (UserIdsList.size() < 2) {
				return;
			}
			Friendship friendship = createUniqueFriendship(UserIdsList);
			while (true) {
				List<Integer> friendsOfUser1List = friendshipDAO
						.getIdsList(friendship.getUserId1());
				List<Integer> friendsOfUser2List = friendshipDAO
						.getIdsList(friendship.getUserId2());
				boolean isFriendship = false;
				for (Integer friendsOfUser1 : friendsOfUser1List) {
					if (friendship.getUserId2() == friendsOfUser1) {
						isFriendship = true;
					}
				}
				if (!isFriendship) {
					for (Integer friendsOfUser2 : friendsOfUser2List) {
						if (friendship.getUserId1() == friendsOfUser2) {
							isFriendship = true;
						}
					}
				}
				if (isFriendship) {
					friendship = createUniqueFriendship(UserIdsList);
				} else {
					break;
				}
			}
			Timestamp datetime = dateGenerator.generateDateTimeFromInitDate();
			friendship.setDateTime(datetime);
			friendshipDAO.create(friendship);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Friendship createUniqueFriendship(List<Integer> list) {
		int user1IdNumber = rand.nextInt(list.size());
		int user2IdNumber = rand.nextInt(list.size());
		while (user1IdNumber == user2IdNumber) {
			user2IdNumber = rand.nextInt(list.size());
		}
		Friendship friendship = new Friendship();
		friendship.setUserId1(list.get(user1IdNumber));
		friendship.setUserId2(list.get(user2IdNumber));		
		return friendship;
	}

	@Override
	public void createPost(Context x) {
		Post post = new Post();
		post.setText(textGenerator.generateTextMessage());
		post.setDateTime(dateGenerator.generateDateTimeFromInitDate());
		try {
			List<Integer> userIdsList = userDAO.getIdsList(-1);
			if (userIdsList.size() == 0) {
				return;
			}
			int userIdNumber = rand.nextInt(userIdsList.size());
			post.setUserId(userIdsList.get(userIdNumber));
			postDAO.create(post);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createLike(Context x) {
		try {
			List<Integer> postIdsList = postDAO.getIdsList(-1);
			if (postIdsList.size() == 0) {
				return;
			}
			int postIdNumber = rand.nextInt(postIdsList.size());
			Post post = postDAO.getById(postIdsList.get(postIdNumber));
			int userId = 0;
			while (true) {			
				List<Integer> friendsOfUserList = friendshipDAO
						.getIdsList(post.getUserId());
				if (friendsOfUserList.size() > 0){
					int userIdNumber = rand.nextInt(friendsOfUserList.size());
					userId = friendsOfUserList.get(userIdNumber);
					break;
				} else {
					postIdNumber = rand.nextInt(postIdsList.size());
					post = postDAO.getById(postIdNumber);					
				}				
			}
			Like like = new Like();
			like.setUserId(userId);
			like.setPostId(postIdsList.get(postIdNumber));
			Timestamp dateTime = dateGenerator.generateDateTimeFromInitDate(post.getDateTime().getTime());
			like.setDateTime(dateTime);
			likeDAO.create(like);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createAll(Context x) {
		int quantity = Properties.getNumber("usersQuantity");
		for (int i = 0; i < quantity; i++) {
			createUser(null);
		}
		quantity = Properties.getNumber("friendshipsQuantity");
		for (int i = 0; i < quantity; i++) {
			createFriendship(null);
		}
		quantity = Properties.getNumber("postsQuantity");
		for (int i = 0; i < quantity; i++) {
			createPost(null);
		}
		quantity = Properties.getNumber("likesQuantity");
		for (int i = 0; i < quantity; i++) {
			createLike(null);
		}
	}
}
