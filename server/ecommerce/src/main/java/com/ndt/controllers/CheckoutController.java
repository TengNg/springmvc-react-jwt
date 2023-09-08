package com.ndt.controllers;

import com.ndt.pojo.Cart;
import com.ndt.pojo.CartItem;
import com.ndt.pojo.Category;
import com.ndt.pojo.User;
import com.ndt.services.CartItemService;
import com.ndt.services.CategoryService;
import com.ndt.services.CheckoutService;
import com.ndt.services.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(
		origins = {
			"http://localhost:5173",
			"http://localhost:5174",
			"http://localhost:8080"
		},
		allowCredentials = "true",
		maxAge = 3600,
		allowedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.POST,
			RequestMethod.DELETE, RequestMethod.PUT,
			RequestMethod.PATCH, RequestMethod.OPTIONS,
			RequestMethod.HEAD, RequestMethod.TRACE}
)
public class CheckoutController {
	@Autowired
	private CheckoutService checkoutService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private UserService userService;

	@PostMapping("/checkout")
	public ResponseEntity<?> checkout(@RequestBody Map<String, Object> requestBody) {
		// add cart { username, paymentMethod }
		Cart cart = this.checkoutService.proceedCheckout(requestBody);

		Object itemsObject = requestBody.get("items");
		List<Map<String, Object>> items = (List<Map<String, Object>>) itemsObject;
		List<CartItem> cartItems = new ArrayList<>();
		for (Map<String, Object> item : items) {
			// add item { cart_id, product_id, quantity }
			item.put("cartId", cart.getCartId());
			CartItem cartItem = this.cartItemService.addCartItem(item);
			cartItems.add(cartItem);
		}

		Map<String, Object> data = new HashMap<>();
		data.put("cart", cart);
		data.put("items", cartItems);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@PostMapping("/checkout/cart/add-item")
	public ResponseEntity<?> addItem(@RequestBody Map<String, Object> params) {
		CartItem cartItem = this.cartItemService.addCartItem(params);

		Map<String, Object> data = new HashMap<>();
		data.put("cartItem", cartItem);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/checkout/cart/{userId}")
	public ResponseEntity<?> cart(@PathVariable String userId) {
		List<Cart> carts = this.checkoutService.getCartsByUserId(userId);

		Map<String, Object> data = new HashMap<>();
		data.put("carts", carts);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}

