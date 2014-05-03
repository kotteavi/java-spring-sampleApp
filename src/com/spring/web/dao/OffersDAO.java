 package com.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.MysqlParameterMetadata;
import com.spring.web.models.Offer;

@Repository
@Transactional
@Component
public class OffersDAO {
//	private NamedParameterJdbcTemplate jdbc;
//
//	@Autowired
//	public void setDataSource(DataSource jdbc) {
//
//		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
//	}

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Offer> getOffers(String username) {

		Criteria crit = session().createCriteria(Offer.class);
		// "user" is form the offer class
		crit.createAlias("user", "u");
		crit.add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.eq("u.username", username));

		return crit.list();

		// return jdbc
		// .query("select * from offers, users where offers.username =users.username and users.enabled = true and  offers.username = :username",
		// new MapSqlParameterSource("username", username),
		// new OfferRowMapper());
	}

	@SuppressWarnings("unchecked")
	public List<Offer> getOffers() {
		Criteria crit = session().createCriteria(Offer.class);
		// "user" is form the offer class
		crit.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));

		return crit.list();
		// return jdbc
		// .query("select * from offers, users where offers.username =users.username and users.enabled = true",
		// new OfferRowMapper());
	}

	public Offer getOffer(int id) {
		Criteria crit = session().createCriteria(Offer.class);
		// "user" is from  the offer class
		crit.createAlias("user", "u");
		crit.add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.idEq(id));
		
		return (Offer) crit.uniqueResult();
		
//		MapSqlParameterSource params = new MapSqlParameterSource();
//		params.addValue("id", id);
//
//		// select * from offers where id = :id
//		return jdbc
//				.queryForObject(
//						" select * from offers, users where offers.username=users.username and users.enabled=true",
//						params, new OfferRowMapper());
	}

	//
	// CRUD methods
	//

	public void saveOrUpdate(Offer offer) {
		// BeanPropertySqlParameterSource paramMap = new
		// BeanPropertySqlParameterSource(
		// offer);
		//
		// return jdbc
		// .update("insert into offers (username, text) values (:username, :text);",
		// paramMap) == 1;

//		session().save(offer);
		session().saveOrUpdate(offer);

	}

	// @Transactional
	// public int[] create(List<Offer> offers) {
	// SqlParameterSource[] batchArgs = SqlParameterSourceUtils
	// .createBatch(offers.toArray());
	//
	// return jdbc
	// .batchUpdate(
	// "insert into offers (username, text) values (:username, :text) ;",
	// batchArgs);
	// }
//
//	public void update(Offer offer) {
//		session().update(offer);
//		
////		BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(
////				offer);
////
////		return jdbc.update("update offers set text = :text where id = :id",
////				paramMap) == 1;
//	}

	public boolean delete(int id) {
		
		// this has hql language, hibernate query language. 
		Query query = session().createQuery("delete from Offer where id=:id");
		query.setLong("id", id);
		return query.executeUpdate()==1;
		
//		MapSqlParameterSource paramMap = new MapSqlParameterSource("id", id);
//
//		return jdbc.update("delete from offers where id = :id", paramMap) == 1;

	}
}
