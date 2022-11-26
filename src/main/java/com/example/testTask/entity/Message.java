package com.example.testTask.entity;

import javax.persistence.*;

/**
 * Data layer entity for user`s messages.
 * messageId, messageText, message_user_id
 */
@Entity
@Table
public class Message {
	@Id
	@SequenceGenerator(name = "message_sequence", sequenceName = "message_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_sequence")
	private Long messageId;
	private String messageText;
	@ManyToOne
	@JoinColumn(name = "message_user_id", referencedColumnName = "userId")
	private AppUser appUser;

	public Message() {
	}

	public Message(String messageText, AppUser appUser) {
		this.messageText = messageText;
		this.appUser = appUser;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	@Override
	public String toString() {
		return "Message{" +
				", messageText='" + messageText + '\'' +
				'}';
	}
}
