package com.example.testTask.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Data layer
@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
}
