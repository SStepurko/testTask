package com.example.testTask.user;

import org.springframework.stereotype.Component;

import java.util.List;

// Service layer
@Component
public class UserService {
	public List<User> getUsers() {
		return List.of(
				new User(1L, "user1", "pass1", "msg1"),
				new User(2L, "user2", "pass2", "msg2")
		);
	}
}
