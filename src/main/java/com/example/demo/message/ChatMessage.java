package com.example.demo.message;

import lombok.Data;

@Data
public class ChatMessage{
	private String receiver;
	private String content;
	private String type;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
//	public static void main(String[] args) {
//		ChatMessage chatMessage = new ChatMessage();
//		chatMessage.setContent("chat");
//		chatMessage.setSender("qwe");
//		chatMessage.setReceiver("zxc");
//		chatMessage.setSendTime("2018-1-31");
//		System.out.println(JSON.toJSON(chatMessage));
//	}
}
