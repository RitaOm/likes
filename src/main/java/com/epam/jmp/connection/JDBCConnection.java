package com.epam.jmp.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

import com.epam.jmp.util.Properties;

public class JDBCConnection implements IconnectionDB {

	private String driver;
	private String url;
	private String user;
	private String password;

	private Driver dbDriver;
	
	private static final ReentrantLock LOCK = new ReentrantLock();
	private static JDBCConnection instance;
	
	public static IconnectionDB getInstance() {
		LOCK.lock();
		try {
			if (instance == null) {
				instance = new JDBCConnection();
			}
		} finally {
			LOCK.unlock();
		}
		return instance;
	}

	private JDBCConnection() {
		this.driver = Properties.getString("driver");
		this.url = Properties.getString("url");
		this.user = Properties.getString("user");
		this.password = Properties.getString("password");
		registerDriver();
	}

	private void registerDriver() {
		try {
			dbDriver = (Driver) Class.forName(driver).newInstance();
			DriverManager.registerDriver(dbDriver);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	@Override
	public void closeConnection(Connection connection)
			throws SQLException {
		if (connection != null){
			connection.close();
		}
	}

}
