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

import com.personal.bookshopspring.models.Sales;
import com.personal.bookshopspring.repositories.SalesRepository;
import com.personal.bookshopspring.services.SalesCRUDServices;
import com.personal.bookshopspring.services.SalesCRUDServicesImp;

@SpringBootTest
class SalesCRUDServicesTests {
	
	@Mock
	private Sales mockSale;

	@Mock
	private SalesRepository mockRepository;
	
	private SalesCRUDServices services;

	@BeforeEach
	void setUp() {
		this.services = new SalesCRUDServicesImp(mockRepository);
	}
	
	@Test
	void test_SaveCallsRepositorySave_WhenCalled() {
		// Act
		services.save(mockSale);
		// Assert
		verify(mockRepository, times(1)).save(mockSale);
		verifyNoMoreInteractions(mockRepository);

	}

	@Test
	void test_SaveReturnsCorrectSale_WhenGivenSale() {
		// Act
		Sales sale = services.save(mockSale);
		// Assert
		Assertions.assertEquals(mockSale, sale);
	}

	@Test
	void test_DeleteCallsRepositoryDelete_WhenCalled() {
		// Act
		services.delete(mockSale);
		// Assert
		verify(mockRepository, times(1)).delete(mockSale);
		verifyNoMoreInteractions(mockRepository);

	}

	@Test
	void test_FindAllCallsRepositoryFindAll_WhenCalled() {
		// Act
		services.findAll();
		// Assert
		verify(mockRepository, times(1)).findAll();
		verifyNoMoreInteractions(mockRepository);
	}

	@Test
	void test_FindAllReturnsListOfSize1_WhenGivenSale() {
		// Arrange
		when(services.findAll()).thenReturn(new ArrayList<Sales>(Arrays.asList(mockSale)));
		// Act
		List<Sales> sales = services.findAll();
		// Assert
		Assertions.assertEquals(1, sales.size());
	}

	@Test
	void test_FindAllReturnsListOfSize0_WhenGivenNothing() {
		// Arrange
		when(services.findAll()).thenReturn(new ArrayList<Sales>());
		// Act
		List<Sales> sales = services.findAll();
		// Assert
		Assertions.assertEquals(0, sales.size());
	}

	@Test
	void test_FindByIdCallsRepositoryFindById_WhenCalled() {
		// Act
		services.findById(1L); // 1L is of type Long, while 1 is int
		// Assert
		verify(mockRepository, times(1)).findById(1L);
		verifyNoMoreInteractions(mockRepository);
	}

	@Test
	void test_FindByIdReturnsCorrectSale_WhenGivenId() {
		// Arrange
		when(services.findById(1L)).thenReturn(Optional.of(mockSale));
		// Mockito.doReturn(Optional.of(mockBook)).when(services).findById(1L);
		// Act
		Optional<Sales> sale = services.findById(1L); // 1L is of type Long, while 1 is int
		// Assert
		Assertions.assertEquals(Optional.of(mockSale), sale);
	}

}
