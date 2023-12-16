package com.groceryManagement.adminService.requestBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateBean {

	private String productName;
	private float productPricePerQuantity;
	private int productDiscount;
	private String quantity;
	private float stock;
}
