package com.example.testTask.repository;

import com.example.testTask.entity.AppUser;
import com.example.testTask.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Message repository with new findFirst10ByAppUser
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	List<Message> findFirst10ByAppUser(AppUser user);
}
