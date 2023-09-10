package com.ndt.controllers;

import com.ndt.components.JwtService;
import com.ndt.pojo.Product;
import com.ndt.pojo.User;
import com.ndt.services.ProductService;
import com.ndt.services.UserService;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "20") int size,
			@RequestParam(name = "category", required = false, defaultValue = "All" ) String category,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "seller", required = false) String seller,
			@RequestParam(name = "sort_by", required = false) String sortBy
	) {
		List<Product> products = this.productService.getProducts();

		if (!"All".equalsIgnoreCase(category)) {
			products = products.stream()
					.filter(product -> product.getCategoryId().getCategoryName().equals(category))
					.collect(Collectors.toList());
		}

		if (name != null && !name.isEmpty()) {
			products = products.stream()
					.filter(product -> product.getName().contains(name))
					.collect(Collectors.toList());
		}

		if (seller != null && !seller.isEmpty()) {
			products = products.stream()
					.filter(product -> product.getUserId().getUsername().contains(seller))
					.collect(Collectors.toList());
		}

		if (sortBy != null && !sortBy.isEmpty() && sortBy.matches("^(asc|desc)\\(\\w+\\)$")) {
			String[] parts = sortBy.split("\\(");
			String sortDirection = parts[0];
			String fieldName = parts[1].substring(0, parts[1].length() - 1);
			Comparator<Product> comparator = null;
			switch (fieldName) {
				case "name":
					comparator = Comparator.comparing(Product::getName);
					break;
				case "price":
					comparator = Comparator.comparing(Product::getPrice);
					break;
				default:
					throw new IllegalArgumentException("Invalid field name: " + fieldName);
			}
			
			if (sortDirection.equals("asc")) {
				Collections.sort(products, comparator);
			} else if (sortDirection.equals("desc")) {
				Collections.sort(products, comparator.reversed());
			} else {
				throw new IllegalArgumentException("Invalid sort direction: " + sortDirection);
			}
		}

		int totalItems = products.size();
		int start = page * size;
		int end = Math.min(start + size, totalItems);
		products = products.subList(start, end);

		Map<String, Object> data = new HashMap<>();
		data.put("products", products);
		data.put("totalItems", totalItems);

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

	@PostMapping(
			path = "/products/add",
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	public ResponseEntity<?> addUser(@RequestParam Map<String, String> params, @RequestPart MultipartFile image) {
		if (image == null) {
			Map<String, Object> data = new HashMap<>();
			data.put("msg", "missing profile image");
			return new ResponseEntity<>(data, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		Product newProduct = this.productService.addProduct(params, image);
		Map<String, Object> data = new HashMap<>();
		data.put("new_product", newProduct);
		return new ResponseEntity<>(data, HttpStatus.CREATED);
	}
}
