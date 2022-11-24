package com.example.testTask.user;

import com.example.testTask.PostData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// Service layer
@Component
public class UserService {

	//	Dependency injection
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<AppUser> getUsers() {
		return userRepository.findAll();
	}

	public void addNewMessage(PostData postData) {
		System.out.println(postData.getMessage());
	}
}
