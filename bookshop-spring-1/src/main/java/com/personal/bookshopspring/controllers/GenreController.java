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
import com.personal.bookshopspring.models.Genre;
import com.personal.bookshopspring.services.GenreCRUDServiceImp;

@RestController
@RequestMapping("/genre")
public class GenreController implements CrudController<Genre, Long> {

	private final GenreCRUDServiceImp genreServices;

	public GenreController(GenreCRUDServiceImp genreServices) {
		this.genreServices = genreServices;
	}

	public ResponseEntity<?> getEntity(@PathVariable Long id) {

		Genre result = genreServices.findById(id).orElse(null);

		if (result == null) {
			return new ResponseEntity<String>("Genre id not present", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Genre>(result, HttpStatus.OK);

	}

	public ResponseEntity<?> delete(@PathVariable Long id) {

		Genre result = genreServices.findById(id).orElse(null);

		if (result == null) {
			return new ResponseEntity<String>("Genre id not present", HttpStatus.NOT_FOUND);
		} else {
			genreServices.delete(result);
			String deletedMessage = "Deleted customer of Id " + id.toString();
			return new ResponseEntity<String>(deletedMessage, HttpStatus.OK);
		}
	}

	public ResponseEntity<?> add(@RequestBody Genre genre, BindingResult bindingResult) {

		Genre result = genreServices.save(genre);

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<String>("Could not create object", HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<Genre>(result, HttpStatus.CREATED);
		}
	}

	public ResponseEntity<?> getAll() {

		List<Genre> result = genreServices.findAll();

		return new ResponseEntity<List<Genre>>(result, HttpStatus.FOUND);
	}

	@PostMapping("/genrename/{id}")
	public ResponseEntity<?> updateFirstName(@PathVariable Long id, @RequestBody String name) {

		Genre genre = genreServices.findById(id).orElse(null);
		
		if (genre == null) {
			return new ResponseEntity<String>("Genre id not present", HttpStatus.NOT_FOUND);
		}
		genre.setName(name);

		return new ResponseEntity<Genre>(genre, HttpStatus.FOUND);

	}
	
	@GetMapping("/{id}/books")
	public ResponseEntity<?> getBooks(@PathVariable Long id) {

		Genre genre = genreServices.findById(id).orElse(null);

		if (genre == null) {
			return new ResponseEntity<String>("Genre id not present", HttpStatus.NOT_FOUND);
		} else {
			List<Book> books = genre.getBooks();
			return new ResponseEntity<List<Book>>(books, HttpStatus.FOUND);
		}
	}

}