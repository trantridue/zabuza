package com.nordnet.zabuza.ws.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import nordnet.tools.converter.Converter;
import nordnet.tools.converter.exceptions.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nordnet.common.wadl.WadlController;
import com.nordnet.zabuza.domain.model.User;
import com.nordnet.zabuza.services.UserService;
import com.wordnik.swagger.annotations.Api;

/**
 * Web Service interface for user
 *
 * @author dtrantri
 */

@Api(value = "ZABUZA", description = "zabuza API")
@Controller
@RequestMapping("/user/")
public class UserController extends WadlController {

	@Autowired
	private UserService userService;

	@Autowired
	private Converter converter;

	@RequestMapping(value = "all", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<com.nordnet.zabuza.ws.entities.User> getAllUser() throws ConverterException {
		List<com.nordnet.zabuza.ws.entities.User> users = new ArrayList<com.nordnet.zabuza.ws.entities.User>();
		try {
			List<User> userModels = userService.getAllUser();
			for (User user : userModels) {
				users.add(converter.convert(user, com.nordnet.zabuza.ws.entities.User.class));
			}
			return users;
		} catch (Exception e) {
			throw e;
		}
	}

}
