package com.ndt.controllers;

import com.ndt.pojo.User;
import com.ndt.services.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
    private UserService userService;
    
	@PostMapping(
			path = "/register",
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	public ResponseEntity<?> addUser(@RequestParam Map<String, String> params, @RequestPart MultipartFile image) {
		if (image == null) {
			Map<String, Object> data = new HashMap<>();
			data.put("msg", "missing profile image");
			return new ResponseEntity<>(data, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		User newUser = this.userService.addUser(params, image);
		Map<String, Object> data = new HashMap<>();
		data.put("msg", "new user created");
		data.put("new_user", newUser);
		return new ResponseEntity<>(data, HttpStatus.CREATED);
	}
}
