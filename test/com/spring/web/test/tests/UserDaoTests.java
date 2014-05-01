package com.spring.web.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.web.dao.User;
import com.spring.web.dao.UserDAO;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/spring/web/config/dao-context.xml",
		"classpath:com/spring/web/config/security-context.xml",
		"classpath:com/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

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

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}

	@Test
	public void testCreateRetrieve() {
		userDAO.create(user1);
		List<User> users1 = userDAO.getAllUsers();

		assertEquals("One user should have been created and retrieved ", 1,
				users1.size());
		assertEquals("Created users should be identical to retrieved", user1,
				users1.get(0));

		userDAO.create(user2);
		userDAO.create(user3);
		userDAO.create(user4);

		List<User> users2 = userDAO.getAllUsers();
		assertEquals("One user should have been created and retrieved ", 4,
				users2.size());
	}

	@Test
	public void testReadUsername() {
		userDAO.create(user1);
		boolean expected = userDAO.readUsername(user1.getUsername());
		assertEquals("User name: " + user1.getUsername() + " should exist",
				expected, true);

		boolean expected2 = userDAO.readUsername("hello");
		assertEquals("Username 'hello' should not exist", expected2, false);

	}

}
