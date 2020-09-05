package com.personal.bookshopspring.repositories;

import org.springframework.data.repository.CrudRepository;

import com.personal.bookshopspring.models.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
