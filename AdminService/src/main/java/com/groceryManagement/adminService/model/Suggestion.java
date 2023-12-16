package com.groceryManagement.adminService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "Suggestion")
public class Suggestion {

	@JsonIgnore
	@Id
	private String Id;
	private String userEmail;
	private String suggestion;
	

}
