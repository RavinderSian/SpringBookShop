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
public class AuthorController {

	private final AuthorCRUDServicesImp authorServices;

	public AuthorController(AuthorCRUDServicesImp authorServices) {
		this.authorServices = authorServices;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getAuthor(@PathVariable Long id) {

		Author result = authorServices.findById(id).orElse(null);

		if (result == null) {
			return new ResponseEntity<String>("Author id not present", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Author>(result, HttpStatus.FOUND);

	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {

		Author result = authorServices.findById(id).orElse(null);

		if (result == null) {
			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		} else {
			authorServices.delete(result);
			String deletedMessage = "Deleted author of Id " + id.toString();
			return new ResponseEntity<String>(deletedMessage, HttpStatus.FOUND);

		}

	}

	@PostMapping("/add")
	public ResponseEntity<?> addAuthor(@RequestBody Author author, BindingResult bindingResult) {

		Author result = authorServices.save(author);

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<String>("Could not create object", HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<Author>(result, HttpStatus.CREATED);

		}

	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllAuthors() {

		List<Author> result = authorServices.findAll();

		return new ResponseEntity<List<Author>>(result, HttpStatus.FOUND);

	}

	@PostMapping("/changefirstname/{id}")
	public ResponseEntity<?> updateFirstName(@PathVariable Long id, @RequestBody String firstName) {

		Author author = authorServices.findById(id).orElse(null);
		if (author == null) {
			return new ResponseEntity<String>("Author id not present", HttpStatus.NOT_FOUND);
		}
		
		author.setFirstName(firstName);

		return new ResponseEntity<Author>(author, HttpStatus.FOUND);

	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<?> getBooks(@PathVariable Long id) {

		Author author = authorServices.findById(id).orElse(null);
		
		List<Book> result = author.getBooks();

		if (result == null) {
			return new ResponseEntity<String>("Author id not present", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Book>>(result, HttpStatus.FOUND);

		}

	}

}
