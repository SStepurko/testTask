package com.example.testTask.user;

public class User {
	private Long id;
	private String name;
	private String password;
	private String message;

	public User() {
	}

	public User(Long id, String name, String password, String message) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.message = message;
	}

	public User(String name, String password, String message) {
		this.name = name;
		this.password = password;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", message='" + message + '\'' +
				'}';
	}
}
