package com.example.testTask;

import com.example.testTask.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class TestTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestTaskApplication.class, args);
	}

	@GetMapping
	public List<User> getUsers() {
		return List.of(
				new User(1L, "user1", "pass1", "msg1"),
				new User(2L, "user2", "pass2", "msg2")
		);
	}

}
