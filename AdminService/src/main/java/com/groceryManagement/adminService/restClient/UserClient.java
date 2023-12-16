package com.groceryManagement.adminService.restClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.groceryManagement.adminService.model.Users;

@FeignClient("USER-SERVICE")
public interface UserClient {
	
	@GetMapping("/findAllUsers")
	public List<Users> findAll();

}
