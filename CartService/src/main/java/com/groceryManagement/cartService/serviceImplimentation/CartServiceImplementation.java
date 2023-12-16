package com.groceryManagement.cartService.serviceImplimentation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.groceryManagement.cartService.model.Cart;
import com.groceryManagement.cartService.model.Product;
import com.groceryManagement.cartService.model.Users;
import com.groceryManagement.cartService.repository.CartRepository;
import com.groceryManagement.cartService.requestBean.CartDeleteDto;
import com.groceryManagement.cartService.requestBean.CartDto;
import com.groceryManagement.cartService.requestBean.OrderDto;
import com.groceryManagement.cartService.restClient.ProductClient;
import com.groceryManagement.cartService.restClient.UserClient;
import com.groceryManagement.cartService.service.CartService;

@Service
public class CartServiceImplementation implements CartService {

	private CartRepository cartRepository;
	private ProductClient productClient;
	private UserClient userClient;

	public CartServiceImplementation(CartRepository cartRepository, ProductClient productClient,
			UserClient userClient) {
		this.cartRepository = cartRepository;
		this.productClient = productClient;
		this.userClient = userClient;
	}

	@Override
	public ResponseEntity<String> addProductToCart(CartDto cartDto) {

		Product productDetails;
		try {
			productDetails = productClient.findProduct(cartDto.getProductName());
			if (productDetails==null) {
				return new ResponseEntity<String>("Product not found in stock!", HttpStatus.INTERNAL_SERVER_ERROR);

			}
		} catch (Exception e) {
			return new ResponseEntity<String>("product not found in stock!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (cartDto.getQuantity() <= productDetails.getStock()) {



			Users userDetails = userClient.findUser(cartDto.getUserEmail());
			if (userDetails == null) {
				return new ResponseEntity<String>("Wrong EmailAddress! plz enter currect email!",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			Cart cart = new Cart();
			if (userDetails.getUserEmail().equals(cartDto.getUserEmail())) {
				cart.setProductName(cartDto.getProductName());
				cart.setUserEmail(cartDto.getUserEmail());
				cart.setProductQuantity(cartDto.getQuantity());
				cart.setProductOriginalPrice(productDetails.getProductPricePerQuantity());
				cart.setProductPriceWithDiscount(
						productDetails.getProductPricePerQuantity() - productDetails.getProductDiscount());
				cart.setDiscountPrice(productDetails.getProductDiscount());
				cart.setProductId(productDetails.getProductId());
				cartRepository.save(cart);
			}
		}else {
			return new ResponseEntity<String>("Product out of stock!",
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<String>("Product added Successfully!", HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<List<Cart>> getAllCartItem(OrderDto orderDto) {
		
		Users userPojo = userClient.findUser(orderDto.getUserEmail());
		if (userPojo == null) {
			return new ResponseEntity("userEmail not found!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<Cart> cartlist = cartRepository.findAllByUserEmail(orderDto.getUserEmail());
		if (cartlist.isEmpty()) {
			return new ResponseEntity("cart is Empty!", HttpStatus.INTERNAL_SERVER_ERROR);
		} else
			return new ResponseEntity<>(cartlist, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Cart> deleteProduct(CartDeleteDto cartDeleteDto) {
		Cart deleteProduct = cartRepository.findByCartId(cartDeleteDto.getCartId());
		if (deleteProduct == null) {
			return new ResponseEntity("cartId not found!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		cartRepository.delete(deleteProduct);
		return new ResponseEntity("deleted successfully!", HttpStatus.OK);

	}

	@Override
	public List<Cart> findAllByUserEmail(String userMail) {
		return cartRepository.findAllByUserEmail(userMail);
	}

	@Override
	public String deleteAllByUserEmail(String userMail) {
		cartRepository.deleteAllByUserEmail(userMail);
		return "Product deleted from cart";
	}

}
