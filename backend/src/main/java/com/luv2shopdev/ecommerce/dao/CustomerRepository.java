package com.luv2shopdev.ecommerce.dao;

import com.luv2shopdev.ecommerce.entity.Customer;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
@Cacheable(value = "mailCache")
    Customer findByEmail(String email);
}
