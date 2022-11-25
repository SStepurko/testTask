package com.example.testTask.service;

import com.example.testTask.entity.AppUser;
import com.example.testTask.entity.Message;
import com.example.testTask.repository.MessageRepository;
import com.example.testTask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Service layer
@Component
public class UserService implements UserDetailsService {

	//	Dependency injection
	private final UserRepository userRepository;
	private final MessageRepository messageRepository;
	@Autowired
	public UserService(UserRepository userRepository, MessageRepository messageRepository) {
		this.userRepository = userRepository;
		this.messageRepository = messageRepository;
	}

	public List<AppUser> getAllUsers() {
		return userRepository.findAll();
	}

	public Long getUserId(String name) {
		Optional<AppUser> appUserByName = userRepository.findAppUserByName(name);
		if (appUserByName.isEmpty()) {
			throw new IllegalStateException("can't find user");
		}
		return appUserByName.get().getUser_id();
	}

	public List<Message> getLastMessages(Long userId) {
		List<Message> allByMessagesUserId = messageRepository.findAllByMessagesUserId(userId);
		return allByMessagesUserId;
	}

//	public void addNewMessage(PostData postData) {
//		Optional<AppUser> userByName = userRepository.findAppUserByName(postData.getName());
////		List<AppUser> userByName = userRepository.findByName(postData.getName());
//		List<Message> allByMessagesUserId = messageRepository.findAllByMessagesUserId(userByName.get().getUser_id());
//		System.out.println(userByName);
//		System.out.println(allByMessagesUserId);
//
////		System.out.println(postData.getMessage());
//	}

	public void addNewMessage(String message, Long id) {
		messageRepository.save(new Message(message, id));
	}


//	method for user detail service for security
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AppUser> appUser = userRepository.findAppUserByName(username);
		if (appUser.isEmpty()) {
			throw new UsernameNotFoundException("User not found in DB");
		}
		String name = appUser.get().getName();
		String pass = appUser.get().getPassword();
		return new org.springframework.security.core.userdetails.User(name, pass, new ArrayList<>());
	}
}
