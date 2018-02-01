package com.example.demo.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.message.BaseMessage;
import com.example.demo.message.ChatMessage;
import com.example.demo.utils.Constants;

@Controller
public class ChatController {
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@MessageMapping("/all")
	@SendTo("/topic/all")
	public String all(Principal principal,String message){
		BaseMessage baseMessage = new BaseMessage();
		baseMessage.setType(Constants.TO_ALL);
		baseMessage.setContent(message);
		baseMessage.setSender(principal.getName());
		baseMessage.setSendTime(format.format(new Date()));
		return JSON.toJSONString(baseMessage);
	}
	
	@MessageMapping("/chat")
	public void chat(Principal principal,String message){
		ChatMessage chatMessage = JSON.parseObject(message,ChatMessage.class);
		BaseMessage baseMessage = new BaseMessage();
		baseMessage.setType(Constants.TO_ONE);
		baseMessage.setSender(principal.getName());
		System.out.println(principal.getName());
		baseMessage.setContent(chatMessage.getContent());
		baseMessage.setSendTime(format.format(new Date()));
		template.convertAndSendToUser(chatMessage.getReceiver(), "/topic/chat", JSON.toJSONString(baseMessage));
	}
}
