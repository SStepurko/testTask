package com.example.testTask.api;

import com.example.testTask.security.JwtImpl;
import com.example.testTask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Request controller for 2 endpoints.
 * "/api/authenticate" for authentication with json request { name: "name, password: "password" }
 * "/api/message" for post requests with JSON body { name: "name, message: "message" }
 */
// API layer
@RestController
@RequestMapping(path = "/api")
public class UserController {

	//	Dependency Injection
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JwtImpl jwtImpl;

	@Autowired
	public UserController(UserService userService, AuthenticationManager authenticationManager, JwtImpl jwtImpl) {
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.jwtImpl = jwtImpl;
	}

	/**
	 * ENDPOINT for message control
	 * @param postData json with "name" and "message"
	 * @return last 10 messages or 200ok for add new message
	 * @throws Exception incorrect request
	 */
	@PostMapping(value = "/message")
	public ResponseEntity messageController(@RequestBody PostData postData) throws Exception {
		try {
			if (Objects.equals(postData.getMessage(), "history 10")) {
//				return last 10 messages if message was "history 10"
				return ResponseEntity.ok(userService.getLastMessages(postData.getName()));
			} else {
//				save any message
				userService.addNewMessage(postData.getMessage(), postData.getName());
				return ResponseEntity.ok("added");
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Incorrect request");
		}
	}

	/**
	 * ENDPOINT for authentication
	 * @param authenticationData json with "name" and "password"
	 * @return jwt token
	 * @throws Exception incorrect credentials
	 */
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticateToken(@RequestBody AuthenticationData authenticationData) throws Exception {
//		Check username and password from POST data
		try {
			authenticationManager
					.authenticate(
							new UsernamePasswordAuthenticationToken(authenticationData.getName(), authenticationData.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect name or password");
		}
//		generate JWT token and send it back
		final UserDetails userDetails = userService.loadUserByUsername(authenticationData.getName());
		final String jwt = jwtImpl.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
