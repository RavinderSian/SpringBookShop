package com.personal.bookshopspring.repositories;

import org.springframework.data.repository.CrudRepository;

import com.personal.bookshopspring.models.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {

}
