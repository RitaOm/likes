package com.epam.jmp.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

	public void create(T object)  throws SQLException;
	
	public int getQuantity()  throws SQLException;
	
	public List<Integer> getIdsList()  throws SQLException;

    public T getById(int id) throws SQLException;

    public List<T> getAll() throws SQLException;
}
