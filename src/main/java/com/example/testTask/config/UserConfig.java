package com.example.testTask.config;

import com.example.testTask.entity.AppUser;
import com.example.testTask.entity.Message;
import com.example.testTask.repository.MessageRepository;
import com.example.testTask.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Prepare two users for DB and two messages.
 */
@Configuration
public class UserConfig {

	@Bean
	CommandLineRunner userRun(UserRepository userRepository, MessageRepository messageRepository) {
		return args -> {
// add two default users to DB
			AppUser user1 = new AppUser("user1", "pass1");
			AppUser user2 = new AppUser("user2", "pass2");
			userRepository.saveAll(List.of(user1, user2));
// add two messages for user1
			Message msg0101 = new Message("message 01 01", user1);
			Message msg0201 = new Message("message 02 01", user1);
			messageRepository.saveAll(List.of(msg0101, msg0201));
		};
	}

}
