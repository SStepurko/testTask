package com.example.testTask.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtImpl {
	private String SECRET = "mysecret";

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET).compact();
	}

//	realization of io.jsonwebtoken Claims class methods to get username and expiration date from token
	private Claims extractAllClaims(String token){
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
	}

//	method with Claims builtin functions, so we can use them
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public String extractUsername(String token){
			return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token){
		return extractClaim(token, Claims::getExpiration);
	}

	private Boolean isTokenExpired(String token){
		return extractExpiration(token).before(new Date());
	}

//	check username from token and expiration time of token
	public Boolean validateToken(String token, UserDetails userDetails){
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
