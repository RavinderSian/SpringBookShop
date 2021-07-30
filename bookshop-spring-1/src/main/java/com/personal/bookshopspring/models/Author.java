package com.personal.bookshopspring.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity(name = "authors")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long authorId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "author", orphanRemoval = true)
	private List<Book> books = new ArrayList<>();
	
	public Author() {
		
	}
	
	public void addBook(Book book) {
		books.add(book);
		book.setAuthor(this);
	}
	
	public void removeBook(Book book) {
		books.remove(book);
		book.setAuthor(null);
	}

}
