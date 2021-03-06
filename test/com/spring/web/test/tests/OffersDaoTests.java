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

import com.spring.web.dao.OffersDAO;
import com.spring.web.dao.UserDAO;
import com.spring.web.models.Offer;
import com.spring.web.models.User;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/spring/web/config/dao-context.xml",
		"classpath:com/spring/web/config/security-context.xml",
		"classpath:com/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OffersDaoTests {

	@Autowired
	private OffersDAO offersDao;
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("Avinash Kotte", "kotteavin", "somePass",
			"kotteavin@gmail.com", true, "ROLE_ADMIN");
	private User user2 = new User("Abhilash Kotte", "kotteabh", "somePass",
			"kotteabh@gmail.com", true, "ROLE_ADMIN");
	private User user3 = new User("Sridevi Kotte", "kottesri", "somePass",
			"kottesri@gmail.com", true, "ROLE_ADMIN");
	private User user4 = new User("Bhujanga Rao Kotte", "kotterao",
			"somePass", "kotterao@gmail.com", true, "ROLE_ADMIN");
	
	private Offer offer1 = new Offer(user1,
			"Avinash Kotte's offer");
	private Offer offer2 = new Offer(user2,
			"Abhilash kotte's offer");
	private Offer offer3 = new Offer(user3,
			"Sridevi Kotte's offer");
	private Offer offer4 = new Offer(user4,
			"Bhujanga rao Kotte's offer");
	private Offer offer5 = new Offer(user4,
			"Bhujanga rao Kotte's 2nd offer");
	private Offer offer6 = new Offer(user4,
			"Bhujanga rao Kotte's  3rd offer");


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
		offersDao.saveOrUpdate(offer1);
		
		List<Offer> offers1 = offersDao.getOffers(user1.getUsername());
		assertEquals("Offers hould be equal", offer1, offers1.get(0));

		userDAO.create(user2);
		offersDao.saveOrUpdate(offer2);
		
		List<Offer> offers2 = offersDao.getOffers();
		assertEquals(2, offers2.size());

	}
	
	@Test 
	public void testUpdates(){
		userDAO.create(user1);
		offersDao.saveOrUpdate(offer1);
		
		List<Offer> offers1 = offersDao.getOffers(user1.getUsername());
		assertEquals("Offers hould be equal", offer1.getText(), offers1.get(0).getText());
		
		offer1.setText("This some updated text");
		offersDao.saveOrUpdate(offer1);
		List<Offer> offersUpdate = offersDao.getOffers(user1.getUsername());
		assertNotEquals("Offers hould be not be equal", offer2.getText(), offers1.get(0).getText());
	}
	
	@Test
	public void testDelete(){
		userDAO.create(user4);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		
		List<Offer> offers1 = offersDao.getOffers(user4.getUsername());
		assertEquals("Offers hould be equal", 3, offers1.size());
		
		
		offersDao.delete(offers1.get(offers1.size()-1).getId());
		List<Offer> offers2 = offersDao.getOffers(user4.getUsername());
		assertEquals("Offers hould be equal", 2, offers2.size());
		
		
	}
	
	
	@Test
	public void testGetOffersUsername(){
		
		userDAO.create(user4);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		
		List<Offer> offers1 = offersDao.getOffers();
		assertEquals(3, offers1.size());

		
		List<Offer> user4Offers = offersDao.getOffers(user4.getUsername());
		assertEquals(3, user4Offers.size());
		
		assertEquals(offer4, user4Offers.get(0));
		
		
	}
}
