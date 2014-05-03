package com.spring.web.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.web.dao.FormValidationGroup;
import com.spring.web.models.Offer;
import com.spring.web.services.OffersService;

@Controller
public class OffersController {

	private OffersService offersService;

	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}

	@RequestMapping("/createoffer")
	public String createOffer(Model model, Principal principal) {

		Offer offer = null;

		// user can only have one offer 
		// check to see if user already has offer
		if (principal != null) {
			String username = principal.getName();
			offer = offersService.getOffer(username);
		}
		if (offer == null) {
			offer = new Offer();
		}

		model.addAttribute("offer", offer);

		return "createoffer";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(@Validated (FormValidationGroup.class) Offer offer, BindingResult results,
			Principal principal,
			@RequestParam(value = "delete", required = false) String delete) {

		// if errors reload same page
		if (results.hasErrors())
			return "createoffer";

		// create offer
		if (delete == null) {
			String username = principal.getName();
			offer.getUser().setUsername(username);
			offersService.saveOrUpdate(offer);

			return "offercreated";
		}
		// delete offer
		else {
			offersService.delete(offer.getId());
			return "offerdeleted";
		}

	}

}
