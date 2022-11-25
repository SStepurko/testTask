package com.example.testTask.user;

import com.example.testTask.entity.AppUser;
import com.example.testTask.entity.Message;
import com.example.testTask.repository.MessageRepository;
import com.example.testTask.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.List;

@Configuration
public class UserConfig {

	@Bean
	CommandLineRunner userRun(UserRepository repository) {
		return args -> {
//			add two default users to DB
			AppUser user1 = new AppUser("user1", "pass1");
			AppUser user2 = new AppUser("user2", "pass2");
			repository.saveAll(List.of(user1, user2));
		};
	}

	@Bean
	@DependsOn("userRun")
	CommandLineRunner messageRun(MessageRepository repository) {
		return args -> {
			Message msg0101 = new Message("message 01 01", 1L);
			Message msg0201 = new Message("message 02 01", 1L);
			repository.saveAll(List.of(msg0101, msg0201));
		};
	}
}
