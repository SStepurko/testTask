package com.example.testTask.entity;

import javax.persistence.*;

/**
 * Data layer entity for users.
 * userId, name, password
 */
@Entity(name = "AppUser")
@Table(name = "app_user")
public class AppUser {
	@Id
	@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
	private Long userId;
	private String name;
	private String password;

	public AppUser() {
	}

	public AppUser(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long id) {
		this.userId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + userId +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
