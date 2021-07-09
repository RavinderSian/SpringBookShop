package com.personal.bookshopspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.bookshopspring.models.Sales;
import com.personal.bookshopspring.repositories.SalesRepository;

@Service
public class SalesCRUDServicesImp implements SalesCRUDServices{
	
	private final SalesRepository repository;

	public SalesCRUDServicesImp(SalesRepository repository) {
		this.repository = repository;
	}

	@Override
	public Sales save(Sales sales) {
		repository.save(sales);
		return sales;
	}

	@Override
	public void delete(Sales sales) {
		repository.delete(sales);

	}

	@Override
	public List<Sales> findAll() {
		return (List<Sales>) repository.findAll();
	}

	@Override
	public Optional<Sales> findById(Long id) {
		Sales sales =  this.repository.findById(id).orElse(null);
		return Optional.ofNullable(sales);
	}

}
