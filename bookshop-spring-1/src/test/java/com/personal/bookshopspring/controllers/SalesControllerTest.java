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
import com.personal.bookshopspring.models.Sales;
import com.personal.bookshopspring.services.SalesCRUDServices;

@WebMvcTest(SalesController.class)
class SalesControllerTest implements ControllerTest{

	@Autowired
	private MockMvc mockMvc;
	
	private SalesController controller;
	
	@MockBean
	private SalesCRUDServices services;
	
	@BeforeEach
	public void setUp() {
		controller = new SalesController(services);
	}

	@Override
	@Test
	public void test_Controller_IsNotNull() {
		assertThat(controller, not(equalTo(null)));
	}

	@Override
	@Test
	public void test_GetEntity_ReturnsCorrectStatusAndResponse_WhenEntityPresent() throws Exception {
		Sales sale = new Sales();
		sale.setSaleId(1L);
		sale.setPricePaid(50.00);
		when(services.findById(1L)).thenReturn(Optional.of(sale));

		mockMvc.perform(get("/sale/1"))
		.andExpect(content().json("{'saleId' : 1, 'pricePaid' : 50.00}"))
		.andExpect(status().isOk());
	}

	@Override
	@Test
	public void test_GetEntity_ReturnsCorrectStatusAndResponse_WhenEntityNotPresent() throws Exception {
		mockMvc.perform(get("/sale/1"))
		.andExpect(content().string("Sale id not present"))
		.andExpect(status().isNotFound());
	}

	@Override
	@Test
	public void test_Delete_ReturnsCorrectStatusAndResponse_WhenEntityPresent() throws Exception {
		Sales sale = new Sales();
		sale.setSaleId(1L);
		sale.setPricePaid(50.00);
		when(services.findById(1L)).thenReturn(Optional.of(sale));
		
		mockMvc.perform(delete("/sale/delete/1"))
		.andExpect(content().string("Deleted sale of id 1"))
		.andExpect(status().isOk());
	}

	@Override
	@Test
	public void test_Delete_ReturnsCorrectStatusAndResponse_WhenEntityNotPresent() throws Exception {
		mockMvc.perform(delete("/sale/delete/1"))
		.andExpect(content().string("Sale id not present"))
		.andExpect(status().isNotFound());
	}

	@Override
	@Test
	public void test_Add_ReturnsCorrectStatusAndResponse_WhenGivenValidEntity() throws Exception {
		Sales sale = new Sales();
		sale.setSaleId(1L);
		sale.setPricePaid(50.00);
		
		when(services.save(sale)).thenReturn(sale);

	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(sale);
		
		mockMvc.perform(put("/sale/add").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
		.andExpect(status().isOk())
		.andExpect(content().json("{'saleId' : 1, 'pricePaid' : 50.00}"));
	}

	@Override
	@Test
	public void test_GetAll_ReturnsCorrectStatusAndResponse_WhenCalled() throws Exception {
		Sales sale = new Sales();
		sale.setSaleId(1L);
		sale.setPricePaid(50.00);
		
		Sales secondSale = new Sales();
		secondSale.setSaleId(2L);
		secondSale.setPricePaid(50.00);
		
		List<Sales> sales = new ArrayList<>();
		sales.add(sale);
		sales.add(secondSale);
		
		when(services.findAll()).thenReturn(sales);
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String responseJson = ow.writeValueAsString(sales);
		
		mockMvc.perform(get("/sale/all"))
		.andExpect(status().isOk())
		.andExpect(content().json(responseJson));
	}
	
	@Test
	void test_UpdatePrice_ReturnsCorrectStatusAndResponse_WhenEntityPresent() throws Exception {
		
		Sales sale = new Sales();
		sale.setSaleId(1L);
		sale.setPricePaid(10.00);
		when(services.findById(1L)).thenReturn(Optional.of(sale));
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(50);
		
		mockMvc.perform(patch("/sale/newprice/1").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
		.andExpect(status().isOk())
		.andExpect(content().json("{'saleId' : 1, 'pricePaid' : 50.00}"));
	}
	
	@Test
	void test_UpdatePrice_ReturnsCorrectStatusAndResponse_WhenEntityNotPresent() throws Exception {
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(50.00);
		
		mockMvc.perform(patch("/sale/newprice/1").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
		.andExpect(status().isNotFound())
		.andExpect(content().string("Sale id not present"));
	}

}
