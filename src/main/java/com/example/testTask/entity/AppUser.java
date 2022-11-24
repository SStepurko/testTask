package com.example.testTask.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "app_user")
public class AppUser {
	@Id
	@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
	private Long user_id;
	@Column
//	TODO uniq nonNull
	private String name;
	private String password;
	//	TODO change to list
	private String message;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "user_id")
	private List<Message> messages;

	public AppUser() {
	}

	public AppUser(Long user_id, String name, String password, String message) {
		this.user_id = user_id;
		this.name = name;
		this.password = password;
		this.message = message;
	}

	public AppUser(String name, String password, String message) {
		this.name = name;
		this.password = password;
		this.message = message;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long id) {
		this.user_id = id;
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
				"id=" + user_id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", message='" + message + '\'' +
				'}';
	}
}
