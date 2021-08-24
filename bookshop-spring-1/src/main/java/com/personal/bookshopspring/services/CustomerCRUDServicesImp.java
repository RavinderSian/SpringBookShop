package com.personal.bookshopspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.bookshopspring.models.Customer;
import com.personal.bookshopspring.repositories.CustomerRepository;

@Service
public class CustomerCRUDServicesImp implements CustomerCRUDServices{

	private final CustomerRepository repository;
	
	public CustomerCRUDServicesImp(CustomerRepository repository) {
		this.repository = repository;
	}

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
		return Optional.ofNullable(repository.findById(id).orElse(null));
	}
}
