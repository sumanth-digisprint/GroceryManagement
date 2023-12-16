package com.groceryManagement.cartService.restClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.groceryManagement.cartService.model.Users;

@FeignClient("USER-SERVICE")
public interface UserClient {
	
	@GetMapping("/FindUser")
	public Users findUser(@RequestParam String userMail);

}
