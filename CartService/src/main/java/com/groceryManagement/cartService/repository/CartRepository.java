package com.groceryManagement.cartService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.groceryManagement.cartService.model.Cart;
import com.groceryManagement.cartService.requestBean.CartDto;

public interface CartRepository extends MongoRepository<Cart, Integer>{

	Cart save(CartDto cartRequest);

	//List<Orders> findByUserEmail(String userEmail);

	List<Cart> findAllByUserEmail(String userEmail);

	void deleteAllByUserEmail(String userEmail);

	Cart findByCartId(String cartId);
}
