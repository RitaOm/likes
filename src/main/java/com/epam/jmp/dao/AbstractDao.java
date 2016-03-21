package com.epam.jmp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.jmp.connection.IconnectionDB;
import com.epam.jmp.connection.JDBCConnection;

public abstract class AbstractDao<T> implements Dao<T> {

	protected IconnectionDB connectionDB;
		
	public AbstractDao() {
		connectionDB = JDBCConnection.getInstance();
	}

	public abstract String getCreateQuery();
	
	public abstract String getQuantityQuery();
	
	public abstract String getIdsListQuery();
	
	public abstract String getSelectQuery();

	public abstract String idColomnName();

	protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

	protected abstract void prepareStatementForInsert(
			PreparedStatement statement, T object) throws SQLException;

	@Override
	public void create(T object) throws SQLException {
		Connection connection = connectionDB.getConnection();
		try (PreparedStatement statement = connection
				.prepareStatement(getCreateQuery())) {
			prepareStatementForInsert(statement, object);
			statement.executeUpdate();
		} finally {
			connectionDB.closeConnection(connection);
		}
	}
	
	@Override
	public int getQuantity() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Integer> getIdsList(int id) throws SQLException {
		Connection connection = connectionDB.getConnection();
		List<Integer> list = new ArrayList<Integer>();
		String sql = getIdsListQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			if(id>0){
				statement.setInt(1, id);
			}
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Integer idNumber = rs.getInt(idColomnName());
				list.add(idNumber);
			}
		} finally {
			connectionDB.closeConnection(connection);
		}
		return list;
	}


	@Override
	public T getById(int id) throws SQLException {
		Connection connection = connectionDB.getConnection();
		List<T> list;
		String sql = getSelectQuery() + " WHERE " + idColomnName() + " = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
		} finally {
			connectionDB.closeConnection(connection);
		}
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.iterator().next();
		}
	}

	@Override
	public List<T> getAll() throws SQLException {
		Connection connection = connectionDB.getConnection();
		List<T> list;
		String sql = getSelectQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
		} finally {
			connectionDB.closeConnection(connection);
		}
		return list;
	}

}
