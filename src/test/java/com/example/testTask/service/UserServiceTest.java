package com.example.testTask.service;

import com.example.testTask.entity.AppUser;
import com.example.testTask.entity.Message;
import com.example.testTask.repository.MessageRepository;
import com.example.testTask.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserServiceTest {

	@Autowired
	UserRepository userRepository;
	@Autowired
	MessageRepository messageRepository;

	AppUser testUser = new AppUser("test", "pass");

	@BeforeEach
	void setUp() {
		userRepository.save(testUser);
	}

	@AfterEach
	void tearDown() {
		messageRepository.deleteAll(messageRepository.findFirst10ByAppUser(testUser));
		userRepository.delete(testUser);
	}

	@Test
	void getLastMessages() {
		assertThat(messageRepository.findFirst10ByAppUser(testUser)).isEmpty();
		Message test = new Message("Test", testUser);
		messageRepository.save(test);
		List<Message> message = messageRepository.findFirst10ByAppUser(testUser);
		assertThat(message).hasSize(1).contains(test);
	}

	@Test
	void addNewMessage() {
		assertThat(messageRepository.findFirst10ByAppUser(testUser)).isEmpty();
		messageRepository.save(new Message("test2", testUser));
		assertThat(messageRepository.findFirst10ByAppUser(testUser)).hasSize(1);
	}

	@Test
	void loadUserByUsername() {
		Optional<AppUser> appUserByName = userRepository.findAppUserByName(testUser.getName());
		assertThat(appUserByName.isPresent()).isTrue();
	}
}