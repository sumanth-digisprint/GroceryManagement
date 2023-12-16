package com.groceryManagement.adminService.serviceImplimentation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.groceryManagement.adminService.model.Admin;
import com.groceryManagement.adminService.model.FeedBack;
import com.groceryManagement.adminService.model.Orders;
import com.groceryManagement.adminService.model.Product;
import com.groceryManagement.adminService.model.Suggestion;
import com.groceryManagement.adminService.model.Users;
import com.groceryManagement.adminService.repository.AdminRepository;
import com.groceryManagement.adminService.requestBean.AdminLoginDTO;
import com.groceryManagement.adminService.requestBean.ProductRequestBean;
import com.groceryManagement.adminService.requestBean.ProductUpdateBean;
import com.groceryManagement.adminService.requestBean.productNameRequired;
import com.groceryManagement.adminService.restClient.FeedBackClient;
import com.groceryManagement.adminService.restClient.OrderClient;
import com.groceryManagement.adminService.restClient.ProductClient;
import com.groceryManagement.adminService.restClient.SuggestionClient;
import com.groceryManagement.adminService.restClient.UserClient;
import com.groceryManagement.adminService.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private AdminRepository adminRepository;
	private UserClient userClient;
	private SuggestionClient suggestionClient;
	private OrderClient ordersClient;
	private ProductClient productClient;
	private FeedBackClient feedBackClient;

	public AdminServiceImpl(AdminRepository adminRepository, UserClient userClient, SuggestionClient suggestionClient,
			OrderClient ordersClient, ProductClient productClient, FeedBackClient feedBackClient) {
		super();
		this.adminRepository = adminRepository;
		this.userClient = userClient;
		this.suggestionClient = suggestionClient;
		this.ordersClient = ordersClient;
		this.productClient = productClient;
		this.feedBackClient = feedBackClient;
	}

//	@Override
//	public ResponseEntity<String> adminRegister(AdminRegisterDTO adminRegisterDTO) {
//		if (!adminRegisterDTO.getName().isEmpty() && !adminRegisterDTO.getEmail().isEmpty()
//				&& !adminRegisterDTO.getPassword().isEmpty() && !adminRegisterDTO.getPhoneNumber().isEmpty()) {
//			Admin admin = adminRepository.findByEmail(adminRegisterDTO.getEmail());
//			if (admin != null) {
//				return new ResponseEntity<>("Already Register", HttpStatus.INTERNAL_SERVER_ERROR);
//
//			} else {
//				Admin admin1 = new Admin();
//				BeanUtils.copyProperties(adminRegisterDTO, admin1);
//				adminRepository.save(admin1);
//				return new ResponseEntity<>("Register Done", HttpStatus.CREATED);
//			}
//		}
//		return new ResponseEntity<>("Fill all detalis properly", HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	@Override
	public ResponseEntity<String> adminLogin(AdminLoginDTO adminLoginDTO) {
		if (!adminLoginDTO.getEmail().isEmpty() && !adminLoginDTO.getPassword().isEmpty()) {
			Admin admin = adminRepository.findByEmailAndPassword(adminLoginDTO.getEmail(), adminLoginDTO.getPassword());
			if (Objects.nonNull(admin)) {
				return new ResponseEntity<>("Login successful", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Incorrect Details", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>("Fill all detalis properly", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public List<Users> fetchAllUsers() {
		return userClient.findAll();
	}

	@Override
	public ResponseEntity<List<FeedBack>> getAllFeedback() {
		List<FeedBack> list = feedBackClient.findAll();
		if(list.isEmpty()) {
			return new ResponseEntity("List Of Empty", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return  new ResponseEntity(list,HttpStatus.OK);
	}

	@Override
	public List<Suggestion> suggest() {
		return suggestionClient.findAll();
	}

	@Override
	public List<Orders> fetchAllOrdersData() {
		return ordersClient.findAll();
	}
	

	@Override
	public List<Product> fetchAllProductData() {
		return productClient.findAll();
	}

	@Override
	public ResponseEntity<String> addProductData(ProductRequestBean product) {
		Product product2 = new Product();
		BeanUtils.copyProperties(product, product2);
		product2.setProductAddDate(LocalDate.now().atTime(LocalTime.now()));
		productClient.save(product2);
		return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteProductData(productNameRequired product) {
		try {
			Product result = productClient.findProduct(product.getProductName());
			productClient.deleteById(result.getProductId());
			return new ResponseEntity<>("Data deleted..", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Product not fount in product list..", HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<Product> updateProductData(ProductUpdateBean product) {
		Product	 result = productClient.findProduct(product.getProductName());
		if(result==null) {
			return new ResponseEntity("Product not fount in product list..", HttpStatus.OK);
		}
		result.setProductPricePerQuantity(product.getProductPricePerQuantity());
		result.setQuantity(product.getQuantity());
		result.setProductDiscount(product.getProductDiscount());
		result.setStock(product.getStock());
		productClient.save(result);
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}