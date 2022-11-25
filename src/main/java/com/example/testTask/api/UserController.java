package com.example.testTask.api;

import com.example.testTask.entity.AppUser;
import com.example.testTask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
	public ResponseEntity messageController(@RequestBody PostData postData) {

		Long currentUserId = userService.getUserId(postData.getName());

		if (Objects.equals(postData.getMessage(), "history 10")) {
				return ResponseEntity.ok(userService.getLastMessages(currentUserId));
		} else {
			userService.addNewMessage(postData.getMessage(), currentUserId);
			return ResponseEntity.ok("added");
		}

	}

	// test GET point
	@GetMapping
	public List<AppUser> getUsers() {
		return userService.getAllUsers();
	}

}
