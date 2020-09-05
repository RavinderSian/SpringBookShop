package com.personal.bookshopspring.services;

import java.util.List;
import java.util.Optional;

import com.personal.bookshopspring.models.Book;

public interface BookCRUDServices {

	Book save(Book book);

	void delete(Book book);

	List<Book> findAll();

	Optional<Book> findById(Long id);

}
