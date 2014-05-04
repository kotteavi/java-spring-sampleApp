package com.spring.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.spring.web.dao.MessagesDAO;
import com.spring.web.dao.OffersDAO;
import com.spring.web.dao.UserDAO;
import com.spring.web.models.Message;
import com.spring.web.models.Offer;
import com.spring.web.models.User;

@Service("usersService")
public class UsersService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private MessagesDAO msgDAO;

	public void createUser(User user) {
		userDAO.create(user);
	}

	public boolean isUsernameAvailable(String username) {
		return userDAO.readUsername(username);
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {

		return userDAO.getAllUsers();
	}

	public void sendMessage(Message msg) {
		msgDAO.saveOrUpdate(msg);
	}

	public User getUser(String username) {
		return userDAO.getUser(username);
	}

	public List<Message> getMessages(String username) {
		return msgDAO.getMessages();
	}

}
