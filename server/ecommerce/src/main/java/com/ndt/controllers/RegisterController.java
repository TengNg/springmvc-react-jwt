package com.ndt.controllers;

import com.ndt.components.JwtService;
import com.ndt.pojo.User;
import com.ndt.services.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
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

public class RegisterController {
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserService userService;
    
	@PostMapping("/register")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user, HttpServletResponse response) {
		User foundUser = this.userService.getUserByUsername(user.getUsername());
		if (foundUser != null) {
			Map<String, Object> data = new HashMap<>();
			data.put("msg", "username is already exist");
			return new ResponseEntity<>(data, HttpStatus.CONFLICT);
		}

		User newUser = this.userService.addUser(user);

		Map<String, Object> data = new HashMap<>();
		data.put("msg", "new user created");
		data.put("user", newUser);

		return new ResponseEntity<>(data, HttpStatus.CREATED);
    }
}
