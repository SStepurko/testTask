package com.example.testTask.repository;

import com.example.testTask.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Data layer
@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
	Optional<AppUser> findAppUserByName(String name);
}
