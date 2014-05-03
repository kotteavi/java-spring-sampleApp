package com.spring.web.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.web.dao.Message;
import com.spring.web.services.UsersService;

@Controller
public class MessagesController {

	private UsersService usersService;

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	@RequestMapping("/messages")
	public String showMessages() {

		return "messages";
	}
	
	@RequestMapping(value = "/getmessages", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getMessages(Principal principal) {

		List<Message> messages = null;
		
		if (principal == null) {
			messages = new ArrayList<Message>();
		} else {
			String username = principal.getName();
			messages = usersService.getMessages(username);
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("messages", messages);
		data.put("number", messages.size());

		return data;
	}

	@RequestMapping(value = "/sendmessage", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void sendMessage(Principal principal,
			@RequestBody Map<String, Object> data) {

		Integer target = (Integer) data.get("target");

		// test to see if data is received
		System.out.println(data);

		Map<String, Object> returnValue = new HashMap<String, Object>();
		returnValue.put("success", true);
		returnValue.put("target", target );
	}
	
}
