package com.spring.web.test.tests;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.web.dao.MessagesDAO;
import com.spring.web.dao.UserDAO;
import com.spring.web.models.Message;
import com.spring.web.models.User;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/spring/web/config/dao-context.xml",
		"classpath:com/spring/web/config/security-context.xml",
		"classpath:com/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoTests {

	@Autowired
	private MessagesDAO messagesDAO;
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("Avinash Kotte", "kotteavin", "911Change",
			"kotteavin@gmail.com", true, "ROLE_ADMIN");
	private User user2 = new User("Abhilash Kotte", "kotteabh", "911Change",
			"kotteabh@gmail.com", true, "ROLE_ADMIN");
	private User user3 = new User("Sridevi Kotte", "kottesri", "911Change",
			"kottesri@gmail.com", true, "ROLE_ADMIN");
	private User user4 = new User("Bhujanga Rao Kotte", "kotterao",
			"911Change", "kotterao@gmail.com", true, "ROLE_ADMIN");

	private Message message1 = new Message("Hello", "I want you services",
			user1.getName(), user1.getEmail(), user1.getUsername());
	private Message message2 = new Message("Hello", "I want you services",
			user2.getName(), user2.getEmail(), user2.getUsername());

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");

	}

	@Test
	public void testSave() {
		userDAO.create(user1);
		userDAO.create(user2);
		
		messagesDAO.saveOrUpdate(message1);
		
	}

}
