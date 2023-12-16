package com.groceryManagement.cartService.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.groceryManagement.cartService.requestBean.CartDeleteDto;
import com.groceryManagement.cartService.requestBean.CartDto;
import com.groceryManagement.cartService.requestBean.OrderDto;
import com.groceryManagement.cartService.serviceImplimentation.CartServiceImplementation;

@WebMvcTest(CartController.class)
public class CartControllerTest {

	@Autowired
	MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@MockBean
	CartServiceImplementation cartServiceImplementation;

	@Test
	public void addProductToCart() throws Exception {
		objectMapper=new ObjectMapper();
		CartDto cartDto= new CartDto();
		mockMvc.perform(post("/addProductToCart")
				.header("userEmail", "amaity@digisprint.com")
				.header("productName", "Apple")
				.header("quantity", 3)
				.content(objectMapper.writeValueAsString(cartDto))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

	}

	@Test
	public void getAllOrdersTest() throws Exception {
		objectMapper= new ObjectMapper();
		OrderDto orderDto=new OrderDto();
		mockMvc.perform(post("/getAllProductFromCart")
				.header("userEmail", "hanish@gmail.com")
				.content(objectMapper.writeValueAsString(orderDto)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

	}

	@Test
	public void deleteProduct() throws Exception {
		objectMapper= new ObjectMapper();
		CartDeleteDto cartDeleteDto=new CartDeleteDto();
		mockMvc.perform(delete("/deleteFromCart")
				.header("cartId", "23432")
				.content(objectMapper.writeValueAsString(cartDeleteDto)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

	}
	
	@Test
	public void findAllByUserEmailTest() throws Exception {
		objectMapper= new ObjectMapper();
		mockMvc.perform(get("/findAllByUserEmail").param("userMail", "12")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

	}
	

	@Test
	public void deleteAllByUserEmailTest() throws Exception {
		objectMapper= new ObjectMapper();
		mockMvc.perform(get("/deleteAllByUserEmail").param("userMail", "122")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

	}
	
	
}
