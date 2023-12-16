package com.groceryManagement.cartService.requestBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {
	
  private String productName;
  private String userEmail;
  private float quantity;
  
}
