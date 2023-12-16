package com.groceryManagement.cartService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection ="cart")
public class Cart {

	@Id
	private String cartId;
	private	String userEmail;
	private	String productId;
	private	String productName;
	private	float productOriginalPrice;
	private	float productPriceWithDiscount;
	private	int discountPrice;
	private float productQuantity;

}
