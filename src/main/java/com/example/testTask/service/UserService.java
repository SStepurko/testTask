package com.example.testTask.service;

import com.example.testTask.entity.AppUser;
import com.example.testTask.entity.Message;
import com.example.testTask.repository.MessageRepository;
import com.example.testTask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service to work with repository. Also implements UserDetailsService so we can use it in security section
 */
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

	public List<Message> getLastMessages(String name) {
		AppUser user = userRepository.findAppUserByName(name).get();
		List<Message> allByMessagesUserId = messageRepository.findFirst10ByAppUser(user);
		return allByMessagesUserId;
	}

	public void addNewMessage(String message, String name) {
		AppUser user = userRepository.findAppUserByName(name).get();
		messageRepository.save(new Message(message, user));
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
