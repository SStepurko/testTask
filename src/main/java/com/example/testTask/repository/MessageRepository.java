package com.example.testTask.repository;

import com.example.testTask.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	public List<Message> findAllByMessagesUserId (Long messagesUserId);
}
