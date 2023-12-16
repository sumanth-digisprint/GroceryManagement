package com.groceryManagement.adminService.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Document(collection = "admin")
@Getter
public class Admin {

	private String id;
	private String name;
	private String email;
	private String password;
	private String phoneNumber;

}
