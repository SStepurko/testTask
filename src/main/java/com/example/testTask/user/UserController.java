package com.example.testTask.user;

import com.example.testTask.PostData;
import com.example.testTask.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping
	public void messageController(@RequestBody PostData postData) {
		userService.addNewMessage(postData);
	}

	// test GET point
	@GetMapping
	public List<AppUser> getUsers() {
		return userService.getUsers();
	}

}
