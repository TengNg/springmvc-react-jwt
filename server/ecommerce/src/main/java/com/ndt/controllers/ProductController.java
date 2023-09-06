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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class ProductController {
	@Autowired
	private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> products(
			@RequestParam(
					name = "category",
					required = false,
					defaultValue = "All" 
			) String category
	) {
		List<Product> products = this.productService.getProducts();

		if (!"All".equalsIgnoreCase(category)) {
			products = products.stream()
					.filter(product -> product.getCategoryId().getCategoryName().equals(category))
					.collect(Collectors.toList());
		}

		Map<String, Object> data = new HashMap<>();
		data.put("products", products);

		return new ResponseEntity<>(data, HttpStatus.OK);
    }

	@GetMapping("/products/{id}")
	public ResponseEntity<Map<String, Object>> product(@PathVariable String id) {
		Product foundProduct = this.productService.getProductById(id);
		if (foundProduct == null) {
			Map<String, Object> data = new HashMap<>();
			data.put("msg", "no product found");
			return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("product", foundProduct);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable String id) {
		Product foundProduct = this.productService.getProductById(id);
		if (foundProduct == null) {
			Map<String, Object> data = new HashMap<>();
			data.put("msg", "no product found");
			return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
		}

		this.productService.deleteProductById(id);

		Map<String, Object> data = new HashMap<>();
		data.put("product", foundProduct);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}
