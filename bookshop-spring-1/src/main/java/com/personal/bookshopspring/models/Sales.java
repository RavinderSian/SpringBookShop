package com.personal.bookshopspring.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity(name = "sales")
public class Sales {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long saleId;
	
	@Column(name = "date_of_sale")
	private LocalDate saleDate;
	
	@Column(name = "amount_paid")
	private double pricePaid;
	
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_book_id")
	private Book book;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_customer_id")
	private Customer customer;
	
	public Sales() { //need empty constructor for jpa
		
	}
	
	@PrePersist
	public void preCreation() {  // This method uses assigns the date of sale to the creation date
		this.saleDate = LocalDate.now();
	}

}
