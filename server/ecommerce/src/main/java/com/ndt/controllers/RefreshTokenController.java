package com.ndt.controllers;

import com.ndt.components.JwtService;
import com.ndt.pojo.User;
import com.ndt.services.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
public class RefreshTokenController {
    @Autowired
    private JwtService jwtService;

	@Autowired
	private UserService userService;

	@GetMapping("/refresh")
	public ResponseEntity<Map<String, String>> handleRefreshToken(
			@CookieValue(name = "token", required = false) Cookie refreshTokenCookie, 
			HttpServletResponse response
	) {
		if (refreshTokenCookie == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}

		String refreshToken = refreshTokenCookie.getValue();

		User foundUser = this.userService.getUserByRefreshToken(refreshToken);

		if (foundUser == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		try {
			if (!this.jwtService.verifyToken(refreshToken)) {
				return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
			}
			
			String newAccessToken = this.jwtService.generateTokenLogin(
					foundUser.getUsername(), 
					JwtService.ACCESS_TOKEN_EXPIRE_TIME
			);
			
			Map<String, String> data = new HashMap<>();
			data.put("accessToken", newAccessToken);
			data.put("userRole", foundUser.getUserRole());
			
			return ResponseEntity.ok().body(data);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
}

