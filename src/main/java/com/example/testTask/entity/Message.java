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

	@ManyToOne
	@JoinColumn(name = "message_user_id", referencedColumnName = "userId")
	private AppUser appUser;
//	private Long messagesUserId;

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

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Long getMessageId() {
		return messageId;
	}

//	public Long getMessagesUserId() {
//		return messagesUserId;
//	}

//	public void setMessagesUserId(Long messagesUserId) {
//		this.messagesUserId = messagesUserId;
//	}

	@Override
	public String toString() {
		return "Message{" +
				"messageId=" + messageId +
				", messageText='" + messageText + '\'' +
				'}';
	}
}
