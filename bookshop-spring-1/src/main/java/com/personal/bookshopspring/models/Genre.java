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
@Entity(name = "genres")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long genre_id;

	@Column(name = "genre")
	private String name;

	@JsonIgnore // This is necessary to prevent infinite recursion, combine with
				// @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) on the other
				// side of the mapping
	@OneToMany(mappedBy = "genre", orphanRemoval = true)
	private List<Book> books = new ArrayList<>();

	public Genre() {

	}
}
