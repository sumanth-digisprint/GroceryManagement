package com.groceryManagement.adminService.restClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.groceryManagement.adminService.model.Orders;

@FeignClient("ORDERS-SERVICE")
public interface OrderClient {
	
	@GetMapping("/findAllOrders")
	public List<Orders> findAll();

}
