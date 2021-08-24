package com.personal.bookshopspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.bookshopspring.models.Genre;
import com.personal.bookshopspring.repositories.GenreRepository;

@Service
public class GenreCRUDServiceImp implements GenreCRUDServices {
	
	private final GenreRepository repository;

	public GenreCRUDServiceImp(GenreRepository repository) {
		this.repository = repository;
	}

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
		return Optional.ofNullable(repository.findById(id).orElse(null));
	}

}
