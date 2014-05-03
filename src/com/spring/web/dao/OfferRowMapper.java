package com.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.web.models.Offer;
import com.spring.web.models.User;

public class OfferRowMapper implements RowMapper<Offer> {

	@Override
	public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setAuthority(rs.getString("authority"));
		user.setEmail(rs.getString("email"));
		user.setEnabled(true);
		user.setName(rs.getString("name"));
		user.setUsername(rs.getString("username"));

		Offer offer = new Offer();
		offer.setId(rs.getInt("id"));
		offer.setText(rs.getString("text"));
		offer.setUser(user);

		return offer;
	}

}
