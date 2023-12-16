package com.groceryManagement.adminService.restClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.groceryManagement.adminService.model.FeedBack;

@FeignClient("FEEDBACK-SERVICE")
public interface FeedBackClient {
	
	@GetMapping("SeeAllFeedBacks")
	public List<FeedBack> findAll();

}
