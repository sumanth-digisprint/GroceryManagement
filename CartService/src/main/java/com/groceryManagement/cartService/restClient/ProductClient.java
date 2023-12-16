package com.groceryManagement.cartService.restClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.groceryManagement.cartService.model.Product;

@FeignClient("PRODUCT-SERVICE")
public interface ProductClient {
	
	@GetMapping("FindingItemToAddToCart")
	public Product findProduct(@RequestParam String productName);

}
