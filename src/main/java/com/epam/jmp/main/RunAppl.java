package com.epam.jmp.main;

import com.epam.jmp.service.impl.*;

public class RunAppl {

	public static void main(String[] args) throws Exception{
		CreateRandomService createService = new CreateRandomService();
		createService.createAll(null);
		GetCountService getCountService = new GetCountService();
		System.out.println("Users: "+getCountService.getUsersCount());
		System.out.println("Friendships: "+getCountService.getFriendshipsCount());
		System.out.println("Posts: "+getCountService.getPostsCount());
        System.out.println("Likes: "+getCountService.getLikesCount());
	}

}
