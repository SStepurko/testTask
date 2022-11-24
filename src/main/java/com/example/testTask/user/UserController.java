package com.example.testTask.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// API layer
@RestController
@RequestMapping(path = "api/users")
public class UserController {

//	Dependency Injection
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}


	@GetMapping
	public List<AppUser> getUsers() {
		return userService.getUsers();
	}

}
