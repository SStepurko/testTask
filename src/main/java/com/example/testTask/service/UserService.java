package com.example.testTask.service;

import com.example.testTask.PostData;
import com.example.testTask.entity.AppUser;
import com.example.testTask.entity.Message;
import com.example.testTask.repository.MessageRepository;
import com.example.testTask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

// Service layer
@Component
public class UserService {

	//	Dependency injection
	private final UserRepository userRepository;
	private final MessageRepository messageRepository;
	@Autowired
	public UserService(UserRepository userRepository, MessageRepository messageRepository) {
		this.userRepository = userRepository;
		this.messageRepository = messageRepository;
	}


	public List<AppUser> getUsers() {
		return userRepository.findAll();
	}

	public void addNewMessage(PostData postData) {
		Optional<AppUser> userByName = userRepository.findAppUserByName(postData.getName());
//		List<AppUser> userByName = userRepository.findByName(postData.getName());
		List<Message> allByMessagesUserId = messageRepository.findAllByMessagesUserId(userByName.get().getUser_id());
		System.out.println(userByName);
		System.out.println(allByMessagesUserId);

//		System.out.println(postData.getMessage());
	}
}
