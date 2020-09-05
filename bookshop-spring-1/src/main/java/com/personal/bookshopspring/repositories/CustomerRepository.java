package com.personal.bookshopspring.repositories;

import org.springframework.data.repository.CrudRepository;

import com.personal.bookshopspring.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
