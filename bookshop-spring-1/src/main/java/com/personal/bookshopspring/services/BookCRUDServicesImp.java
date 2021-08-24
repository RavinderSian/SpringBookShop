package com.personal.bookshopspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.bookshopspring.models.Book;
import com.personal.bookshopspring.repositories.BookRepository;

@Service
public class BookCRUDServicesImp implements BookCRUDServices {

	private final BookRepository repository;
	
	public BookCRUDServicesImp(BookRepository repository) {
		this.repository = repository;
	}

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
		//.orElse(null) returns null when id is not present or an object of type Book when the id is present in db
		return Optional.ofNullable(repository.findById(id).orElse(null));
	}

}
