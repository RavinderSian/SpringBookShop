package com.personal.bookshopspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.bookshopspring.models.Author;
import com.personal.bookshopspring.repositories.AuthorRepository;

@Service
public class AuthorCRUDServicesImp implements AuthorCRUDServices{
	
	private final AuthorRepository repository;
	
	public AuthorCRUDServicesImp(AuthorRepository repository) {
		this.repository = repository;
	}

	@Override
	public Author save(Author author) {
		repository.save(author);
		return author;
	}

	@Override
	public void delete(Author author) {
		repository.delete(author);
		
	}

	@Override
	public List<Author> findAll() {
		return (List<Author>) this.repository.findAll();
	}

	@Override
	public Optional<Author> findById(Long id) {
		Author author = repository.findById(id).orElse(null); //.orElse(null) returns null when id is not present or an object of type Book when the id is present in db
		return Optional.ofNullable(author);
	}
}
