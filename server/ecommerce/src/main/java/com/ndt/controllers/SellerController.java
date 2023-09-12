package com.ndt.controllers;

import com.ndt.components.JwtService;
import com.ndt.pojo.Product;
import com.ndt.pojo.User;
import com.ndt.services.ProductService;
import com.ndt.services.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/for-sellers")
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
public class SellerController {
	@Autowired
	private ProductService productService;

	@GetMapping(value = "/manage-products/{sellerId}")
	public ResponseEntity<Map<String, Object>> products(@PathVariable String sellerId) {
		List<Product> products = this.productService.getProductsBySellerId(sellerId);

		Map<String, Object> data = new HashMap<>();
		data.put("products", products);

		return new ResponseEntity<>(data, HttpStatus.OK);
    }

	@PostMapping(value = "/manage-products/{sellerId}/add-product")
	public ResponseEntity<Map<String, Object>> addProduct(@RequestBody Map<String, Object> request) {
		return null;
	}
	
	@PutMapping(value = "/manage-products/{sellerId}/edit-product")
	public ResponseEntity<Map<String, Object>> editProduct(@RequestBody Map<String, Object> request) {
		return null;
	}

	@DeleteMapping(value = "/manage-products/{sellerId}/edit-product")
	public ResponseEntity<Map<String, Object>> deleteProduct(@RequestBody Map<String, Object> request) {
		return null;
	}
}

