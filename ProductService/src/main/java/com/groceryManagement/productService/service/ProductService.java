package com.groceryManagement.productService.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.groceryManagement.productService.model.Product;
import com.groceryManagement.productService.requestBean.ProductFilterDTO;
import com.groceryManagement.productService.responseBean.SearchResponse;

public interface ProductService {

	ResponseEntity<List<Product>> fetchAllData();

	ResponseEntity<List<Product>> filterProduct(ProductFilterDTO product);

	List<SearchResponse> searchItems(String searchVariable);

	Product findProduct(String productName);

	String save(Product product);

	String deleteProduct(String productId);

}
