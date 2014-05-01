package com.spring.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component
public class UserDAO {
//	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}

//	@Autowired
//	public void setDataSource(DataSource jdbc) {
//
//		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
//	}

//	@Transactional
//	public boolean create(User user) {
//		// BeanPropertySqlParameterSource paramMap = new
//		// BeanPropertySqlParameterSource(
//		// user);
//
//		MapSqlParameterSource paramMap = new MapSqlParameterSource();
//		paramMap.addValue("username", user.getUsername());
//		paramMap.addValue("password",
//				passwordEncoder.encode(user.getPassword()));
//		paramMap.addValue("email", user.getEmail());
//		paramMap.addValue("name", user.getName());
//		paramMap.addValue("enabled", user.isEnabled());
//		paramMap.addValue("authority", user.getAuthority());
//
//		return jdbc
//				.update("insert into users (username, password, email, name, enabled, authority) values (:username, :password, :email, :name, :enabled, :authority);",
//						paramMap) == 1;
//	}
	
	@Transactional
	public void create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user); 
	}

	public boolean readUsername(String username) {

		return getUser(username)!=null;
		
//		return jdbc.queryForObject(
//				"select count(*) from users where username=:username",
//				new MapSqlParameterSource("username", username), Integer.class) > 0;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return session().createQuery("from User").list();
		
//		return jdbc.query("select * from users ",
//				BeanPropertyRowMapper.newInstance(User.class));
	}

	public User getUser(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("username", username));
		User user = (User) crit.uniqueResult();
		return user;
		
	}

}
