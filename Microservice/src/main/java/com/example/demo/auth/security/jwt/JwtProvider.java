package com.example.demo.auth.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.auth.security.service.MyUserPrincipal;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
@Component
public class JwtProvider {

	@Value("${app.jwtSecret}")
	private String jwtSecret;
	@Value("${app.jwtExpiration}")
	private int jwtExpiration;

	public String generateJwtToken(Authentication authentication) {
		MyUserPrincipal userPrincipal = (MyUserPrincipal) authentication.getPrincipal();

		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public Boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			System.out.println("Invalid Jwt Signature -> Message : " + e);
		} catch (MalformedJwtException e) {
			System.out.println("Invalid Jwt Signature -> Message : " + e);

		} catch (ExpiredJwtException e) {
			System.out.println("Invalid Jwt Signature -> Message : " + e);

		} catch (UnsupportedJwtException e) {
			System.out.println("Invalid Jwt Signature -> Message : " + e);

		} catch (IllegalArgumentException e) {
			System.out.println("Invalid Jwt Signature -> Message : " + e);

		}
		return false;
	}

	public String getUserNameFromJwtToken(String Token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(Token).getBody().getSubject();
	}

}
