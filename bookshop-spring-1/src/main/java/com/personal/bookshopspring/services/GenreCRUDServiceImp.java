package com.personal.bookshopspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.bookshopspring.models.Genre;
import com.personal.bookshopspring.repositories.GenreRepository;

@Service
public class GenreCRUDServiceImp implements GenreCRUDServices {
	
	@Autowired
	GenreRepository repository;

	@Override
	public Genre save(Genre genre) {
		repository.save(genre);
		return genre;
	}

	@Override
	public void delete(Genre genre) {
		repository.delete(genre);

	}

	@Override
	public List<Genre> findAll() {
		return (List<Genre>) repository.findAll();
	}

	@Override
	public Optional<Genre> findById(Long id) {
		Genre genre =  this.repository.findById(id).orElse(null);
		return Optional.ofNullable(genre);
	}

}
