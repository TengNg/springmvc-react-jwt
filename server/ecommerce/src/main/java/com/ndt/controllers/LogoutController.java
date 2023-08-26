package com.ndt.controllers;

import com.ndt.components.JwtService;
import com.ndt.pojo.User;
import com.ndt.services.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

public class LogoutController {
    @Autowired
    private UserService userService;
    
	@GetMapping("/logout")
	public ResponseEntity<Map<String, Object>> handleRefreshToken(
			@CookieValue(name = "token", required = false) Cookie refreshTokenCookie,
			HttpServletResponse response
	) {
		if (refreshTokenCookie == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

		String refreshToken = refreshTokenCookie.getValue();

		User foundUser = this.userService.getUserByRefreshToken(refreshToken);

		if (foundUser == null) {
			Cookie cookie = new Cookie("token", null);
			cookie.setMaxAge(0);
			cookie.setHttpOnly(true);
			cookie.setPath("/");
			response.addCookie(cookie);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

		this.userService.updateRefreshToken(foundUser, null);

		Cookie cookie = new Cookie("token", null);
		cookie.setMaxAge(0);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		response.addCookie(cookie);

		Map<String, Object> data = new HashMap<>();
		data.put("msg", "logout success");

		return new ResponseEntity<>(data, HttpStatus.OK);
    }
}

