package com.personal.bookshopspring.services;

import java.util.List;
import java.util.Optional;

import com.personal.bookshopspring.models.Sales;

public interface SalesCRUDServices {
	
	Sales save(Sales sales);
	void delete(Sales sales);
	List<Sales> findAll();
	Optional<Sales> findById(Long id);
}
