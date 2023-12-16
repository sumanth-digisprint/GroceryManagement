package com.groceryManagement.adminService.restClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.groceryManagement.adminService.model.Suggestion;

@FeignClient("SUGGESTION-SERVICE")
public interface SuggestionClient {
	
	@GetMapping("/findAllSuggestions")
	public List<Suggestion> findAll();

}
