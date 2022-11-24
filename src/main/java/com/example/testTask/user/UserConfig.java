package com.example.testTask.user;

import com.example.testTask.entity.AppUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

	@Bean
	CommandLineRunner commandLineRunner(UserRepository repository) {
		return args -> {
//			add two default users to DB
			AppUser user1 = new AppUser("user1", "pass1", "msg1");
			AppUser user2 = new AppUser("user2", "pass2", "msg2");
			repository.saveAll(List.of(user1, user2));
		};
	}
}
