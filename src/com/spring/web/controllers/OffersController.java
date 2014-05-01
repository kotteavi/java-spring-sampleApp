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
import com.spring.web.dao.Offer;
import com.spring.web.services.OffersService;

@Controller
public class OffersController {

	// @RequestMapping("/")
	// public String showHome(HttpSession session) {
	// session.setAttribute("test",
	// "This is a test of seeion being passed form our Spring controller");
	//
	// return "home";
	// }

	// @RequestMapping("/")
	// public ModelAndView showHome() {
	// ModelAndView mv = new ModelAndView("home");
	//
	// // stays in request scope not in session
	// Map<String, Object> model = mv.getModel();
	// model.put("name", "Avi Kotte");
	//
	// return mv;
	// }

	private OffersService offersService;

	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}

	@RequestMapping("/createoffer")
	public String createOffer(Model model, Principal principal) {

		Offer offer = null;

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

		if (results.hasErrors())
			return "createoffer";

		if (delete == null) {
			String username = principal.getName();
			offer.getUser().setUsername(username);
			offersService.saveOrUpdate(offer);

			return "offercreated";
		} else {
			offersService.delete(offer.getId());
			return "offerdeleted";
		}

	}

}
