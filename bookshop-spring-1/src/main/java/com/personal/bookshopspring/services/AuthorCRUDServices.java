package com.personal.bookshopspring.services;

import java.util.List;
import java.util.Optional;

import com.personal.bookshopspring.models.Author;

public interface AuthorCRUDServices {

	Author save(Author author);
	void delete(Author author);
	List<Author> findAll();
	Optional<Author> findById(Long id);
}
