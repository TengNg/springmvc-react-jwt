/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.components;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.text.ParseException;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author huu-thanhduong
 */
@Component
public class JwtService {

    public static final String SECRET_KEY = "11111111111111111111111111111111";
    public static final byte[] SHARED_SECRET_KEY = SECRET_KEY.getBytes();
    public static final int REFRESH_TOKEN_EXPIRE_TIME = 24 * 60 * 60 * 1000; // 1 day
    public static final int ACCESS_TOKEN_EXPIRE_TIME = 15 * 60 * 1000; // 15 min

    public String generateTokenLogin(String username, int expireTime) {
        String token = null;
        try {
            JWSSigner signer = new MACSigner(SHARED_SECRET_KEY);

            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim("username", username);

            builder.expirationTime(new Date(System.currentTimeMillis() + expireTime));

            JWTClaimsSet claimsSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            signedJWT.sign(signer);
            token = signedJWT.serialize();
        } catch (JOSEException e) {
            System.out.println(e.getMessage());
        }
        return token;
    }

    private JWTClaimsSet getClaimsFromToken(String token) {
        JWTClaimsSet claims = null;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SHARED_SECRET_KEY);
            if (signedJWT.verify(verifier)) {
                claims = signedJWT.getJWTClaimsSet();
            }
        } catch (JOSEException | ParseException e) {
            System.err.println(e.getMessage());
        }
        return claims;
    }

    private Date getExpirationDateFromToken(String token) {
        JWTClaimsSet claims = getClaimsFromToken(token);
        Date expiration = claims.getExpirationTime();
        return expiration;
    }

	public boolean verifyToken(String token) {
		JWTClaimsSet claimsSet = getClaimsFromToken(token);
		Date expirationDate = claimsSet.getExpirationTime();
		Date currentDate = new Date();
		return expirationDate.after(currentDate);
	}

   // public String getUsernameFromToken(String token) {
   //     String username = null;
   //     try {
   //         JWTClaimsSet claims = getClaimsFromToken(token);
   //         username = claims.getStringClaim("username");
   //     } catch (ParseException e) {
   //         System.err.println(e.getMessage());
   //     }
   //     return username;
   // }

	public String getUsernameFromToken(String token) {
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

    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Boolean validateTokenLogin(String token) {
        if (token == null || token.trim().length() == 0) {
            return false;
        }
        String username = getUsernameFromToken(token);

        return !(username == null || username.isEmpty() || isTokenExpired(token));
    }
}
