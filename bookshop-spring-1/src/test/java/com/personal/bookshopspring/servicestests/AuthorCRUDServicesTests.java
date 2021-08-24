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

import com.personal.bookshopspring.models.Author;
import com.personal.bookshopspring.repositories.AuthorRepository;
import com.personal.bookshopspring.services.AuthorCRUDServices;
import com.personal.bookshopspring.services.AuthorCRUDServicesImp;

@SpringBootTest
class AuthorCRUDServicesTests {

	@Mock
	private Author mockAuthor;
	
	@Mock
	private AuthorRepository mockRepository;
	
	private AuthorCRUDServices services;;

	@BeforeEach
	void setUp() throws Exception {
		services = new AuthorCRUDServicesImp(mockRepository);
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
	void test_FindByIdReturnsCorrectAuthor_WhenGivenId() {
		//Arrange
		when(services.findById(1L)).thenReturn(Optional.of(mockAuthor));
		//Mockito.doReturn(Optional.of(mockBook)).when(services).findById(1L);
		//Act
		Optional<Author> author = services.findById(1L); //1L is of type Long, while 1 is int
		//Assert
		Assertions.assertEquals(Optional.of(mockAuthor), author);
	}
	
}
