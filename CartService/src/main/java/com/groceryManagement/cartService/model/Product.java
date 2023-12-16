package com.groceryManagement.cartService.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "Product")
public class Product {

	@Id
	private String productId;
	private String shopName;
	private String productType;
	private String productName;
	private float productPricePerQuantity;
	private int productDiscount;
	private String quantity;
	private LocalDateTime productAddDate;
	private int rating;
	private float stock;
}
