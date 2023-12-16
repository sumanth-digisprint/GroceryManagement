package com.groceryManagement.productService.serviceImplimentation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.groceryManagement.productService.model.Product;
import com.groceryManagement.productService.repository.ProductRepository;
import com.groceryManagement.productService.requestBean.ProductFilterDTO;
import com.groceryManagement.productService.responseBean.SearchResponse;
import com.groceryManagement.productService.service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService {

	ProductRepository productRepository;

	public ProductServiceImplementation(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<List<Product>> fetchAllData() {
		return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public List<SearchResponse> searchItems(String searchVariable) {
		List<SearchResponse> searchList=new ArrayList<>();
		List<Product> products=new ArrayList<>();
		productRepository.findByProductType(searchVariable).forEach(items->products.add(items));
		for (Product product : products) {
			SearchResponse response=new SearchResponse();
			BeanUtils.copyProperties(product, response);
			searchList.add(response);
		}
		return searchList;

	}

	@Override
	public ResponseEntity<List<Product>> filterProduct(ProductFilterDTO product) {
		String type = product.getFilterType();
		List<Product> filterProduct;
		List<Product> sort = productRepository.findAll();

		switch (type) {
		case "Vegetables":

			filterProduct = sort.stream().filter(i -> i.getProductType().contains("Vegetables"))
			.collect(Collectors.toList());
			break;
		case "Fruits":

			filterProduct = sort.stream().filter(i -> i.getProductType().equalsIgnoreCase("Fruits"))
			.collect(Collectors.toList());
			break;
		case "leafy green":
			filterProduct = sort.stream().filter(i -> i.getProductType().equalsIgnoreCase("leafy green"))
			.collect(Collectors.toList());
			break;
		case "Root":
			filterProduct = sort.stream().filter(i -> i.getProductType().equalsIgnoreCase("Root Vegetables"))
			.collect(Collectors.toList());
			break;
		case "Marrow":
			filterProduct = sort.stream().filter(i -> i.getProductType().equalsIgnoreCase("Marrow Vegetables"))
			.collect(Collectors.toList());
			break;
		case "Plantae":
			filterProduct = sort.stream().filter(i -> i.getProductType().equalsIgnoreCase("Plantae Vegetables"))
			.collect(Collectors.toList());
			break;
		default:
			return new ResponseEntity("ProductType is not Exist. Please search in different type..", HttpStatus.OK);
		}
		return new ResponseEntity<>(filterProduct, HttpStatus.OK);
	}

	@Override
	public Product findProduct(String productName) {
		return productRepository.findByProductName(productName);
	}

	@Override
	public String save(Product product) {
		productRepository.save(product);
		return"Data saved..";
	}

	@Override
	public String deleteProduct(String productId) {
		productRepository.deleteById(productId);
		return "Data deleted..";
	}

}
