package com.spring.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.spring.web.dao.OffersDAO;
import com.spring.web.models.Offer;

@Service("offersService")
public class OffersService {

	private OffersDAO offersDAO;

	@Autowired
	public void setOffersDAO(OffersDAO offersDAO) {
		this.offersDAO = offersDAO;
	}

	public List<Offer> getAvailableOffers() {
		return offersDAO.getOffers();

	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public void createOffer(Offer offer) {
		offersDAO.saveOrUpdate(offer);
	}

	public boolean hasOffer(String name) {

		if (name == null)
			return false;

		List<Offer> offers = offersDAO.getOffers(name);

		if (offers.size() == 0)
			return false;

		return true;
	}

	public Offer getOffer(String username) {

		if (username == null) {
			return null;
		}

		List<Offer> offers = offersDAO.getOffers(username);

		if (offers.isEmpty())
			return null;

		return offers.get(0);
	}

	public void saveOrUpdate(Offer offer) {

		offersDAO.saveOrUpdate(offer);

	}

	public void delete(int id) {
		offersDAO.delete(id);
	}

}
