package com.groceryManagement.adminService.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groceryManagement.adminService.requestBean.AdminLoginDTO;
import com.groceryManagement.adminService.requestBean.ProductRequestBean;
import com.groceryManagement.adminService.requestBean.ProductUpdateBean;
import com.groceryManagement.adminService.requestBean.productNameRequired;
import com.groceryManagement.adminService.restClient.UserClient;
import com.groceryManagement.adminService.service.AdminService;

@WebMvcTest(controllers = AdminController.class)
public class TestAdminController {
	
	@Autowired
	MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@MockBean
	AdminService adminService;

	@MockBean
	UserClient userClient;

	@Test
	public void userLogin() throws Exception {
		objectMapper = new ObjectMapper();
		AdminLoginDTO adminLoginDTO = new AdminLoginDTO();
		mockMvc.perform(post("/adminLogin").header("email", "abuhurera600@gmail.com").header("password", "Abu@123")
				.content(objectMapper.writeValueAsString(adminLoginDTO)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	public void getAllOrders() throws Exception {
		mockMvc.perform(get("/findAllOrderedsByAdmin").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	public void getAllUsers() throws Exception {
		mockMvc.perform(get("/findAllUsersByAdmin").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getSuggestion() throws Exception {
		mockMvc.perform(get("/Get Suggestion").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getAll() throws Exception {
		objectMapper = new ObjectMapper();
		mockMvc.perform(get("/SeeAllFeedBacks").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void getAllItemsTest() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		ProductRequestBean product=new ProductRequestBean();
		mockMvc.perform(get("/findAllProductsByAdmin") .header("ProductName", "Apple")
				.content(objectMapper.writeValueAsString(product)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void addDataTest() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		ProductRequestBean product=new ProductRequestBean();
		mockMvc.perform(post("/productAdding")
				.content(objectMapper.writeValueAsString(product)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	public void deleteDataTest() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		productNameRequired product=new productNameRequired();
		mockMvc.perform(post("/productDeleting")
				.content(objectMapper.writeValueAsString(product)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void updateDataTest() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		ProductUpdateBean product=new ProductUpdateBean();
		mockMvc.perform(put("/updateProductData")
				.content(objectMapper.writeValueAsString(product)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
}
