package com.personal.bookshopspring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.bookshopspring.models.Sales;
import com.personal.bookshopspring.services.SalesCRUDServices;
import com.personal.bookshopspring.services.SalesCRUDServicesImp;

@RestController
@RequestMapping("/sale")
public class SalesController {

	@Autowired
	SalesCRUDServices salesServices = new SalesCRUDServicesImp();

	@GetMapping("/{id}")
	public ResponseEntity<?> getSale(@PathVariable Long id) {

		Sales result = salesServices.findById(id).orElse(null);

		if (result == null) {
			return new ResponseEntity<String>("Sale id not present", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Sales>(result, HttpStatus.FOUND);

	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<?> deleteGenre(@PathVariable Long id) {

		Sales result = salesServices.findById(id).orElse(null);

		if (result == null) {
			return new ResponseEntity<String>("Sale id not present", HttpStatus.NOT_FOUND);
		} else {
			salesServices.delete(result);
			String deletedMessage = "Deleted customer of Id " + id.toString();
			return new ResponseEntity<String>(deletedMessage, HttpStatus.FOUND);

		}

	}

	@PostMapping("/add")
	public ResponseEntity<?> addGenre(@RequestBody Sales sale, BindingResult bindingResult) {

		Sales result = salesServices.save(sale);

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<String>("Could not create object", HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<Sales>(result, HttpStatus.CREATED);

		}

	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllGenres() {

		List<Sales> result = salesServices.findAll();

		return new ResponseEntity<List<Sales>>(result, HttpStatus.FOUND);

	}

	@PostMapping("/newprice/{id}")
	public ResponseEntity<?> updatePricePaid(@PathVariable Long id, @RequestBody double price, BindingResult bindingResult) {

		Sales sale = salesServices.findById(id).orElse(null);
		
		if(sale == null) {
			return new ResponseEntity<String>("sale id not present", HttpStatus.NOT_FOUND);
		}		
		
		sale.setPricePaid(price);
		return new ResponseEntity<Sales>(sale, HttpStatus.FOUND);

	}
	
}
