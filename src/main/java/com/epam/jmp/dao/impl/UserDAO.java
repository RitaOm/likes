package com.epam.jmp.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.jmp.dao.AbstractDao;
import com.epam.jmp.entity.User;

public class UserDAO extends AbstractDao<User> {

	@Override
	public String getCreateQuery() {
		return "INSERT INTO users (id, name, surname, birthdate) VALUES (NULL, ?, ?, ?);";
	}

	@Override
	public String getQuantityQuery() {
		return "SELECT COUNT(*) AS count FROM users;";
	}

	@Override
	public String getIdsListQuery() {
		return "SELECT id FROM users;";
	}
	@Override
	public String getSelectQuery() {
		return "SELECT * FROM users";
	}

	@Override
	public String idColomnName() {
		return "id";
	}

	@Override
	public List<User> parseResultSet(ResultSet rs) throws SQLException {
		ArrayList<User> result = new ArrayList<User>();
		while (rs.next()) {
			User user = newUser(rs);
			result.add(user);
		}
		return result;
	}

	public User newUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setSurname(rs.getString("surname"));
		user.setBirthdate(rs.getDate("birthdate"));
		return user;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			User object) throws SQLException {
		statement.setString(1, object.getName());
		statement.setString(2, object.getSurname());
		statement.setDate(3, object.getBirthdate());
	}

}
