package com.groceryManagement.cartService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
