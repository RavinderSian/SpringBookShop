package com.personal.bookshopspring.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.bookshopspring.models.Customer;
import com.personal.bookshopspring.models.Sales;
import com.personal.bookshopspring.services.CustomerCRUDServicesImp;

@RestController
@RequestMapping("/customer")
public class CustomerController implements CrudController<Customer, Long> {
	
	private final CustomerCRUDServicesImp customerServices;
	
	public CustomerController(CustomerCRUDServicesImp customerServices) {
		this.customerServices = customerServices;
	}

	public ResponseEntity<?> getEntity(@PathVariable Long id){
		
		Customer result = customerServices.findById(id).orElse(null);
		
		if (result == null) {
			return new ResponseEntity<String>("Customer id not present", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(result, HttpStatus.OK);
	}
	
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		Customer result = customerServices.findById(id).orElse(null);
		
		if (result == null) {
			return new ResponseEntity<String>("Customer id not present", HttpStatus.NOT_FOUND);
		}else {
			customerServices.delete(result);
			String deletedMessage = "Deleted customer of Id " + id.toString();
			return new ResponseEntity<String>(deletedMessage, HttpStatus.OK);
		}
	}
	
	public ResponseEntity<?> add(@RequestBody Customer customer, BindingResult bindingResult){
		Customer result = customerServices.save(customer);
		
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<String>("Could not create customer", HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Customer>(result, HttpStatus.OK);
		}
	}
	
	public ResponseEntity<?> getAll(){
		List<Customer> result = customerServices.findAll();
		return new ResponseEntity<List<Customer>>(result, HttpStatus.OK);
	}
	
	@PatchMapping("/namechange/{id}")
	public ResponseEntity<?> updateFirstName(@PathVariable Long id, @RequestBody String firstName){
		 
		Customer customer = customerServices.findById(id).orElse(null);
		
		if (customer == null) {
			return new ResponseEntity<String>("Customer id not present", HttpStatus.NOT_FOUND);
		}
		customer.setFirstName(firstName);
		customerServices.save(customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@GetMapping("/{id}/purchases")
	public ResponseEntity<?> getPurchases(@PathVariable Long id){
		
		Customer customer = customerServices.findById(id).orElse(null);
		
		if (customer == null) {
			return new ResponseEntity<String>("Customer id not present", HttpStatus.NOT_FOUND);
		}
		List<Sales> purchases = customer.getSales();
		return new ResponseEntity<List<Sales>>(purchases, HttpStatus.OK);
		
	}

}
