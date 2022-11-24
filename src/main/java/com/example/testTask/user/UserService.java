package com.example.testTask.user;

import org.springframework.stereotype.Component;

import java.util.List;

// Service layer
@Component
public class UserService {
	public List<AppUser> getUsers() {
		return List.of(
				new AppUser(1L, "user1", "pass1", "msg1"),
				new AppUser(2L, "user2", "pass2", "msg2")
		);
	}
}
