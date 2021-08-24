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
import com.personal.bookshopspring.models.Customer;
import com.personal.bookshopspring.services.CustomerCRUDServices;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest implements ControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private CustomerController controller;
	
	@MockBean
	private CustomerCRUDServices services;
	
	@Override
	@BeforeEach
	public void setUp() {
		controller = new CustomerController(services);
	}

	@Test
	@Override
	public void test_Controller_IsNotNull() {
		assertThat(controller, not(equalTo(null)));
	}
	
	@Test
	@Override
	public void test_GetEntity_ReturnsCorrectStatusAndResponse_WhenEntityPresent() throws Exception {

		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setFirstName("test");
		customer.setLastName("testing");
		when(services.findById(1L)).thenReturn(Optional.of(customer));

		mockMvc.perform(get("/customer/1"))
		.andExpect(content().json("{'customerId' : 1, 'firstName' : 'test', 'lastName' : 'testing'}"))
		.andExpect(status().isOk());
	}

	@Test
	@Override
	public void test_GetEntity_ReturnsCorrectStatusAndResponse_WhenEntityNotPresent() throws Exception {
		mockMvc.perform(get("/customer/1"))
		.andExpect(status().isNotFound());
	}

	@Test
	@Override
	public void test_Delete_ReturnsCorrectStatusAndResponse_WhenEntityPresent() throws Exception {

		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setFirstName("test");
		customer.setLastName("testing");
		when(services.findById(1L)).thenReturn(Optional.of(customer));
		
		mockMvc.perform(delete("/customer/delete/1"))
		.andExpect(status().isOk());
	}

	@Test
	@Override
	public void test_Delete_ReturnsCorrectStatusAndResponse_WhenEntityNotPresent() throws Exception {
		mockMvc.perform(delete("/customer/delete/1"))
		.andExpect(status().isNotFound());
	}

	@Test
	@Override
	public void test_Add_ReturnsCorrectStatusAndResponse_WhenGivenValidEntity() throws Exception {

		Customer customer = new Customer();
		customer.setFirstName("test");
		customer.setCustomerId(1L);
		
		when(services.save(customer)).thenReturn(customer);

	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(customer);
		
		mockMvc.perform(put("/customer/add").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
		.andExpect(status().isOk())
		.andExpect(content().json("{'customerId' : 1, 'firstName' : 'test'}"));
		
	}

	@Test
	@Override
	public void test_GetAll_ReturnsCorrectStatusAndResponse_WhenCalled() throws Exception {
		
		Customer customer = new Customer();
		customer.setFirstName("test");
		customer.setCustomerId(1L);
		
		Customer secondCustomer = new Customer();
		secondCustomer.setFirstName("test");
		secondCustomer.setCustomerId(2L);
		
		List<Customer> customers = new ArrayList<>();
		customers.add(customer);
		customers.add(secondCustomer);
		
		when(services.findAll()).thenReturn(customers);
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String responseJson = ow.writeValueAsString(customers);
		
		mockMvc.perform(get("/customer/all"))
		.andExpect(status().isOk())
		.andExpect(content().json(responseJson));
	}

	@Test
	void test_UpdateFirstName_ReturnsCorrectStatusAndResponse_WhenEntityPresentAndRequestValid() throws Exception {
		Customer customer = new Customer();
		customer.setFirstName("test");
		customer.setCustomerId(1L);
		
		when(services.findById(1L)).thenReturn(Optional.of(customer));
		
		mockMvc.perform(patch("/customer/namechange/1").contentType(MediaType.APPLICATION_JSON_VALUE).content("new"))
		.andExpect(content().json("{'customerId' : 1, 'firstName' : 'new'}"))
		.andExpect(status().isOk());
	}

	@Test
	void test_UpdateFirstName_ReturnsCorrectStatusAndResponse_WhenEntityNotPresentAndRequestValid() throws Exception {
		mockMvc.perform(patch("/customer/namechange/10").contentType(MediaType.APPLICATION_JSON_VALUE).content("new"))
		.andExpect(status().isNotFound());
	}

}
