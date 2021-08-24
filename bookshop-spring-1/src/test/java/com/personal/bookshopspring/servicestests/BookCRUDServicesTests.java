package com.personal.bookshopspring.servicestests;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

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
