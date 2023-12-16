package com.groceryManagement.cartService.serviceImplementation;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import com.groceryManagement.cartService.model.Cart;
import com.groceryManagement.cartService.model.Product;
import com.groceryManagement.cartService.model.Users;
import com.groceryManagement.cartService.repository.CartRepository;
import com.groceryManagement.cartService.requestBean.CartDeleteDto;
import com.groceryManagement.cartService.requestBean.CartDto;
import com.groceryManagement.cartService.requestBean.OrderDto;
import com.groceryManagement.cartService.restClient.ProductClient;
import com.groceryManagement.cartService.restClient.UserClient;
import com.groceryManagement.cartService.serviceImplimentation.CartServiceImplementation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceImplementationTest {

	@InjectMocks
	private CartServiceImplementation cartServiceImplementation;

	@Mock
	private CartRepository cartRepository;
	@Mock
	private ProductClient productClient;
	@Mock
	private UserClient userClient;

	@MockBean
	private Users user;
	@MockBean
	private Product product;
	@MockBean
	private Cart cart;
	private CartDto cartDto;
	private OrderDto orderDto;
	private CartDeleteDto  cartDeleteDto;

	@Test
	public void addProductToCartUserNullTest() {
		user=new Users();
		product=new Product();
		product.setStock(20);
		user.setUserEmail("anmol12@gmail.com");
		cartDto=new CartDto();
		cartDto.setProductName("Onion");
		cartDto.setUserEmail("anmol12@gmail.com");
		cartDto.setQuantity(4);
		when(productClient.findProduct(cartDto.getProductName())).thenReturn(product);
		when(userClient.findUser(cartDto.getUserEmail())).thenReturn(null);
		when(cartRepository.save(cartDto)).thenReturn(cart);
		assertEquals("Wrong EmailAddress! plz enter currect email!",cartServiceImplementation.addProductToCart(cartDto).getBody());

	}

	@Test
	public void addProductToCartPositiveTest() {
		user=new Users();
		product=new Product();
		product.setStock(20);
		user.setUserEmail("anmol12@gmail.com");
		cartDto=new CartDto();
		cartDto.setProductName("Onion");
		cartDto.setUserEmail("anmol12@gmail.com");
		cartDto.setQuantity(4);
		when(productClient.findProduct(cartDto.getProductName())).thenReturn(product);
		when(userClient.findUser(cartDto.getUserEmail())).thenReturn(user);
		when(cartRepository.save(cartDto)).thenReturn(cart);
		assertEquals("Product added Successfully!",cartServiceImplementation.addProductToCart(cartDto).getBody());

	}

	@Test
	public void addProductToCartElseTest() {
		cartDto=new CartDto();
		cartDto.setProductName("Onion");
		cartDto.setUserEmail("anmol12@gmail.com");
		cartDto.setQuantity(10);
		when(productClient.findProduct(cartDto.getProductName())).thenReturn(null);
		assertEquals("Product not found in stock!",cartServiceImplementation.addProductToCart(cartDto).getBody());

	}
	
	
	@Test
	public void addProductToCartEmptyListTest() {
		user=new Users();
		product=new Product();
		product.setStock(20);
		user.setUserEmail("anmol12@gmail.com");
		cartDto=new CartDto();
		cartDto.setProductName("Onion");
		cartDto.setUserEmail("anmol12@gmail.com");
		cartDto.setQuantity(1000);
		when(productClient.findProduct(cartDto.getProductName())).thenReturn(product);
		when(userClient.findUser(cartDto.getUserEmail())).thenReturn(user);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,cartServiceImplementation.addProductToCart(cartDto).getStatusCode());

	}

	
	@Test
	public void getAllCartItemTest() {
		orderDto=new OrderDto();
		orderDto.setUserEmail("anmol12@gmail.com");
		user=new Users();
		user.setUserEmail("anmol12@gmail.com");
		user.setUserName("Hanish");
		List<Cart> list = new ArrayList();
		list.add(cart);
		when(userClient.findUser(orderDto.getUserEmail())).thenReturn(user);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, cartServiceImplementation.getAllCartItem(orderDto).getStatusCode());

	}
	
	@Test
	public void getAllCartItemElseTest() {
		orderDto= new OrderDto();
		when(userClient.findUser(orderDto.getUserEmail())).thenReturn(null);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, cartServiceImplementation.getAllCartItem(orderDto).getStatusCode());

	}
	
	@Test
	public void getAllCartItemElseListTest() {
		orderDto=new OrderDto();
		orderDto.setUserEmail("anmol12@gmail.com");
		user=new Users();
		user.setUserEmail("anmol12@gmail.com");
		user.setUserName("Hanish");
		List<Cart> list = new ArrayList();
		list.add(null);
		when(userClient.findUser(orderDto.getUserEmail())).thenReturn(user);
		when(cartRepository.findAllByUserEmail(orderDto.getUserEmail())).thenReturn(list);
		assertEquals(HttpStatus.OK, cartServiceImplementation.getAllCartItem(orderDto).getStatusCode());

	}
	
	

	@Test
	public void deleteProductTest() {
		Product product=new Product();
		product.setStock(100);
		product.setProductName("Redmi");
		List<Product>listOfProduct= new ArrayList<>();
		listOfProduct.add(product);
		cart=new Cart();
		cart.setCartId("12454");
	
		cartDeleteDto=new CartDeleteDto();
		cartDeleteDto.setCartId("12454");
		when(cartRepository.findByCartId(cartDeleteDto.getCartId())).thenReturn(cart);
		assertEquals(HttpStatus.OK, cartServiceImplementation.deleteProduct(cartDeleteDto).getStatusCode());
	   
	    }
	
	@Test
	public void deleteProductElseTest() {
		Product product=new Product();
		product.setStock(100);
		product.setProductName("Redmi");
		List<Product>listOfProduct= new ArrayList<>();
		listOfProduct.add(product);
		cart=new Cart();
		cart.setCartId("12454");
	
		cartDeleteDto=new CartDeleteDto();
		cartDeleteDto.setCartId("12454");
		when(cartRepository.findByCartId(cartDeleteDto.getCartId())).thenReturn(null);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, cartServiceImplementation.deleteProduct(cartDeleteDto).getStatusCode());
	   
	    }
	
	@Test
	public void findAllByUserEmailTest() {
		String userMail="amit12@gmial.com";
		cart= new Cart();
		cart.setCartId("1");
		List<Cart>cartlist= new ArrayList<>();
		cartlist.add(cart);
		when(cartRepository.findAllByUserEmail(userMail)).thenReturn(cartlist);
		assertEquals(1, cartServiceImplementation.findAllByUserEmail(userMail).size());
	}
	
	@Test
	public void deleteAllByUserEmailTest() {
		String userMail="amit12@gmial.com";
		assertEquals("Product deleted from cart", cartServiceImplementation.deleteAllByUserEmail(userMail));
}

}
