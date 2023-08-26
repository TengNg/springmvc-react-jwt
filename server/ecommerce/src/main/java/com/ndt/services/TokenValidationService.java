package com.ndt.services;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class TokenValidationService {
	public String getUserFromToken(String token) {
		try {
			JWT jwt = SignedJWT.parse(token);

			JWTClaimsSet claims = jwt.getJWTClaimsSet();

			if (claims.getExpirationTime().getTime() < System.currentTimeMillis()) {
				return null;
			}

			String username = (String) claims.getClaim("username");

			return username;
		} catch (ParseException e) {
			return null;
		}
	}
}
