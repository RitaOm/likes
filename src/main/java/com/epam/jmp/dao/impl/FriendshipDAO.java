package com.epam.jmp.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.jmp.dao.AbstractDao;
import com.epam.jmp.entity.Friendship;

public class FriendshipDAO extends AbstractDao<Friendship> {

	@Override
	public String getCreateQuery() {
		return "INSERT INTO friendships (id_user1, id_user2, date_time) VALUES (?, ?, ?);";
	}

	@Override
	public String getQuantityQuery() {
		return "SELECT COUNT(*) AS count FROM friendships;";
	}

	@Override
	public String getIdsListQuery() {
		return "SELECT id_user1 FROM friendships WHERE id_user2 = ?;";
	}
	@Override
	public String getSelectQuery() {
		return "SELECT * FROM friendships";
	}

	@Override
	public String idColomnName() {
		return "id_user1";
	}

	@Override
	public List<Friendship> parseResultSet(ResultSet rs) throws SQLException {
		ArrayList<Friendship> result = new ArrayList<Friendship>();
		while (rs.next()) {
			Friendship friendship = newFriendship(rs);
			result.add(friendship);
		}
		return result;
	}

	public Friendship newFriendship(ResultSet rs) throws SQLException {
		Friendship friendship = new Friendship();
		friendship.setUserId1(rs.getInt("id_user1"));
		friendship.setUserId2(rs.getInt("id_user2"));
		friendship.setDateTime(rs.getTimestamp("date_time"));
		return friendship;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Friendship object) throws SQLException {
		statement.setInt(1, object.getUserId1());
		statement.setInt(2, object.getUserId2());
		statement.setTimestamp(3, object.getDateTime());
	}
	
	@Override
	public Friendship getById(int id) throws SQLException {
		return null;
	}

}
