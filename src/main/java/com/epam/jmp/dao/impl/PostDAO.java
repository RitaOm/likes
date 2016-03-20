package com.epam.jmp.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.jmp.dao.AbstractDao;
import com.epam.jmp.entity.Post;

public class PostDAO extends AbstractDao<Post> {

	@Override
	public String getCreateQuery() {
		return "INSERT INTO posts (id, id_user, text, date_time) VALUES (NULL, ?, ?, ?);";
	}

	@Override
	public String getQuantityQuery() {
		return "SELECT COUNT(*) FROM posts";
	}

	@Override
	public String getIdsListQuery() {
		return "SELECT id FROM posts";
	}
	@Override
	public String getSelectQuery() {
		return "SELECT * FROM posts";
	}

	@Override
	public String idColomnName() {
		return "id";
	}

	@Override
	public List<Post> parseResultSet(ResultSet rs) throws SQLException {
		ArrayList<Post> result = new ArrayList<Post>();
		while (rs.next()) {
			Post post = newPost(rs);
			result.add(post);
		}
		return result;
	}

	public Post newPost(ResultSet rs) throws SQLException {
		Post post = new Post();
		post.setId(rs.getInt("id"));
		post.setUserId(rs.getInt("id_user"));
		post.setText(rs.getString("text"));
		post.setDateTime(rs.getTimestamp("date_time"));
		return post;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Post object) throws SQLException {
		statement.setInt(1, object.getUserId());
		statement.setString(2, object.getText());
		statement.setTimestamp(3, object.getDateTime());
	}

}
