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

import com.personal.bookshopspring.models.Customer;
import com.personal.bookshopspring.repositories.CustomerRepository;
import com.personal.bookshopspring.services.CustomerCRUDServices;
import com.personal.bookshopspring.services.CustomerCRUDServicesImp;

@SpringBootTest
class CustomerCRUDServicesTests {

	@Mock
	private Customer mockCustomer;

	@Mock
	private CustomerRepository mockRepository;

	private CustomerCRUDServices services;
	
	@BeforeEach
	void setUp() {
		services = new CustomerCRUDServicesImp(mockRepository);
	}
	
	@Test
	void test_SaveCallsRepositorySave_WhenCalled() {
		// Act
		services.save(mockCustomer);
		// Assert
		verify(mockRepository, times(1)).save(mockCustomer);
		verifyNoMoreInteractions(mockRepository);
	}

	@Test
	void test_SaveReturnsCorrectCustomer_WhenGivenCustomer() {
		// Act
		Customer customer = services.save(mockCustomer);
		// Assert
		Assertions.assertEquals(mockCustomer, customer);
	}

	@Test
	void test_DeleteCallsRepositoryDelete_WhenCalled() {
		// Act
		services.delete(mockCustomer);
		// Assert
		verify(mockRepository, times(1)).delete(mockCustomer);
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
	void test_FindAllReturnsListOfSize1_WhenGivenCustomer() {
		// Arrange
		when(services.findAll()).thenReturn(new ArrayList<Customer>(Arrays.asList(mockCustomer)));
		// Act
		List<Customer> customers = services.findAll();
		// Assert
		Assertions.assertEquals(1, customers.size());
	}

	@Test
	void test_FindAllReturnsListOfSize0_WhenGivenNothing() {
		// Arrange
		when(services.findAll()).thenReturn(new ArrayList<Customer>());
		// Act
		List<Customer> customers = services.findAll();
		// Assert
		Assertions.assertEquals(0, customers.size());
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
	void test_FindByIdReturnsCorrectCustomer_WhenGivenId() {
		// Arrange
		when(services.findById(1L)).thenReturn(Optional.of(mockCustomer));
		// Act
		Optional<Customer> customer = services.findById(1L);
		// Assert
		Assertions.assertEquals(Optional.of(mockCustomer), customer);
	}

}
