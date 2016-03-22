package com.epam.jmp.main;

import com.epam.jmp.service.impl.*;

public class RunAppl {

	public static void main(String[] args) throws Exception{
		CreateRandomService createService = new CreateRandomService();
		createService.createAll(null);
		System.out.println("Users: "+createService.getUsersCount());
		System.out.println("Friendships: "+createService.getFriendshipsCount());
		System.out.println("Posts: "+createService.getPostsCount());
        System.out.println("Likes: "+createService.getLikesCount());
	}

}
