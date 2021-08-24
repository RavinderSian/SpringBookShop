package com.personal.bookshopspring.repositories;

import org.springframework.data.repository.CrudRepository;

import com.personal.bookshopspring.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
