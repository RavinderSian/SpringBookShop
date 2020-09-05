package com.personal.bookshopspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.bookshopspring.models.Genre;

@Service
public interface GenreCRUDServices {

	Genre save(Genre genre);
	void delete(Genre genre);
	List<Genre> findAll();
	Optional<Genre> findById(Long id);
}
