package com.groceryManagement.productService.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import com.groceryManagement.productService.controller.ProductController;
import com.groceryManagement.productService.model.Product;
import com.groceryManagement.productService.requestBean.ProductFilterDTO;
import com.groceryManagement.productService.service.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {


	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;
	
	@Test
	public void getAllItemsTest() throws Exception {
		  
		  ObjectMapper objectMapper = new ObjectMapper(); 
		  mockMvc.perform(get("/findAllProducts") .header("ProductName", "Apple").contentType(MediaType.
		  APPLICATION_JSON)) .andExpect(status().isOk());
		}

	@Test
	public void addDataTest() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		Product product=new Product();
		mockMvc.perform(put("/saveProduct")
				.content(objectMapper.writeValueAsString(product)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void searchDataTest() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		mockMvc.perform(get("/Search").param("searchVariable", "apple").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void findDatatoCartTest() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		mockMvc.perform(get("/FindingItemToAddToCart").param("productName", "apple").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	public void filterDataTest() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		ProductFilterDTO product=new ProductFilterDTO();
		mockMvc.perform(post("/filterProducts")
				.content(objectMapper.writeValueAsString(product)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void deleteDataTest() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		ProductFilterDTO product=new ProductFilterDTO();
		mockMvc.perform(post("/DeletingProduct").param("productId", "1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
}
