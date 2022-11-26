package com.example.testTask.repository;

import com.example.testTask.entity.AppUser;
import com.example.testTask.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	List<Message> findAllByAppUser (AppUser user);
//	List<Message> findAllByMessagesUserId (Long messagesUserId);
	List<Message> findTopByAppUser (AppUser user);
//	List<Message> findTopByMessagesUserId (Long messagesUserId);
	List<Message> findFirst10ByAppUser (AppUser user);
}
