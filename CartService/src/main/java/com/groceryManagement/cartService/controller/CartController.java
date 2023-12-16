package com.groceryManagement.cartService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groceryManagement.cartService.model.Cart;
import com.groceryManagement.cartService.requestBean.CartDeleteDto;
import com.groceryManagement.cartService.requestBean.CartDto;
import com.groceryManagement.cartService.requestBean.OrderDto;
import com.groceryManagement.cartService.service.CartService;

@RestController
public class CartController {

	@Autowired
	CartService cartService;

	@PostMapping("/addProductToCart")
	public ResponseEntity<String> addProductToCart(@RequestBody CartDto cartDto) {
		return  cartService.addProductToCart(cartDto);
	}

	@PostMapping("/getAllProductFromCart")
	public ResponseEntity<List<Cart>> getAllOrders(@RequestBody OrderDto orderDto){
		return cartService.getAllCartItem(orderDto);
	}

	@DeleteMapping("/deleteFromCart")
	public ResponseEntity<Cart>deleteProduct(@RequestBody CartDeleteDto  cartDeleteDto){
		return	cartService.deleteProduct(cartDeleteDto);
	}
	
	@GetMapping("/findAllByUserEmail")
	public List<Cart> findAllByUserEmail(@RequestParam String userMail) {
		return cartService.findAllByUserEmail(userMail);
	}
	
	@GetMapping("/deleteAllByUserEmail")
	public String deleteAllByUserEmail(@RequestParam String userMail) {
	   return cartService.deleteAllByUserEmail(userMail);
	}

}
