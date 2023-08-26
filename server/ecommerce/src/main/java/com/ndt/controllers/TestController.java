package com.ndt.controllers;

import com.ndt.components.JwtService;
import com.ndt.pojo.Category;
import com.ndt.pojo.Product;
import com.ndt.services.CategoryService;
import com.ndt.services.ProductService;
import com.ndt.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/products/")
	public ResponseEntity<List<Product>> products() {
		List<Product> products = this.productService.getProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/categories/")
	public ResponseEntity<List<Category>> categories() {
		List<Category> categories = this.categoryService.getCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
}
