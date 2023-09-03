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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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

public class LoginController {
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserService userService;

	@GetMapping("/check-cookies")
	public ResponseEntity<Map<String, Object>> cookiesCheck(
			@CookieValue(name = "token", required = false) Cookie refreshTokenCookie,
			HttpServletResponse response
	) {
		if (refreshTokenCookie == null) {
			Map<String, Object> data = new HashMap<>();
			data.put("msg", "Refresh token not found");
			return ResponseEntity.status(401).body(data);
		}

		if (!this.jwtService.verifyToken(refreshTokenCookie.getValue())) {
			Map<String, Object> data = new HashMap<>();
			data.put("msg", "Refresh token is not valid");
			return ResponseEntity.status(401).body(data);
		}

		Map<String, Object> data = new HashMap<>();
		data.put("msg", "user is already logged in");
		return ResponseEntity.status(200).body(data);
	}
    
	@PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user, HttpServletResponse response) {
        if (this.userService.authUser(user.getUsername(), user.getPassword()) == true) {
            String refreshToken = this.jwtService.generateTokenLogin(
					user.getUsername(), 
					JwtService.REFRESH_TOKEN_EXPIRE_TIME
			);

            String accessToken = this.jwtService.generateTokenLogin(
					user.getUsername(), 
					JwtService.ACCESS_TOKEN_EXPIRE_TIME
			);

			User currentUser = this.userService.getUserByUsername(user.getUsername());
			if (currentUser != null){
				this.userService.updateRefreshToken(currentUser, refreshToken);
			}

			HttpCookie refreshTokenCookie = ResponseCookie.from("token", refreshToken)
					.maxAge(60 * 60 * 24) 
					.httpOnly(true)
					.path("/")
					.build();

			Map<String, String> data = new HashMap<>();
			data.put("accessToken", accessToken);
			data.put("refreshToken", refreshToken);

			return ResponseEntity.ok()
					.header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
					.body(data);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
