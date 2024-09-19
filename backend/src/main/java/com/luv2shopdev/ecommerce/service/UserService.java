package com.luv2shopdev.ecommerce.service;

import com.luv2shopdev.ecommerce.dto.UserDto;
import com.luv2shopdev.ecommerce.entity.User;

public interface UserService {
	
	User login(UserDto user) throws Exception;

	User register(UserDto user) throws Exception;

}
