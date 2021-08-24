package com.personal.bookshopspring.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Entity(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "price")
	private double price;
	
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@JoinColumn(name = "fk_genre_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Genre genre;
	
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@JoinColumn(name = "fk_author_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Author author;
	
	@JsonIgnore
	@OneToMany(mappedBy = "book", orphanRemoval = true)
	private List<Sales> sales = new ArrayList<>();
	
	public Book() { //need empty constructor for jpa
		
	}
	
	public void addSale(Sales sale) {
		sales.add(sale);
		sale.setBook(this);
	}
	
	public void removeSale(Sales sale) {
		sales.remove(sale);
		sale.setBook(null);
	}
	
}
