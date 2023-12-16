
package com.groceryManagement.productService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.groceryManagement.productService.model.Product;
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	
	Product findByProductName(String productName);

	@Query(value ="{$or:[{'productType': {$regex : ?0, $options: 'i'}},{'productName': {$regex : ?0, $options: 'i'}}]}")
	List<Product> findByProductType(String searchVariable);

}
