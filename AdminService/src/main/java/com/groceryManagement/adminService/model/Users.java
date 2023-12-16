package com.groceryManagement.adminService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Document(collection = "User")
@Getter
public class Users {
	@Id
	private String userId;
	private String userName;
	private String userEmail;
	private String userReferralEmail;
	private String phoneNumber;
	private float walletAmount;
	private String password;
	private String address;
	private int points;
}
