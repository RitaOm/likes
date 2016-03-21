package com.epam.jmp.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.jmp.dao.AbstractDao;
import com.epam.jmp.entity.Like;

public class LikeDAO extends AbstractDao<Like> {

	@Override
	public String getCreateQuery() {
		return "INSERT INTO likes (id_post, id_user, date_time) VALUES (?, ?, ?);";
	}

	@Override
	public String getQuantityQuery() {
		return "SELECT COUNT(*) AS count FROM likes;";
	}

	@Override
	public String getIdsListQuery() {
		return "SELECT id_post FROM likes WHERE id_user = ?;";
	}
	@Override
	public String getSelectQuery() {
		return "SELECT * FROM likes";
	}

	@Override
	public String idColomnName() {
		return "id_post";
	}

	@Override
	public List<Like> parseResultSet(ResultSet rs) throws SQLException {
		ArrayList<Like> result = new ArrayList<Like>();
		while (rs.next()) {
			Like like = newLike(rs);
			result.add(like);
		}
		return result;
	}

	public Like newLike(ResultSet rs) throws SQLException {
		Like like = new Like();
		like.setPostId(rs.getInt("id_post"));
		like.setUserId(rs.getInt("id_user"));
		like.setDateTime(rs.getTimestamp("date_time"));
		return like;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Like object) throws SQLException {
		statement.setInt(1, object.getPostId());
		statement.setInt(2, object.getUserId());
		statement.setTimestamp(3, object.getDateTime());
	}
	
	@Override
	public Like getById(int id) throws SQLException {
		return null;
	}


}
