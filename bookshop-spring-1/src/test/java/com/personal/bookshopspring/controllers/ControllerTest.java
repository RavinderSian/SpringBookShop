package com.personal.bookshopspring.controllers;

public interface ControllerTest {
	
	void setUp();

	void test_Controller_IsNotNull();
	
	void test_GetEntity_ReturnsCorrectStatusAndResponse_WhenEntityPresent() throws Exception;
	
	void test_GetEntity_ReturnsCorrectStatusAndResponse_WhenEntityNotPresent() throws Exception;
	
	void test_Delete_ReturnsCorrectStatusAndResponse_WhenEntityPresent() throws Exception;
	
	void test_Delete_ReturnsCorrectStatusAndResponse_WhenEntityNotPresent() throws Exception;
	
	void test_Add_ReturnsCorrectStatusAndResponse_WhenGivenValidEntity() throws Exception;
	
	void test_GetAll_ReturnsCorrectStatusAndResponse_WhenCalled() throws Exception;
	
}
