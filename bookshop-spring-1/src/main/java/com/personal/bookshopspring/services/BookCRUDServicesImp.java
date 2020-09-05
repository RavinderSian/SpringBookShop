package com.personal.bookshopspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.bookshopspring.models.Book;
import com.personal.bookshopspring.repositories.BookRepository;

@Service
public class BookCRUDServicesImp implements BookCRUDServices {

	@Autowired
	BookRepository repository;
	
	@Override
	public Book save(Book book) {
		repository.save(book);
		return book;
	}

	@Override
	public void delete(Book book) {
		repository.delete(book);
		
	}

	@Override
	public List<Book> findAll() {
		return (List<Book>) this.repository.findAll();
	}

	@Override
	public Optional<Book> findById(Long id) {
		Book book = repository.findById(id).orElse(null); //.orElse(null) returns null when id is not present or an object of type Book when the id is present in db
		return Optional.ofNullable(book);
	}

}
