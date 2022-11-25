package com.example.testTask.api;

import com.example.testTask.entity.AppUser;
import com.example.testTask.security.JwtImpl;
import com.example.testTask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

	@PostMapping(value = "/message")
	public ResponseEntity messageController(@RequestBody PostData postData) {

		Long currentUserId = userService.getUserId(postData.getName());

		if (Objects.equals(postData.getMessage(), "history 10")) {
			return ResponseEntity.ok(userService.getLastMessages(currentUserId));
		} else {
			userService.addNewMessage(postData.getMessage(), currentUserId);
			return ResponseEntity.ok("added");
		}

	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticateToken(@RequestBody AuthenticationData authenticationData) throws Exception {
		try {
			authenticationManager
					.authenticate(
							new UsernamePasswordAuthenticationToken(authenticationData.getName(), authenticationData.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect name or password");
		}
		final UserDetails userDetails = userService.loadUserByUsername(authenticationData.getName());
		final String jwt = jwtImpl.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}


	// test GET point
	@GetMapping
	public List<AppUser> getUsers() {
		return userService.getAllUsers();
	}

}
