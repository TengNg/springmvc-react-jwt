package com.ndt.controllers;

import com.ndt.pojo.*;
import com.ndt.services.CartItemService;
import com.ndt.services.PaymentTransactionService;
import com.ndt.services.TestService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

public class StatisticController {
	@Autowired
	private PaymentTransactionService transactionService;
	@Autowired
	private CartItemService cartItemService;

	@GetMapping("/for-sellers/statistics/{sellerId}")
	public ResponseEntity<Map<String, Object>> product(@PathVariable String sellerId) {
		List<CartItem> items = this.cartItemService.getCartItems();

		Map<String, Object> data = new HashMap<>();

		BigDecimal sum = BigDecimal.ZERO;
		for (CartItem cartItem : items) {
			// data.put("id", cartItem.getProductId().getUserId().getUserId());
			if (cartItem.getProductId().getUserId().getUserId().equals(sellerId)) {
				BigDecimal n = BigDecimal.valueOf(cartItem.getQuantity());
				sum = sum.add(n.multiply(cartItem.getProductId().getPrice()));
			}	
		}

		data.put("totalRevenue", sum);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}

