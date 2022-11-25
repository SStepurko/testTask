package com.example.testTask.api;

public class AuthenticationData {
	private String name;
	private String password;

	public AuthenticationData() {
	}

	public AuthenticationData(String name, String password) {
		this.name = name;
		this.password = password;
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
}
