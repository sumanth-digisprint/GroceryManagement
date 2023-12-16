package com.groceryManagement.productService.responseBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResponse {

	private String productType;
	private String productName;
	private String quantity;
	private float productPricePerQuantity;
	private int productDiscount;

}
