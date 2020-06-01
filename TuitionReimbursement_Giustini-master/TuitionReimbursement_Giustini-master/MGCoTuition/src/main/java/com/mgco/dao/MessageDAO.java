package com.mgco.dao;

import java.sql.SQLException;
import java.util.List;

import com.mgco.beans.Message;

public interface MessageDAO {
	public void insertMessage(Message m) throws SQLException;

	public List<Message> getMessageList() throws SQLException;
	
	public List<Message> getMessagesById(int id) throws SQLException;
}
