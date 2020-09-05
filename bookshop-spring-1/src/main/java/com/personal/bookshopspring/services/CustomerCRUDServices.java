package com.personal.bookshopspring.services;

import java.util.List;
import java.util.Optional;

import com.personal.bookshopspring.models.Customer;

public interface CustomerCRUDServices {

	Customer save(Customer customer);
	void delete(Customer customer);
	List<Customer> findAll();
	Optional<Customer> findById(Long id);
}
