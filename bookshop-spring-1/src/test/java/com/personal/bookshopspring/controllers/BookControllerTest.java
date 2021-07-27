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
import com.personal.bookshopspring.services.BookCRUDServices;

@WebMvcTest(BookController.class)
class BookControllerTest implements ControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private BookController controller;
	
	@MockBean
	private BookCRUDServices services;
	
	@Override
	@BeforeEach
	public void setUp() {
		controller = new BookController(services);
	}

	@Override
	@Test
	public void test_Controller_IsNotNull() {
		assertThat(controller, not(equalTo(null)));
	}

	@Override
	@Test
	public void test_GetEntity_ReturnsCorrectStatusAndResponse_WhenEntityPresent() throws Exception {
		Book book = new Book();
		book.setTitle("test");
		when(services.findById(1L)).thenReturn(Optional.of(book));
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String responseJson = ow.writeValueAsString(book);
		
		mockMvc.perform(get("/book/1"))
		.andExpect(status().isOk())
		.andExpect(content().json(responseJson));
	}
	
	@Override
	@Test
	public void test_GetEntity_ReturnsCorrectStatusAndResponse_WhenEntityNotPresent() throws Exception {
		mockMvc.perform(get("/book/1"))
		.andExpect(status().isNotFound())
		.andExpect(content().string("Book id not found"));
	}
	
	@Override
	@Test
	public void test_Delete_ReturnsCorrectStatusAndResponse_WhenEntityPresent() throws Exception {
		Book book = new Book();
		book.setTitle("test");
		when(services.findById(1L)).thenReturn(Optional.of(book));
		
		mockMvc.perform(delete("/book/delete/1"))
		.andExpect(status().isOk())
		.andExpect(content().string("Deleted book of Id 1"));
	}
	
	@Override
	@Test
	public void test_Delete_ReturnsCorrectStatusAndResponse_WhenEntityNotPresent() throws Exception {
		mockMvc.perform(delete("/book/delete/1"))
		.andExpect(status().isNotFound())
		.andExpect(content().string("Book id not found"));
	}
	
	@Override
	@Test
	public void test_Add_ReturnsCorrectStatusAndResponse_WhenGivenValidEntity() throws Exception {
		Book book = new Book();
		book.setTitle("test");
		book.setBookId(1L);
		
		when(services.save(book)).thenReturn(book);

	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(book);
		
		mockMvc.perform(put("/book/add").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
		.andExpect(status().isOk())
		.andExpect(content().json("{'bookId' : 1, 'title' : 'test'}"));
	}
	
	@Override
	@Test
	public void test_GetAll_ReturnsCorrectStatusAndResponse_WhenCalled() throws Exception {
		Book book = new Book();
		book.setTitle("test");
		
		Book secondBook = new Book();
		secondBook.setTitle("second test");
		
		List<Book> books = new ArrayList<>();
		books.add(book);
		books.add(secondBook);
		
		when(services.findAll()).thenReturn(books);
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String responseJson = ow.writeValueAsString(books);
		
		mockMvc.perform(get("/book/all"))
		.andExpect(status().isOk())
		.andExpect(content().json(responseJson));
	}
	
	@Test
	void test_UpdateTitle_ReturnsCorrectStatusAndResponse_WhenEntityPresentAndRequestValid() throws Exception {
		Book book = new Book();
		book.setTitle("test");
		book.setBookId(1L);
		
		when(services.findById(1L)).thenReturn(Optional.of(book));
		
		mockMvc.perform(patch("/book/newtitle/1").contentType(MediaType.APPLICATION_JSON_VALUE).content("new"))
		.andExpect(status().isOk())
		.andExpect(content().json("{'bookId' : 1, 'title' : 'new'}"));
	}
	
	@Test
	void test_UpdateTitle_ReturnsCorrectStatusAndResponse_WhenEntityNotPresentAndRequestValid() throws Exception {
		mockMvc.perform(patch("/book/newtitle/1").contentType(MediaType.APPLICATION_JSON_VALUE).content("new"))
		.andExpect(status().isNotFound())
		.andExpect(content().string("Book id not found"));
	}
	
}
