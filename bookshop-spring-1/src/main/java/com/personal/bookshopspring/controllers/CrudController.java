package com.personal.bookshopspring.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CrudController<T, ID> {

	@GetMapping("/{id}")
	public ResponseEntity<?> getEntity(@PathVariable ID id);
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll();
	
	@PutMapping("/add")
	public ResponseEntity<?> add(@RequestBody T t, BindingResult bindingResult);
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable ID id);
	
}
