package com.ndt.controllers;

import com.ndt.components.JwtService;
import com.ndt.services.TokenValidationService;
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
public class TokenCheckController {
	@Autowired
	private JwtService jwtService;

	@GetMapping("/check-token/")
	public ResponseEntity<String> checkToken(@RequestHeader("Authorization") String authorizationHeader) {
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			return new ResponseEntity<>("No token", HttpStatus.OK);
		}
		
		String token = authorizationHeader.substring(7);
		
		if (token.isEmpty() || token == null) {
			return new ResponseEntity<>("No token", HttpStatus.OK);
		}
		
		String username = this.jwtService.getUsernameFromToken(token);
		return new ResponseEntity<>("token?: " + token, HttpStatus.OK);
	}
}
