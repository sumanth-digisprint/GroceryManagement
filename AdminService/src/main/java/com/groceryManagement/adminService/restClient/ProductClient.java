package com.groceryManagement.adminService.restClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.groceryManagement.adminService.model.Product;
import com.groceryManagement.adminService.requestBean.productNameRequired;

@FeignClient("PRODUCT-SERVICE")
public interface ProductClient {
	
	@GetMapping("FindingItemToAddToCart")
	public Product findProduct(@RequestParam String productName);
	
	@PostMapping("/saveProduct")
	public String save(@RequestBody Product product);
	
	@GetMapping("/findAllProducts")
	public List<Product> findAll();
	
	@PostMapping("/DeletingProduct")
	public void deleteById(@RequestBody String productId);
	
	@PostMapping("/findByProductId")
	public void findByProductId(@RequestParam String productId);

}
