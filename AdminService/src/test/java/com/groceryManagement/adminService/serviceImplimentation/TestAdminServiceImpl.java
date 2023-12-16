package com.groceryManagement.adminService.serviceImplimentation;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAdminServiceImpl {

	@InjectMocks
	AdminServiceImpl adminServiceImpl;

	@Mock
	AdminRepository adminRepository;
	@Mock
	OrderClient ordersClient;
	@Mock
	UserClient userClient;
	@Mock
	FeedBackClient feedBackClient;
	@Mock
	SuggestionClient suggestionClient;
	@Mock
	ProductClient productClient;

	@MockBean
	Admin admin;
	@MockBean
	FeedBack feedBack;
	@MockBean
	Suggestion suggestion;
	@MockBean
	Product products;

	AdminLoginDTO adminLoginDTO;
	ProductRequestBean productRequestBean;
	ProductUpdateBean productUpdateBean;
	productNameRequired nameRequired;
	@BeforeEach
	public void setUp() {
		admin = new Admin();
		feedBack = new FeedBack();
		suggestion = new Suggestion();
		products = new Product();
		products.setProductName("Apple");
		productRequestBean = new ProductRequestBean();
		productUpdateBean = new ProductUpdateBean();
		productUpdateBean.setProductName("Apple");
		nameRequired=new productNameRequired();
		nameRequired.setProductName("Apple");
	}

//	@Test
//	public void adminRegisterPositive() {
//		adminRegisterDTO = new AdminRegisterDTO();
//		adminRegisterDTO.setName("Abu Hurera");
//		adminRegisterDTO.setPassword("abuhurera@1324");
//		adminRegisterDTO.setEmail("abuhurera@gmail.com");
//		adminRegisterDTO.setPhoneNumber("6362849466");
//		when(adminRepository.save(admin)).thenReturn(admin);
//		assertEquals(HttpStatus.CREATED, adminServiceImpl.adminRegister(adminRegisterDTO).getStatusCode());
//	}

//	@Test
//	public void adminRegisterNegetiveOne() {
//		adminRegisterDTO = new AdminRegisterDTO();
//		adminRegisterDTO.setName("Abu Hurera");
//		adminRegisterDTO.setPassword("abuhurera@1324");
//		adminRegisterDTO.setEmail("abuhurera@gmail.com");
//		adminRegisterDTO.setPhoneNumber("6362849466");
//		when(adminRepository.findByEmail(adminRegisterDTO.getEmail())).thenReturn(admin);
//		when(adminRepository.save(admin)).thenReturn(admin);
//		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
//				adminServiceImpl.adminRegister(adminRegisterDTO).getStatusCode());
//	}

//	@Test
//	public void adminRegisterNegetive() {
//		adminRegisterDTO = new AdminRegisterDTO();
//		adminRegisterDTO.setName("");
//		adminRegisterDTO.setPassword("abuhurera@1324");
//		adminRegisterDTO.setEmail("abuhurera@gmail.com");
//		adminRegisterDTO.setPhoneNumber("6362849466");
//		when(adminRepository.findByEmail(adminRegisterDTO.getEmail())).thenReturn(admin);
//		when(adminRepository.save(admin)).thenReturn(admin);
//		assertEquals("Fill all detalis properly", adminServiceImpl.adminRegister(adminRegisterDTO).getBody());
//	}

	@Test
	public void adminLoginPositive() {
		adminLoginDTO = new AdminLoginDTO();
		adminLoginDTO.setEmail("abuhurera@gmail.com");
		adminLoginDTO.setPassword("abuhurer@123");
		when(adminRepository.findByEmailAndPassword(adminLoginDTO.getEmail(), adminLoginDTO.getPassword()))
		.thenReturn(admin);
		assertEquals(HttpStatus.OK, adminServiceImpl.adminLogin(adminLoginDTO).getStatusCode());
	}

	@Test
	public void adminLoginNegetive() {
		adminLoginDTO = new AdminLoginDTO();
		adminLoginDTO.setEmail("abuhurera@gmail.com");
		adminLoginDTO.setPassword("");
		when(adminRepository.findByEmailAndPassword(adminLoginDTO.getEmail(), adminLoginDTO.getPassword()))
		.thenReturn(admin);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, adminServiceImpl.adminLogin(adminLoginDTO).getStatusCode());
	}

	@Test
	public void adminLoginNegetiveOne() {
		adminLoginDTO = new AdminLoginDTO();
		adminLoginDTO.setEmail("abuhurera@gmail.com");
		adminLoginDTO.setPassword("abuhurera@3234");
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, adminServiceImpl.adminLogin(adminLoginDTO).getStatusCode());
	}

	@Test
	public void fetchAllOrdersDataTest() {
		ArrayList<Orders> list=new ArrayList<Orders>();
		Orders order = new Orders();
		list.add(order);
		when(ordersClient.findAll()).thenReturn(list);
		assertEquals(1, adminServiceImpl.fetchAllOrdersData().size());
	}

	@Test
	public void fetchAllUsersTest() {
		ArrayList<Users> list=new ArrayList<Users>();
		Users userPojo = new Users();
		list.add(userPojo);
		when(userClient.findAll()).thenReturn(list);
		assertEquals(1, adminServiceImpl.fetchAllUsers().size());
	}

	@Test
	public void fetchAllDataNegativeTest() {
		ArrayList<Orders> list=new ArrayList<Orders>();
		Orders order = new Orders();
		list.add(order);
		when(ordersClient.findAll()).thenReturn(list);
		assertNotEquals(null, adminServiceImpl.fetchAllOrdersData().size());
	}

	@Test
	public void fetchAllUsersNegativeTest() {
		ArrayList<Users> list=new ArrayList<Users>();
		Users userPojo = new Users();
		list.add(userPojo);
		when(userClient.findAll()).thenReturn(list);
		assertNotEquals(null, adminServiceImpl.fetchAllUsers().size());
	}
	
	@Test
	public void getAllFeedbackPositive() {
		List<FeedBack> feedBackList = new ArrayList<FeedBack>();
		feedBack.setFeedBack("Nice");
		feedBack.setUserEmail("abuhurera600@gmail.com");
		feedBack.setProductName("Apple");
		feedBackList.add(feedBack);
		when(feedBackClient.findAll()).thenReturn(feedBackList);
		assertEquals(feedBackList, adminServiceImpl.getAllFeedback().getBody());
	}

	@Test
	public void getAllFeedbackNegetive() {
		List<FeedBack> feedBackList = new ArrayList<FeedBack>();
		when(feedBackClient.findAll()).thenReturn(feedBackList);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, adminServiceImpl.getAllFeedback().getStatusCode());
	}
	
	@Test
	public void getSuggestionsTest() {
		List<Suggestion> suggestionList = new ArrayList<>();
		suggestion.setUserEmail("revathi@gmail.com");
		suggestion.setSuggestion("choclate");
		suggestionList.add(suggestion);
		when(suggestionClient.findAll()).thenReturn(suggestionList);
		assertEquals(suggestionList, adminServiceImpl.suggest());
	}
	
	@Test
	public void fetchAllProductsDataTest() {
		List<Product> productList = new ArrayList<>();
		products = new Product();
		products.setProductName("Apple");
		products.setProductType("Fruit");
		products.setShopName("Wear House");
		products.setProductPricePerQuantity(30);
		products.setQuantity("1kg");
		products.setProductDiscount(3);
		products.setStock(30);
		productList.add(products);
		when(productClient.findAll()).thenReturn(productList);
		assertEquals(productList, adminServiceImpl.fetchAllProductData());
	}

	@Test
	public void addDataTest() {
		productRequestBean = new ProductRequestBean();
		assertEquals("Data saved successfully", adminServiceImpl.addProductData(productRequestBean).getBody());
	}

	@Test
	public void deleteDataTest() {
		
		when(productClient.findProduct("Apple")).thenReturn(products);
		assertEquals("Data deleted..", adminServiceImpl.deleteProductData(nameRequired).getBody());
	}

	@Test
	public void deleteDataNotFoundTest() {
		when(productClient.findProduct("Apple")).thenReturn(null);
		assertEquals("Product not fount in product list..", adminServiceImpl.deleteProductData(nameRequired).getBody());
	}

	@Test
	public void updateDataTest() {
		when(productClient.findProduct("Apple")).thenReturn(products);
		assertEquals(products, adminServiceImpl.updateProductData(productUpdateBean).getBody());
	}

	@Test
	public void updateDataElseTest() {
		when(productClient.findProduct("Apple")).thenReturn(null);
		assertEquals("Product not fount in product list..",
				adminServiceImpl.updateProductData(productUpdateBean).getBody());
	}

}
