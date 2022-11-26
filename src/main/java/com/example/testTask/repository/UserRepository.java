package com.example.testTask.repository;

import com.example.testTask.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Application user repository with findAppUserByName
 */
@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
	Optional<AppUser> findAppUserByName(String name);
}
