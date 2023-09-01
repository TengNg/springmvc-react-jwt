package com.ndt.controllers;

import com.ndt.pojo.*;
import com.ndt.services.TestService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
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

public class TestController {
	@Autowired
	private TestService testService;
	
	@GetMapping("/orders")
	public ResponseEntity<?> saleOrders(@RequestBody Map<String, Object> requestBody) { 
		int userId = (int) requestBody.get("userId");
		List<SaleOrder> orders = this.testService.userSaleOrders(userId);

		Map<String, Object> data = new HashMap<>();
		data.put("orders", orders);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/orders/details")
	public ResponseEntity<?> orderDetails(@RequestBody Map<String, Object> requestBody) { 
		int userId = (int) requestBody.get("userId");
		List<SaleOrder> orders = this.testService.userSaleOrders(userId);

		Map<String, Object> data = new HashMap<>();

		for (SaleOrder order : orders) {
			List<OrderDetail> orderDetails = this.testService.orderDetails(order.getId());
			data.put("order-" + order.getId(), orderDetails);
		}

		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}
