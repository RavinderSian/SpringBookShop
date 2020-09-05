package com.personal.bookshopspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.bookshopspring.models.Customer;
import com.personal.bookshopspring.repositories.CustomerRepository;

@Service
public class CustomerCRUDServicesImp implements CustomerCRUDServices{

	@Autowired
	CustomerRepository repository;

	@Override
	public Customer save(Customer customer) {
		repository.save(customer);
		return customer;
	}

	@Override
	public void delete(Customer customer) {
		repository.delete(customer);

	}

	@Override
	public List<Customer> findAll() {
		return (List<Customer>) repository.findAll();
	}

	@Override
	public Optional<Customer> findById(Long id) {
		Customer customer =  this.repository.findById(id).orElse(null);
		return Optional.ofNullable(customer);
	}
}
