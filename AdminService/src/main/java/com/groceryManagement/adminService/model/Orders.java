package com.groceryManagement.adminService.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document(collection ="Orders")
public class Orders {

	@Id
	private String orderId;
	private	String userEmail;
	private	String shopName;
	private	String productName;
	private	float orderTotal;
	private	float orderTotalWithDiscount;
	private	String orderStatus;
	private String quantity;
	private	String orderpaymentType;
	private	String orderpaymentStatus;
	private	LocalDateTime orderDate;
	private	String typeOfDelivery;
	private	LocalDateTime scheduledDeliveryTime;
	private	String deliveredAddress;

}
