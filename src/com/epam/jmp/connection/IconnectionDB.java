package com.epam.jmp.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface IconnectionDB {

	Connection getConnection() throws SQLException;
	
	void closeConnection(Connection connection) throws SQLException;

}
