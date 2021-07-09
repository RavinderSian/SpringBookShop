package com.personal.bookshopspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.personal.bookshopspring.models.Author;
import com.personal.bookshopspring.models.Book;
import com.personal.bookshopspring.models.Customer;
import com.personal.bookshopspring.models.Genre;
import com.personal.bookshopspring.models.Sales;
import com.personal.bookshopspring.repositories.AuthorRepository;
import com.personal.bookshopspring.repositories.BookRepository;
import com.personal.bookshopspring.repositories.CustomerRepository;
import com.personal.bookshopspring.repositories.GenreRepository;
import com.personal.bookshopspring.repositories.SalesRepository;

@SpringBootApplication
public class BookshopSpring1Application implements CommandLineRunner{

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	SalesRepository salesRepository; 
	
	@Autowired
	GenreRepository genreRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookshopSpring1Application.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		Author author = new Author();
		author.setFirstName("rav");
		authorRepository.save(author);
		
		Genre genre = new Genre();
		genre.setName("art");
		genreRepository.save(genre);
		
		Book book = new Book();
		book.setTitle("title");
		book.setPrice(15);
		book.setGenre(genre);
		book.setAuthor(author);
		bookRepository.save(book);
		
		Customer customer = new Customer();
		customer.setFirstName("ravinder");
		customerRepository.save(customer);
		
		Sales sale = new Sales();
		sale.setPricePaid(15.5);
		sale.setBook(book);
		sale.setCustomer(customer);
		salesRepository.save(sale);
	}

}
