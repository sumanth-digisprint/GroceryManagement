package com.groceryManagement.adminService.requestBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestBean {

	private String shopName;
	private String productType;
	private String productName;
	private float productPricePerQuantity;
	private int productDiscount;
	private String quantity;
	private int rating;
	private float stock;

}
