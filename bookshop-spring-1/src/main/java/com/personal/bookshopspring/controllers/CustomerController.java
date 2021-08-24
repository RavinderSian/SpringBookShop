package com.personal.bookshopspring.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.bookshopspring.models.Customer;
import com.personal.bookshopspring.services.CustomerCRUDServices;

@RestController
@RequestMapping("/customer")
public class CustomerController implements CrudController<Customer, Long> {
	
	private final CustomerCRUDServices customerServices;
	
	public CustomerController(CustomerCRUDServices customerServices) {
		this.customerServices = customerServices;
	}

	public ResponseEntity<?> getEntity(@PathVariable Long id){
		
		Customer result = customerServices.findById(id).orElse(null);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		Customer result = customerServices.findById(id).orElse(null);
		
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			customerServices.delete(result);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	public ResponseEntity<?> add(@RequestBody Customer customer, BindingResult bindingResult){
		Customer result = customerServices.save(customer);
		return bindingResult.hasErrors()
		? new ResponseEntity<>("Could not create customer", HttpStatus.BAD_REQUEST)
		: new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
	public ResponseEntity<List<Customer>> getAll(){
		List<Customer> result = customerServices.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PatchMapping("/namechange/{id}")
	public ResponseEntity<?> updateFirstName(@PathVariable Long id, @RequestBody String firstName){
		 
		Customer customer = customerServices.findById(id).orElse(null);
		
		if (customer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		customer.setFirstName(firstName);
		customerServices.save(customer);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
}
