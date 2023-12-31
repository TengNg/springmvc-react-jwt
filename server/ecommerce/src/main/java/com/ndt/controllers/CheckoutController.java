package com.ndt.controllers;

import com.ndt.pojo.Cart;
import com.ndt.pojo.CartItem;
import com.ndt.pojo.PaymentTransaction;
import com.ndt.pojo.Product;
import com.ndt.pojo.ShippingProcess;
import com.ndt.services.CartItemService;
import com.ndt.services.CheckoutService;
import com.ndt.services.ProductService;
import com.ndt.services.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	@Autowired
	private ProductService productService;

	@PostMapping("/checkout")
	public ResponseEntity<?> checkout(@RequestBody Map<String, Object> requestBody) {
		// add cart { username, paymentMethod }
		Cart cart = this.checkoutService.proceedCheckout(requestBody);

		// save shipping process
		ShippingProcess process = this.checkoutService.saveProcess(cart.getCartId());

		Object itemsObject = requestBody.get("items");
		List<Map<String, Object>> items = (List<Map<String, Object>>) itemsObject;
		List<CartItem> cartItems = new ArrayList<>();
		for (Map<String, Object> item : items) {
			// add item { cart_id, product_id, quantity }
			item.put("cartId", cart.getCartId());
			CartItem cartItem = this.cartItemService.addCartItem(item);
			cartItems.add(cartItem);

			// handle if cart item quantity > product's stock qty
			// update product's stock qty
			// String productId = (String) item.get("productId");
			// Product product = this.productService.getProductById(productId);

			// Integer productStockQty = product.getStockQuantity();
			// Integer quantity = (Integer) item.get("quantity");

			// if (productStockQty >= quantity) {
			// 	CartItem cartItem = this.cartItemService.addCartItem(item);
			// 	cartItems.add(cartItem);
			// } else {
			// 	
			// }
		}

		Map<String, Object> data = new HashMap<>();
		data.put("cart", cart);
		data.put("items", cartItems);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@PostMapping("/save-transaction")
	public ResponseEntity<?> saveTransaction(@RequestBody Map<String, Object> requestBody) {
		PaymentTransaction transaction = this.checkoutService.saveTransaction(requestBody);
		Map<String, Object> data = new HashMap<>();
		data.put("transaction", transaction);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@PostMapping("/save-shipping-process")
	public ResponseEntity<?> saveShippingProcess(@RequestBody Map<String, Object> requestBody) {
		ShippingProcess process = this.checkoutService.saveProcess((String)requestBody.get("cartId"));
		Map<String, Object> data = new HashMap<>();
		data.put("shipping_process", process);
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

