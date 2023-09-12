package com.ndt.controllers;

import com.ndt.pojo.Cart;
import com.ndt.pojo.CartItem;
import com.ndt.pojo.PaymentTransaction;
import com.ndt.repositories.PaymentTransactionRepository;
import com.ndt.repositories.ShippingProcessRepository;
import com.ndt.services.CartItemService;
import com.ndt.services.CartService;
import com.ndt.services.CategoryService;
import com.ndt.services.CheckoutService;
import com.ndt.services.PaymentTransactionService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(
		origins = {
			"http://localhost:5173",
			"http://localhost:5174",
			"http://localhost:3000",
			"http://localhost:3001",
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
public class PurchaseHistoryController {
	@Autowired
	private CheckoutService checkoutService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ShippingProcessRepository shippingProcessRepository;
	@Autowired
	private PaymentTransactionService paymentTransactionService;

	@GetMapping("/purchase-history/{userId}")
	public ResponseEntity<?> purchaseHistory(@PathVariable String userId) {
		List<PaymentTransaction> transactions = this.checkoutService.getTransactionsByUserId(userId);
		Map<String, Object> data = new HashMap<>();
		data.put("transactions", transactions);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/purchase-history/transactions/{cartId}")
	public ResponseEntity<?> purchaseDetails(@PathVariable String cartId) {
		List<CartItem> items = this.cartItemService.getCartItems(cartId);
		PaymentTransaction paymentTransaction = this.checkoutService.getTransactionByCartId(cartId);
		Map<String, Object> data = new HashMap<>();
		data.put("items", items);
		data.put("transaction", paymentTransaction);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@DeleteMapping("/purchase-history/transactions/{cartId}")
	public ResponseEntity<?> cancelTransaction(@PathVariable String cartId) {
		// delete all items from cart
		this.cartItemService.deleteCartItemsByCartId(cartId);

		// delete relative transaction
		this.paymentTransactionService.removePaymentTransaction(cartId);

		// delete relative shipping_process
		this.shippingProcessRepository.deleteShippingProcessByCartId(cartId);

		// delete cart
		this.cartService.delete(cartId);

		Map<String, Object> data = new HashMap<>();
		data.put("msg", "purchase transaction removed");
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}
