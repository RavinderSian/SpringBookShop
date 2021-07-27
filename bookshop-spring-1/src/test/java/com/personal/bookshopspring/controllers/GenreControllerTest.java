package com.personal.bookshopspring.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.personal.bookshopspring.models.Book;
import com.personal.bookshopspring.models.Genre;
import com.personal.bookshopspring.services.GenreCRUDServices;

@WebMvcTest(GenreController.class)
class GenreControllerTest implements ControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private GenreController controller;
	
	@MockBean
	private GenreCRUDServices services;
	
	@Override
	@BeforeEach
	public void setUp() {
		controller = new GenreController(services);
	}

	@Override
	@Test
	public void test_Controller_IsNotNull() {
		assertThat(controller, not(equalTo(null)));
	}

	@Override
	@Test
	public void test_GetEntity_ReturnsCorrectStatusAndResponse_WhenEntityPresent() throws Exception {
		Genre genre = new Genre();
		genre.setGenreId(1L);
		genre.setName("test");
		when(services.findById(1L)).thenReturn(Optional.of(genre));
		
		mockMvc.perform(get("/genre/1"))
		.andExpect(content().json("{'genreId' : 1, 'name' : 'test'}"))
		.andExpect(status().isOk());
	}

	@Override
	@Test
	public void test_GetEntity_ReturnsCorrectStatusAndResponse_WhenEntityNotPresent() throws Exception {
		mockMvc.perform(get("/genre/1"))
		.andExpect(content().string("Genre id not present"))
		.andExpect(status().isNotFound());
	}

	@Override
	@Test
	public void test_Delete_ReturnsCorrectStatusAndResponse_WhenEntityPresent() throws Exception {
		Genre genre = new Genre();
		genre.setGenreId(1L);
		genre.setName("test");
		when(services.findById(1L)).thenReturn(Optional.of(genre));

		mockMvc.perform(delete("/genre/delete/1"))
		.andExpect(content().string("Deleted genre of id 1"))
		.andExpect(status().isOk());
	}

	@Override
	@Test
	public void test_Delete_ReturnsCorrectStatusAndResponse_WhenEntityNotPresent() throws Exception {
		mockMvc.perform(delete("/genre/delete/1"))
		.andExpect(content().string("Genre id not present"))
		.andExpect(status().isNotFound());
	}

	@Override
	@Test
	public void test_Add_ReturnsCorrectStatusAndResponse_WhenGivenValidEntity() throws Exception {
		Genre genre = new Genre();
		genre.setGenreId(1L);
		genre.setName("test");
		
		when(services.save(genre)).thenReturn(genre);

	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(genre);
		
		mockMvc.perform(put("/genre/add").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
		.andExpect(content().json("{'genreId' : 1, 'name' : 'test'}"))
		.andExpect(status().isOk());

	}

	@Override
	@Test
	public void test_GetAll_ReturnsCorrectStatusAndResponse_WhenCalled() throws Exception {
		Genre genre = new Genre();
		genre.setGenreId(1L);
		genre.setName("test");
		
		Genre secondGenre = new Genre();
		secondGenre.setGenreId(2L);
		secondGenre.setName("test");
		
		List<Genre> genres = new ArrayList<>();
		genres.add(genre);
		genres.add(secondGenre);
		
		when(services.findAll()).thenReturn(genres);
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String responseJson = ow.writeValueAsString(genres);
		
		mockMvc.perform(get("/genre/all"))
		.andExpect(status().isOk())
		.andExpect(content().json(responseJson));
	}
	
	@Test
	void test_Update_ReturnsCorrectStatusAndResponse_WhenEntityPresentAndRequestValid() throws Exception {
		mockMvc.perform(patch("/genre/genrename/1").contentType(MediaType.APPLICATION_JSON_VALUE).content("new"))
		.andExpect(content().string("Genre id not present"))
		.andExpect(status().isNotFound());
	}

	@Test
	void test_Update_ReturnsCorrectStatusAndResponse_WhenEntityNotPresentAndRequestValid() throws Exception {
		
		Genre genre = new Genre();
		genre.setGenreId(1L);
		genre.setName("test");
		when(services.findById(1L)).thenReturn(Optional.of(genre));
		
		mockMvc.perform(patch("/genre/genrename/1").contentType(MediaType.APPLICATION_JSON_VALUE).content("new"))
		.andExpect(content().json("{ 'genreId' : 1, 'name' : 'new'}"))
		.andExpect(status().isOk());
	}
	
	@Test
	void test_GetBooks_ReturnsCorrectStatusAndResponse_WhenEntityPresentAndRequestValid() throws Exception {
		
		Genre genre = new Genre();
		genre.setGenreId(1L);
		genre.setName("test");
		
		Book book = new Book();
		book.setBookId(1L);
		book.setTitle("test");
		book.setPrice(50.00);
		
		Book secondBook = new Book();
		secondBook.setBookId(2L);
		secondBook.setTitle("testing");
		secondBook.setPrice(50.00);
		
		genre.addBook(book);
		genre.addBook(secondBook);
		
		when(services.findById(1L)).thenReturn(Optional.of(genre));
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String responseJson = ow.writeValueAsString(genre.getBooks());
		
		mockMvc.perform(get("/genre/1/books"))
		.andExpect(content().json(responseJson))
		.andExpect(status().isOk());
	}
	
	@Test
	void test_GetBooks_ReturnsCorrectStatusAndResponse_WhenEntityNotPresentAndRequestValid() throws Exception {
		mockMvc.perform(get("/genre/1/books"))
		.andExpect(content().string("Genre id not present"))
		.andExpect(status().isNotFound());
	}
	
}
