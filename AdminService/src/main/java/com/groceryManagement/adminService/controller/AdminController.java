package com.groceryManagement.adminService.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.groceryManagement.adminService.model.FeedBack;
import com.groceryManagement.adminService.model.Orders;
import com.groceryManagement.adminService.model.Product;
import com.groceryManagement.adminService.model.Suggestion;
import com.groceryManagement.adminService.model.Users;
import com.groceryManagement.adminService.requestBean.AdminLoginDTO;
import com.groceryManagement.adminService.requestBean.ProductRequestBean;
import com.groceryManagement.adminService.requestBean.ProductUpdateBean;
import com.groceryManagement.adminService.requestBean.productNameRequired;
import com.groceryManagement.adminService.service.AdminService;

@RestController
public class AdminController {

	private AdminService adminService;
	
	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

//	@PostMapping("/addAdmin") 
//	public ResponseEntity<String> registerAdmin(@RequestBody AdminRegisterDTO adminRegisterDTO) {
//		return adminService.adminRegister(adminRegisterDTO);
//	}

	@PostMapping("/adminLogin")
	public ResponseEntity<String> adminLogin(@RequestBody AdminLoginDTO adminLoginDTO) {
		return adminService.adminLogin(adminLoginDTO);
	}

	@GetMapping("/findAllUsersByAdmin")
	public List<Users> getAllusersByAdmin() {
		return adminService.fetchAllUsers();
	}

	@GetMapping("/SeeAllFeedBacks")
	public ResponseEntity<List<FeedBack>> getAll() {
		return adminService.getAllFeedback();

	}

	@GetMapping("/Get Suggestion")
	public List<Suggestion> getSuggestion(){
		return adminService.suggest();
	}
	
	@GetMapping("/findAllOrderedsByAdmin")
	public List<Orders> getAllOrderedsByAdmin() {
		return adminService.fetchAllOrdersData();
	}

	@GetMapping("/findAllProductsByAdmin")
	public List<Product> getAllProductsByAdmin() {
		return adminService.fetchAllProductData();
	}

	@PostMapping("/productAdding")
	public ResponseEntity<String> addProduct(@RequestBody ProductRequestBean product) {
		return adminService.addProductData(product);
	}

	@PostMapping("/productDeleting")
	public ResponseEntity<String> deleteProduct(@RequestBody productNameRequired product) {
		return adminService.deleteProductData(product);
	}

	@PutMapping("/updateProductData")
	public ResponseEntity<Product> updateProduct(@RequestBody ProductUpdateBean product) {
		return adminService.updateProductData(product);
	}

}
