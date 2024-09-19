package com.luv2shopdev.ecommerce.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2shopdev.ecommerce.dao.UserRepository;
import com.luv2shopdev.ecommerce.dto.UserDto;
import com.luv2shopdev.ecommerce.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User login(UserDto user) throws Exception {
		User userDetails;
		userDetails = userRepository.findByUserName(user.getUserName());
		if (userDetails == null) {
			userDetails = userRepository.findByEmail(user.getEmail());
		}
		if (userDetails == null) {
			throw new Exception("User does not exist !");
		}
		if (!userDetails.getPassword().equals(user.getPassword())) {
			throw new Exception("Incorrect password");
		}
		return userDetails;
	}

	@Override
	@Transactional
	public User register(UserDto userDto) throws Exception {

		try {
			User user = new User();
			User newUser;
			BeanUtils.copyProperties(userDto, user);
			if (user.getFirstName() == null) {
				throw new Exception("FirstName cannot be empty! ");
			} else if (user.getUserName() == null) {
				throw new Exception("UserName cannot be empty! ");
			} else if (user.getEmail() == null) {
				throw new Exception("Email cannot be empty! ");
			} else if (user.getPassword() == null) {
				throw new Exception("Password cannot be empty! ");
			}
			newUser = userRepository.save(user);
			return newUser;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

}