package com.example.testTask.user;

import com.example.testTask.PostData;
import com.example.testTask.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
		Optional<AppUser> userByName = userRepository.findAppUserByName(postData.getName());
//		List<AppUser> userByName = userRepository.findByName(postData.getName());
		System.out.println(userByName);

//		System.out.println(postData.getMessage());
	}
}
