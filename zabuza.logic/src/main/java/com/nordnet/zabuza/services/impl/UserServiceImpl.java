package com.nordnet.zabuza.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nordnet.zabuza.domain.model.User;
import com.nordnet.zabuza.domain.repository.UserRepository;
import com.nordnet.zabuza.services.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	/** {@link UserRepository} userRepository. */
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
}
