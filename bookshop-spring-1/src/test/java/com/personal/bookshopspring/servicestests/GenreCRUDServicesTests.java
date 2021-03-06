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

import com.personal.bookshopspring.models.Genre;
import com.personal.bookshopspring.repositories.GenreRepository;
import com.personal.bookshopspring.services.GenreCRUDServiceImp;
import com.personal.bookshopspring.services.GenreCRUDServices;

@SpringBootTest
class GenreCRUDServicesTests {

	@Mock
	Genre mockGenre;
	
	@Mock
	GenreRepository mockRepository;
	
	private GenreCRUDServices services;
	
	@BeforeEach
	void setUp() {
		services = new GenreCRUDServiceImp(mockRepository);
	}
	
	@Test
	void test_Save_CallsRepositorySave_WhenCalled() {
		//Act
		services.save(mockGenre);
		//Assert
		verify(mockRepository, times(1)).save(mockGenre);
		verifyNoMoreInteractions(mockRepository);
	}
	
	@Test
	void test_Save_ReturnsCorrectGenre_WhenGivenGenre() {
		//Arrange
		when(services.save(mockGenre)).thenReturn(mockGenre);
		//Act
		Genre genre = services.save(mockGenre);
		//Assert
		Assertions.assertEquals(mockGenre, genre);
	}
	
	@Test
	void test_FindAll_CallsRepositoryFindAll_WhenCalled(){
		//Act
		services.findAll();
		//Assert
		verify(mockRepository, times(1)).findAll();
		verifyNoMoreInteractions(mockRepository);
	}
	
	@Test
	void test_FindAll_ReturnsListOfSize1_WhenCalled(){
		//Arrange
		when(services.findAll()).thenReturn(new ArrayList<Genre>(Arrays.asList(mockGenre)));
		//Act
		List<Genre> genres = services.findAll();
		//Assert
		Assertions.assertEquals(1, genres.size());
	}
	
	@Test
	void test_FindAll_ReturnsListOfSize0_WhenCalled(){
		//Arrange
		when(services.findAll()).thenReturn(new ArrayList<>());
		//Act
		List<Genre> genres = services.findAll();
		//Assert
		Assertions.assertEquals(0, genres.size());
	}
	
	@Test
	void test_Delete_CallsRepositoryDelete_WhenCalled() {
		//Act
		services.delete(mockGenre);
		//Assert
		verify(mockRepository, times(1)).delete(mockGenre);
		verifyNoMoreInteractions(mockRepository);
	}
	
	@Test
	void test_FindById_CallsRepositoryFindById_WhenCalled() {
		//Act
		services.findById(1L);
		//Assert
		verify(mockRepository, times(1)).findById(1L);
		verifyNoMoreInteractions(mockRepository);
	}
	
	@Test
	void test_FindById_ReturnsCorrectGenre_WhenGivenId() {
		//Arrange
		when(services.findById(1L)).thenReturn(Optional.of(mockGenre));
		//Act
		Optional<Genre> genre = services.findById(1L);
		//Assert
		Assertions.assertEquals(Optional.of(mockGenre), genre);
	}
	
}
