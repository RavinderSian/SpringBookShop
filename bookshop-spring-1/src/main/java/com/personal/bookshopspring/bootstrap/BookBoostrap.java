package com.personal.bookshopspring.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.personal.bookshopspring.models.Author;
import com.personal.bookshopspring.models.Book;
import com.personal.bookshopspring.models.Genre;
import com.personal.bookshopspring.services.AuthorCRUDServices;
import com.personal.bookshopspring.services.BookCRUDServices;
import com.personal.bookshopspring.services.GenreCRUDServices;

@Component
public class BookBoostrap implements CommandLineRunner {

	@Autowired
	BookCRUDServices services;
	
	@Autowired
	AuthorCRUDServices authorServices;
	
	@Autowired
	GenreCRUDServices genreServices;
	
	@Override
	public void run(String... args) throws Exception {
		

		Author author = new Author();
		author.setFirstName("rav");
		authorServices.save(author);
		
		Genre genre = new Genre();
		genre.setName("art");
		genreServices.save(genre);
		
		Book book = new Book();
		book.setTitle("title");
		book.setPrice(15);
		book.setGenre(genre);
		book.setAuthor(author);
		services.save(book);
	}

}
