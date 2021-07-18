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

import com.personal.bookshopspring.models.Sales;
import com.personal.bookshopspring.services.SalesCRUDServices;

@RestController
@RequestMapping("/sale")
public class SalesController implements CrudController<Sales, Long> {
	
	private final SalesCRUDServices salesServices;
	
	public SalesController(SalesCRUDServices salesServices) {
		this.salesServices = salesServices;
	}

	public ResponseEntity<?> getEntity(@PathVariable Long id) {

		Sales result = salesServices.findById(id).orElse(null);

		if (result == null) {
			return new ResponseEntity<String>("Sale id not present", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Sales>(result, HttpStatus.OK);
	}

	public ResponseEntity<?> delete(@PathVariable Long id) {

		Sales result = salesServices.findById(id).orElse(null);

		if (result == null) {
			return new ResponseEntity<String>("Sale id not present", HttpStatus.NOT_FOUND);
		} else {
			salesServices.delete(result);
			String deletedMessage = "Deleted customer of Id " + id.toString();
			return new ResponseEntity<String>(deletedMessage, HttpStatus.OK);
		}
	}
	
	public ResponseEntity<?> add(@RequestBody Sales sale, BindingResult bindingResult) {

		Sales result = salesServices.save(sale);
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<String>("Could not create object", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Sales>(result, HttpStatus.OK);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		List<Sales> result = salesServices.findAll();
		return new ResponseEntity<List<Sales>>(result, HttpStatus.OK);
	}

	@PatchMapping("/newprice/{id}")
	public ResponseEntity<?> updatePrice(@PathVariable Long id, @RequestBody double price, BindingResult bindingResult) {

		Sales sale = salesServices.findById(id).orElse(null);
		if(sale == null) {
			return new ResponseEntity<String>("sale id not present", HttpStatus.NOT_FOUND);
		}		
		sale.setPricePaid(price);
		salesServices.save(sale);
		return new ResponseEntity<Sales>(sale, HttpStatus.OK);
	}
	
}
