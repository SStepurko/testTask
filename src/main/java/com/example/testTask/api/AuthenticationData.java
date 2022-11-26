package com.example.testTask.api;


/**
 * Class to map authentication request with JSON body { name: "name, password: "password" }
 */
public class AuthenticationData {
	private String name;
	private String password;

//	default constructors;
	public AuthenticationData() {
	}

	public AuthenticationData(String name, String password) {
		this.name = name;
		this.password = password;
	}

//	default getter and setter methods
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
