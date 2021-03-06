package com.spring.web.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.web.dao.FormValidationGroup;
import com.spring.web.dao.PersistanceValidationGroup;
import com.spring.web.models.Message;
import com.spring.web.models.Offer;
import com.spring.web.models.User;
import com.spring.web.services.UsersService;

@Controller
public class LoginController {
	private UsersService usersService;

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/login")
	public String routeToLogin() {

		return "login";
	}

	@RequestMapping("/denied")
	public String routeToDenied() {

		return "denied";
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {

		List<User> users = usersService.getAllUsers();
		model.addAttribute("users", users);

		return "admin";
	}

	@RequestMapping("/loggedout")
	public String routeToLogout() {

		return "loggedout";
	}

	@RequestMapping("/newaccount")
	public String newAccount(Model model) {

		model.addAttribute("user", new User());

		return "newaccount";
	}

	@RequestMapping(value = "/accountcreated", method = RequestMethod.POST)
	public String createAccount(
			@Validated(FormValidationGroup.class) User user,
			BindingResult results) {

		if (results.hasErrors()) {
			return "newaccount";
		}

		// default user has admin access
		user.setAuthority("ROLE_ADMIN");
		user.setEnabled(true);

		// Check for user availability
		if (usersService.isUsernameAvailable(user.getUsername())) {
			results.rejectValue("username", "DuplicateKey.User.username");
			return "newaccount";
		}

		try {
			usersService.createUser(user);
		} catch (DuplicateKeyException e) {
			results.rejectValue("username", "DuplicateKey.User.username");
			return "newaccount";
		}

		return "accountcreated";
	}

}
