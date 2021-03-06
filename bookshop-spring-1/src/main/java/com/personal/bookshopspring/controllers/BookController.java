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

import com.personal.bookshopspring.models.Book;
import com.personal.bookshopspring.models.Sales;
import com.personal.bookshopspring.services.BookCRUDServicesImp;

@RestController
@RequestMapping("/book")
public class BookController {

	private final BookCRUDServicesImp bookServices;
	
	public BookController(BookCRUDServicesImp bookServices) {
		this.bookServices = bookServices;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBook(@PathVariable Long id) {

		Book result = bookServices.findById(id).orElse(null);

		if (result == null) {
			return new ResponseEntity<String>("Book id not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(result, HttpStatus.FOUND);

	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Long id) {

		Book result = bookServices.findById(id).orElse(null);

		if (result == null) {
			return new ResponseEntity<String>("Book id not found", HttpStatus.NOT_FOUND);
		} else {
			bookServices.delete(result);
			String deletedMessage = "Deleted book of Id " + id.toString();
			return new ResponseEntity<String>(deletedMessage, HttpStatus.FOUND);

		}

	}

	@PostMapping("/add")
	public ResponseEntity<?> addBook(@RequestBody Book book, BindingResult bindingResult) {

		Book result = bookServices.save(book);

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<String>("Could not create object", HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<Book>(result, HttpStatus.CREATED);

		}

	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllBooks() {

		List<Book> result = bookServices.findAll();

		return new ResponseEntity<List<Book>>(result, HttpStatus.FOUND);

	}

	@PostMapping("/newtitle/{id}")
	public ResponseEntity<?> updateTitle(@PathVariable Long id, @RequestBody String title) {

		Book book = bookServices.findById(id).orElse(null);

		if (book == null) {
			return new ResponseEntity<String>("Book id not found", HttpStatus.NOT_FOUND);
		}
		book.setTitle(title);

		return new ResponseEntity<Book>(book, HttpStatus.FOUND);

	}

	@GetMapping("/{id}/sales")
	public ResponseEntity<?> getBooks(@PathVariable Long id) {

		Book book = bookServices.findById(id).orElse(null);

		List<Sales> result = book.getSales();

		if (result == null) {
			return new ResponseEntity<String>("Book id not found", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Sales>>(result, HttpStatus.FOUND);

		}

	}
	 
}
