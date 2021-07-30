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
import com.personal.bookshopspring.models.Author;
import com.personal.bookshopspring.models.Book;
import com.personal.bookshopspring.services.AuthorCRUDServices;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest implements ControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private AuthorController controller;
	
	@MockBean
	private AuthorCRUDServices services;
	
	@Override
	@BeforeEach
	public void setUp(){
		controller = new AuthorController(services);
	}

	@Override
	@Test
	public void test_Controller_IsNotNull() {
		assertThat(controller, not(equalTo(null)));
	}

	@Override
	@Test
	public void test_GetEntity_ReturnsCorrectStatusAndResponse_WhenEntityPresent() throws Exception {
		Author author = new Author();
		author.setAuthorId(1L);
		author.setFirstName("test");
		when(services.findById(1L)).thenReturn(Optional.of(author));
		
		mockMvc.perform(get("/author/1"))
		.andExpect(content().json("{'authorId' : 1, 'firstName' : 'test'}"))
		.andExpect(status().isOk());
	}

	@Override
	@Test
	public void test_GetEntity_ReturnsCorrectStatusAndResponse_WhenEntityNotPresent() throws Exception {
		mockMvc.perform(get("/author/1"))
		.andExpect(content().string("Author id not present"))
		.andExpect(status().isNotFound());
	}

	@Override
	@Test
	public void test_Delete_ReturnsCorrectStatusAndResponse_WhenEntityPresent() throws Exception {
		Author author = new Author();
		author.setAuthorId(1L);
		author.setFirstName("test");
		when(services.findById(1L)).thenReturn(Optional.of(author));
		
		mockMvc.perform(delete("/author/delete/1"))
		.andExpect(content().string("Deleted author of id 1"))
		.andExpect(status().isOk());
	}

	@Override
	@Test
	public void test_Delete_ReturnsCorrectStatusAndResponse_WhenEntityNotPresent() throws Exception {
		mockMvc.perform(delete("/author/delete/1"))
		.andExpect(content().string("Author id not present"))
		.andExpect(status().isNotFound());
	}

	@Override
	@Test
	public void test_Add_ReturnsCorrectStatusAndResponse_WhenGivenValidEntity() throws Exception {
		Author author = new Author();
		author.setAuthorId(1L);
		author.setFirstName("test");
		when(services.save(author)).thenReturn(author);
		
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(author);
		
		mockMvc.perform(put("/author/add").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
		.andExpect(status().isOk())
		.andExpect(content().json("{'authorId' : 1, 'firstName' : 'test'}"));
	}

	@Override
	@Test
	public void test_GetAll_ReturnsCorrectStatusAndResponse_WhenCalled() throws Exception {
		Author author = new Author();
		author.setAuthorId(1L);
		author.setFirstName("test");
		Author secondAuthor = new Author();
		secondAuthor.setAuthorId(2L);
		secondAuthor.setFirstName("testing");
		
		List<Author> authors = new ArrayList<>();
		authors.add(author);
		authors.add(secondAuthor);
		when(services.findAll()).thenReturn(authors);
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String responseJson = ow.writeValueAsString(authors);
		
		mockMvc.perform(get("/author/all"))
		.andExpect(status().isOk())
		.andExpect(content().json(responseJson));
	}
	
	@Test
	void test_GetBooks_ReturnsCorrectStatusAndResponse_WhenEntityPresent() throws Exception {
		Author author = new Author();
		author.setAuthorId(1L);
		author.setFirstName("test");
		
		Book book = new Book();
		book.setBookId(1L);
		book.setPrice(50.00);
		Book secondBook = new Book();
		secondBook.setBookId(2L);
		secondBook.setPrice(50.00);
		
		author.addBook(book);
		author.addBook(secondBook);
		when(services.findById(1L)).thenReturn(Optional.of(author));
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String responseJson = ow.writeValueAsString(author.getBooks());
		
		mockMvc.perform(get("/author/books/1"))
		.andExpect(status().isOk())
		.andExpect(content().json(responseJson));
	}
	
	@Test
	void test_GetBooks_ReturnsCorrectStatusAndResponse_WhenEntityNotPresent() throws Exception {
		mockMvc.perform(get("/author/books/1"))
		.andExpect(content().string("Author id not present"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void test_UpdateFirstName_ReturnsCorrectStatusAndResponse_WhenEntityPresent() throws Exception {
		Author author = new Author();
		author.setAuthorId(1L);
		author.setFirstName("test");
		when(services.findById(1L)).thenReturn(Optional.of(author));
		
		mockMvc.perform(patch("/author/changefirstname/1").contentType(MediaType.APPLICATION_JSON_VALUE).content("new"))
		.andExpect(content().json("{'authorId' : 1, 'firstName' : 'new'}"))
		.andExpect(status().isOk());
	}
	
	@Test
	void test_UpdateFirstName_ReturnsCorrectStatusAndResponse_WhenEntityNotPresent() throws Exception {
		mockMvc.perform(patch("/author/changefirstname/1").contentType(MediaType.APPLICATION_JSON_VALUE).content("new"))
		.andExpect(content().string("Author id not present"))
		.andExpect(status().isNotFound());
	}

}
