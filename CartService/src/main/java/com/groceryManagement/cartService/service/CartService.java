package com.groceryManagement.cartService.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.groceryManagement.cartService.model.Cart;
import com.groceryManagement.cartService.requestBean.CartDeleteDto;
import com.groceryManagement.cartService.requestBean.CartDto;
import com.groceryManagement.cartService.requestBean.OrderDto;

public interface CartService {

	ResponseEntity<String> addProductToCart(CartDto cartDto);

	ResponseEntity<List<Cart>> getAllCartItem(OrderDto orderDto);

	ResponseEntity<Cart> deleteProduct(CartDeleteDto cartDeleteDto);

	List<Cart> findAllByUserEmail(String userMail);

	String deleteAllByUserEmail(String userMail);

}
