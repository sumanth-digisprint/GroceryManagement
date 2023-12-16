package com.groceryManagement.adminService.model;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "feedback")
@Getter
@Setter
public class FeedBack {

	private String id;
	private String userEmail;
	private String feedBack;
	private String productName;

}
