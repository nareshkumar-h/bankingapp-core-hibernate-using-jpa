package com.naresh.bankingapp.dao;

import java.util.List;

import com.naresh.bankingapp.exception.DBException;
import com.naresh.bankingapp.model.User;

public interface UserDAO {

	void insert(User user) throws DBException;
	User findOne(int userId) throws DBException;
	void update(User user) throws DBException;
	void delete(User user) throws DBException;	
	public List<User> list() throws DBException;

}