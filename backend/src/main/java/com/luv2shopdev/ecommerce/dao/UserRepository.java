package com.luv2shopdev.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luv2shopdev.ecommerce.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	User findByUserName(String userName);

	User findByEmail(String email);
	
}