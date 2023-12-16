package com.groceryManagement.productService.ServiceImplementation;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.groceryManagement.productService.requestBean.ProductFilterDTO;
import com.groceryManagement.productService.model.Product;
import com.groceryManagement.productService.repository.ProductRepository;
import com.groceryManagement.productService.serviceImplimentation.ProductServiceImplementation;

@ActiveProfiles("junit")
@ExtendWith(MockitoExtension.class)
public class ProductServiceImplementationTest {

	@InjectMocks
	ProductServiceImplementation productServiceImplementation;

	@Mock
	ProductRepository productRepository;
	
	@MockBean
	Product product;

	ArrayList<Product> list;
	@MockBean
	ProductFilterDTO filterDTO;
	
	@BeforeEach
	public void setUp() {
		
		product = new Product();
		product.setProductId("1");
		product.setProductName("Apple");
		filterDTO = new ProductFilterDTO();

		list = new ArrayList<>();
		list.add(product);
	}
	
	@Test
	public void fetchAllDataTest() {
		when(productRepository.findAll()).thenReturn(list);
		assertEquals(1, productServiceImplementation.fetchAllData().getBody().size());
	}
	@Test
	public void searchItemsTest() {
		List<Product> productList = new ArrayList<>();
		Product products = new Product();
		products.setProductName("Apple");
		products.setProductType("Fruit");
		products.setShopName("Wear House");
		products.setProductPricePerQuantity(30);
		products.setQuantity("1kg");
		products.setProductDiscount(3);
		products.setStock(30);
		productList.add(products);
		when(productRepository.findByProductType("Fruit")).thenReturn(productList);
		assertEquals(1, productServiceImplementation.searchItems("Fruit").size());
	}
	@Test
	public void filterProductByVegetablesTest() {
		product.setProductType("Vegetables");	
		filterDTO.setFilterType("Vegetables");
		when(productRepository.findAll()).thenReturn(list);
		assertEquals(list, productServiceImplementation.filterProduct(filterDTO).getBody());
	}

	@Test
	public void filterProductByFruitsTest() {
		product.setProductType("Fruits");
		filterDTO.setFilterType("Fruits");
		when(productRepository.findAll()).thenReturn(list);
		assertEquals(list, productServiceImplementation.filterProduct(filterDTO).getBody());
	}

	@Test
	public void filterProductByLeafTest() {
		product.setProductType("leafy green");
		filterDTO.setFilterType("leafy green");
		when(productRepository.findAll()).thenReturn(list);
		assertEquals(list, productServiceImplementation.filterProduct(filterDTO).getBody());
	}

	@Test
	public void filterProductByRootTest() {
		product.setProductType("Root Vegetables");
		filterDTO.setFilterType("Root");
		when(productRepository.findAll()).thenReturn(list);
		assertEquals(list, productServiceImplementation.filterProduct(filterDTO).getBody());
	}

	@Test
	public void filterProductByMarrowTest() {
		product.setProductType("Marrow Vegetables");
		filterDTO.setFilterType("Marrow");
		when(productRepository.findAll()).thenReturn(list);
		assertEquals(list, productServiceImplementation.filterProduct(filterDTO).getBody());
	}

	@Test
	public void filterProductByPlantaeTest() {
		product.setProductType("Plantae Vegetables");
		filterDTO.setFilterType("Plantae");
		when(productRepository.findAll()).thenReturn(list);
		assertEquals(list, productServiceImplementation.filterProduct(filterDTO).getBody());
	}

	@Test
	public void filterProductNegativeTest() {
		product.setProductType("Plantae");
		filterDTO.setFilterType("abc");
		when(productRepository.findAll()).thenReturn(list);
		assertEquals("ProductType is not Exist. Please search in different type..",
				productServiceImplementation.filterProduct(filterDTO).getBody());
	}
	@Test
	public void addDataTest() {
		assertEquals("Data saved..", productServiceImplementation.save(product));
	}

	@Test
	public void deleteDataTest() {
		assertEquals("Data deleted..", productServiceImplementation.deleteProduct("1"));
	}

	@Test
	public void findProductTest() {
		when(productRepository.findByProductName("Apple")).thenReturn(product);
		assertEquals(product, productServiceImplementation.findProduct("Apple"));
	}


	
}
