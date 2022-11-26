package com.example.testTask.api;

/**
 * Class to map requests with JSON body { name: "name, message: "message" }
 */
public class PostData {
	private String name;
	private String message;

	public PostData(String name, String message) {
		this.name = name;
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
