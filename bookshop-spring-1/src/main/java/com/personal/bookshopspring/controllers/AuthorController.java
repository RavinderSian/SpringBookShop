package com.personal.bookshopspring.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.bookshopspring.models.Author;
import com.personal.bookshopspring.models.Book;
import com.personal.bookshopspring.services.AuthorCRUDServicesImp;

@RestController
@RequestMapping("/author")
public class AuthorController implements CrudController<Author, Long>{

	private final AuthorCRUDServicesImp authorServices;

	public AuthorController(final AuthorCRUDServicesImp authorServices) {
		this.authorServices = authorServices;
	}

	public ResponseEntity<?> getEntity(@PathVariable Long id) {
		Author result = authorServices.findById(id).orElse(null);
		return result == null 
		? new ResponseEntity<>("Author id not present", HttpStatus.NOT_FOUND)
		: new ResponseEntity<>(result, HttpStatus.OK);
	}

	public ResponseEntity<?> delete(@PathVariable Long id) {

		Author result = authorServices.findById(id).orElse(null);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			authorServices.delete(result);
			String deletedMessage = "Deleted author of Id " + id.toString();
			return new ResponseEntity<>(deletedMessage, HttpStatus.OK);
		}
	}

	public ResponseEntity<?> add(@RequestBody Author author, BindingResult bindingResult) {
		Author result = authorServices.save(author);

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>("Could not create object", HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<>(result, HttpStatus.OK);

		}
	}

	public ResponseEntity<?> getAll() {
		List<Author> result = authorServices.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/changefirstname/{id}")
	public ResponseEntity<?> updateFirstName(@PathVariable Long id, @RequestBody String firstName) {

		Author author = authorServices.findById(id).orElse(null);
		if (author == null) {
			return new ResponseEntity<>("Author id not present", HttpStatus.NOT_FOUND);
		}
		
		author.setFirstName(firstName);

		return new ResponseEntity<>(author, HttpStatus.OK);

	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<?> getBooks(@PathVariable Long id) {

		Author author = authorServices.findById(id).orElse(null);
		
		List<Book> result = author.getBooks();

		if (result == null) {
			return new ResponseEntity<>("Author id not present", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(result, HttpStatus.OK);

		}

	}

}
