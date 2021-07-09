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
	void test_SaveCallsRepositorySave_WhenCalled() {
		//Act
		services.save(mockAuthor);
		//Assert
		verify(mockRepository, times(1)).save(mockAuthor);
		verifyNoMoreInteractions(mockRepository);

	}
	
	@Test
	void test_SaveReturnsCorrectAuthor_WhenGivenAuthor() {
		//Act
		Author author = services.save(mockAuthor);
		//Assert
		Assertions.assertEquals(mockAuthor, author);
	}
	
	
	@Test
	void test_DeleteCallsRepositoryDelete_WhenCalled() {
		//Act
		services.delete(mockAuthor);
		//Assert
		verify(mockRepository, times(1)).delete(mockAuthor);
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
	void test_FindAllReturnsListOfSize1_WhenGivenAuthor() {
		//Arrange
		when(services.findAll()).thenReturn(new ArrayList<Author>(Arrays.asList(mockAuthor)));
		//Act
		List<Author> authors = services.findAll();
		//Assert
		Assertions.assertEquals(1, authors.size());
	}
	
	@Test
	void test_FindAllReturnsListOfSize0_WhenGivenNothing() {
		//Arrange
		when(services.findAll()).thenReturn(new ArrayList<Author>());
		//Act
		List<Author> authors = services.findAll();
		//Assert
		Assertions.assertEquals(0, authors.size());
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
