package com.example.testTask.api;


/**
 * Class like ResponseEntity. Use it for response with JWT token
 */
public class AuthenticationResponse {
	//	JWT token
	private String jwt;

	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

}
