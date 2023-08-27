package com.ndt.controllers;

import com.ndt.components.JwtService;
import com.ndt.pojo.Product;
import com.ndt.pojo.User;
import com.ndt.services.ProductService;
import com.ndt.services.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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
public class UserController {
	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

    @GetMapping("/account")
    public ResponseEntity<Map<String, Object>> products(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		String token = authorizationHeader.substring(7);

		if (token == null || token.isEmpty() || token.length() == 0) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}

		String username = this.jwtService.getUsernameFromToken(token);

		if (username == null) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}	

		User user = this.userService.getUserByUsername(username);
		Map<String, Object> data = new HashMap<>();
		data.put("user", user);
		data.put("accessToken", token);

		return new ResponseEntity<>(data, HttpStatus.OK);
    }
}

