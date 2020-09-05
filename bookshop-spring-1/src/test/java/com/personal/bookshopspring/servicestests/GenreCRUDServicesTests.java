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
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.personal.bookshopspring.models.Genre;
import com.personal.bookshopspring.repositories.GenreRepository;
import com.personal.bookshopspring.services.GenreCRUDServiceImp;
import com.personal.bookshopspring.services.GenreCRUDServices;

@SpringBootTest
public class GenreCRUDServicesTests {

	@Mock
	Genre mockGenre;
	
	@Mock
	GenreRepository mockRepository;
	
	@InjectMocks
	GenreCRUDServices genreServices = new GenreCRUDServiceImp();
	
	
	@Test
	public void test_Save_CallsRepositorySave_WhenCalled() {
		//Act
		genreServices.save(mockGenre);
		//Assert
		verify(mockRepository, times(1)).save(mockGenre);
		verifyNoMoreInteractions(mockRepository);
	}
	
	@Test
	public void test_Save_ReturnsCorrectGenre_WhenGivenGenre() {
		//Arrange
		when(genreServices.save(mockGenre)).thenReturn(mockGenre);
		//Act
		Genre genre = genreServices.save(mockGenre);
		//Assert
		Assertions.assertEquals(mockGenre, genre);
	}
	
	@Test
	public void test_FindAll_CallsRepositoryFindAll_WhenCalled(){
		//Act
		genreServices.findAll();
		//Assert
		verify(mockRepository, times(1)).findAll();
		verifyNoMoreInteractions(mockRepository);
	}
	
	@Test
	public void test_FindAll_ReturnsListOfSize1_WhenCalled(){
		//Arrange
		when(genreServices.findAll()).thenReturn(new ArrayList<Genre>(Arrays.asList(mockGenre)));
		//Act
		List<Genre> genres = genreServices.findAll();
		//Assert
		Assertions.assertEquals(1, genres.size());
	}
	
	@Test
	public void test_FindAll_ReturnsListOfSize0_WhenCalled(){
		//Arrange
		when(genreServices.findAll()).thenReturn(new ArrayList<>());
		//Act
		List<Genre> genres = genreServices.findAll();
		//Assert
		Assertions.assertEquals(0, genres.size());
	}
	
	@Test
	public void test_Delete_CallsRepositoryDelete_WhenCalled() {
		//Act
		genreServices.delete(mockGenre);
		//Assert
		verify(mockRepository, times(1)).delete(mockGenre);
		verifyNoMoreInteractions(mockRepository);
	}
	
	@Test
	public void test_FindById_CallsRepositoryFindById_WhenCalled() {
		//Act
		genreServices.findById(1L);
		//Assert
		verify(mockRepository, times(1)).findById(1L);
		verifyNoMoreInteractions(mockRepository);
	}
	
	@Test
	public void test_FindById_ReturnsCorrectGenre_WhenGivenId() {
		//Arrange
		when(genreServices.findById(1L)).thenReturn(Optional.of(mockGenre));
		//Act
		Optional<Genre> genre = genreServices.findById(1L);
		//Assert
		Assertions.assertEquals(Optional.of(mockGenre), genre);
	}
	
}
