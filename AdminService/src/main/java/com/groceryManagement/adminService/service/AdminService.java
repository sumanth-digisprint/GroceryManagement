package com.groceryManagement.adminService.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.groceryManagement.adminService.model.FeedBack;
import com.groceryManagement.adminService.model.Orders;
import com.groceryManagement.adminService.model.Product;
import com.groceryManagement.adminService.model.Suggestion;
import com.groceryManagement.adminService.model.Users;
import com.groceryManagement.adminService.requestBean.AdminLoginDTO;
import com.groceryManagement.adminService.requestBean.ProductRequestBean;
import com.groceryManagement.adminService.requestBean.ProductUpdateBean;
import com.groceryManagement.adminService.requestBean.productNameRequired;

public interface AdminService {

//	ResponseEntity<String> adminRegister(AdminRegisterDTO adminRegisterDTO);

	ResponseEntity<String> adminLogin(AdminLoginDTO adminLoginDTO);

	List<Users> fetchAllUsers();

	ResponseEntity<List<FeedBack>> getAllFeedback();

	List<Suggestion> suggest();
	
	List<Orders> fetchAllOrdersData();	

	List<Product> fetchAllProductData();

	ResponseEntity<String> addProductData(ProductRequestBean product);

	ResponseEntity<String> deleteProductData(productNameRequired product);

	ResponseEntity<Product> updateProductData(ProductUpdateBean product);

}
