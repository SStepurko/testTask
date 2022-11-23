package com.example.testTask.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// API layer
@RestController
@RequestMapping(path = "api/users")
public class UserController {

	@GetMapping
	public List<User> getUsers() {
		return List.of(
				new User(1L, "user1", "pass1", "msg1"),
				new User(2L, "user2", "pass2", "msg2")
		);
	}

}
