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

import com.personal.bookshopspring.models.Book;
import com.personal.bookshopspring.models.Genre;
import com.personal.bookshopspring.services.GenreCRUDServices;

@RestController
@RequestMapping("/genre")
public class GenreController implements CrudController<Genre, Long> {

	private final GenreCRUDServices genreServices;

	public GenreController(GenreCRUDServices genreServices) {
		this.genreServices = genreServices;
	}

	public ResponseEntity<?> getEntity(@PathVariable Long id) {

		Genre result = genreServices.findById(id).orElse(null);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public ResponseEntity<?> delete(@PathVariable Long id) {

		Genre result = genreServices.findById(id).orElse(null);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			genreServices.delete(result);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	public ResponseEntity<?> add(@RequestBody Genre genre, BindingResult bindingResult) {
		Genre result = genreServices.save(genre);

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>("Could not create object", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	public ResponseEntity<List<Genre>> getAll() {
		List<Genre> result = genreServices.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PatchMapping("/genrename/{id}")
	public ResponseEntity<?> updateName(@PathVariable Long id, @RequestBody String name) {
		Genre genre = genreServices.findById(id).orElse(null);
		
		if (genre == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		genre.setName(name);
		genreServices.save(genre);
		return new ResponseEntity<>(genre, HttpStatus.OK);

	}
	
	@GetMapping("/{id}/books")
	public ResponseEntity<?> getBooks(@PathVariable Long id) {
		
		Genre genre = genreServices.findById(id).orElse(null);

		if (genre == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			List<Book> books = genre.getBooks();
			return new ResponseEntity<>(books, HttpStatus.OK);
		}
	}

}