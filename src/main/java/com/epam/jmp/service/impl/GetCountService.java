package com.epam.jmp.service.impl;

import java.sql.SQLException;
import com.epam.jmp.service.*;

public class GetCountService extends AbstractService {

	public long getUsersCount(){
		try {
			return userDAO.getQuantity();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public long getFriendshipsCount(){
		try {
			return friendshipDAO.getQuantity();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public long getPostsCount(){
		try {
			return postDAO.getQuantity();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public long getLikesCount(){
		try {
			return likeDAO.getQuantity();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}		
	}
	
}
