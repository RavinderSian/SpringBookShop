package com.personal.bookshopspring.servicestests;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.personal.bookshopspring.models.Book;
import com.personal.bookshopspring.repositories.BookRepository;
import com.personal.bookshopspring.services.BookCRUDServices;
import com.personal.bookshopspring.services.BookCRUDServicesImp;

@SpringBootTest
class BookCRUDServicesTests {
	
	@Mock
	private Book mockBook;

	@Mock
	private BookRepository mockRepository;
	
	private BookCRUDServices services;
	
	@BeforeEach
	void setUp() {
		this.services = new BookCRUDServicesImp(mockRepository);
	}
	
	@Test
	void test_SaveCallsRepositorySave_WhenCalled() {
		//Act
		services.save(mockBook);
		//Assert
		verify(mockRepository, times(1)).save(mockBook);
		verifyNoMoreInteractions(mockRepository);

	}
	
	@Test
	void test_SaveReturnsCorrectBook_WhenGivenBook() {
		//Act
		Book book = services.save(mockBook);
		//Assert
		Assertions.assertEquals(mockBook, book);
	}
	
	@Test
	void test_DeleteCallsRepositoryDelete_WhenCalled() {
		//Act
		services.delete(mockBook);
		//Assert
		verify(mockRepository, times(1)).delete(mockBook);
		verifyNoMoreInteractions(mockRepository);

	}
	
	@Test
	void test_FindAllCallsRepositoryFindAll_WhenCalled() {
		//Act
		services.findAll();
		//Assert
		verify(mockRepository, times(1)).findAll();
		verifyNoMoreInteractions(mockRepository);
	}
	
	@Test
	void test_FindAllReturnsListOfSize1_WhenGivenBook() {
		//Arrange
		when(services.findAll()).thenReturn(new ArrayList<Book>(Arrays.asList(mockBook)));
		//Act
		List<Book> books = services.findAll();
		//Assert
		Assertions.assertEquals(1, books.size());
	}
	
	@Test
	void test_FindAllReturnsListOfSize0_WhenGivenNothing() {
		//Arrange
		when(services.findAll()).thenReturn(new ArrayList<Book>());
		//Act
		List<Book> books = services.findAll();
		//Assert
		Assertions.assertEquals(0, books.size());
	}
	
	@Test
	void test_FindByIdCallsRepositoryFindById_WhenCalled() {
		//Act
		services.findById(1L); //1L is of type Long, while 1 is int
		//Assert
		verify(mockRepository, times(1)).findById(1L);
		verifyNoMoreInteractions(mockRepository);
	}
	
	
	@Test
	void test_FindByIdReturnsCorrectBook_WhenGivenId() {
		//Arrange
		when(services.findById(1L)).thenReturn(Optional.of(mockBook));
		//Act
		Optional<Book> book = services.findById(1L);
		//Assert
		Assertions.assertEquals(Optional.of(mockBook), book);
	}
	
}
