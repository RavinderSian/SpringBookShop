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

import com.personal.bookshopspring.models.Book;
import com.personal.bookshopspring.services.BookCRUDServices;

@RestController
@RequestMapping("/book")
public class BookController implements CrudController<Book, Long>{

	private final BookCRUDServices bookServices;
	
	public BookController(BookCRUDServices bookServices) {
		this.bookServices = bookServices;
	}

	@Override
	public ResponseEntity<?> getEntity(@PathVariable Long id) {
		Book result = bookServices.findById(id).orElse(null);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Book result = bookServices.findById(id).orElse(null);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			bookServices.delete(result);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<?> add(@RequestBody Book book, BindingResult bindingResult) {
		return bindingResult.hasErrors()
		? new ResponseEntity<>(HttpStatus.NOT_FOUND)
		: new ResponseEntity<>(bookServices.save(book), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Book>> getAll() {
		List<Book> result = bookServices.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PatchMapping("/newtitle/{id}")
	public ResponseEntity<?> updateTitle(@PathVariable Long id, @RequestBody String title) {

		Book book = bookServices.findById(id).orElse(null);
		if (book == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		book.setTitle(title);
		bookServices.save(book);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}

}
