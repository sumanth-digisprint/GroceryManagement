package com.groceryManagement.productService.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.groceryManagement.productService.model.Product;
import com.groceryManagement.productService.requestBean.ProductFilterDTO;
import com.groceryManagement.productService.requestBean.productNameRequired;
import com.groceryManagement.productService.responseBean.SearchResponse;
import com.groceryManagement.productService.service.ProductService;

@RestController
public class ProductController {

	ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/findAllProducts")
	public ResponseEntity<List<Product>> getAllData() {
		return productService.fetchAllData();
	}

	@GetMapping("/Search")
	public List<SearchResponse> search(@RequestParam String searchVariable) {
		return productService.searchItems(searchVariable);
	}

	@PostMapping("/filterProducts")
	public ResponseEntity<List<Product>> filter(@RequestBody ProductFilterDTO product) {
		return productService.filterProduct(product);
	}

	@GetMapping("FindingItemToAddToCart")
	public Product findProduct(@RequestParam String productName) {
		return productService.findProduct(productName);
	}

	@PostMapping("/saveProduct")
	public String save(@RequestBody Product product) {
		return productService.save(product);
	}

	@PostMapping("/DeletingProduct")
	public String deleteById(@RequestBody String productId) {
		return productService.deleteProduct(productId);
	}

}
