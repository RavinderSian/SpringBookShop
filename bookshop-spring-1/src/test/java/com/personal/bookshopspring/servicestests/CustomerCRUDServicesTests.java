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
