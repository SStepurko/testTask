package com.example.testTask.entity;

import javax.persistence.*;

@Entity
@Table
public class Message {
	@Id
	@SequenceGenerator(name = "message_sequence", sequenceName = "message_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_sequence")
	private Long messageId;
	private String messageText;

	public Message() {
	}

	public Message(Long messageId, String messageText) {
		this.messageId = messageId;
		this.messageText = messageText;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Long getMessageId() {
		return messageId;
	}
}
