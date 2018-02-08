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
	
	/**
	 * 群聊
	 * @param principal 当前用户
	 * @param message 接收到的客户端的消息
	 * @return 包装后的消息
	 */
	@MessageMapping("/all")//接收发送到  /{服务端接收地址的前缀}/all 地址的消息
	@SendTo("/topic/all")//将return的结果发送到  /topic/all 地址
	public String all(Principal principal,String message){
		BaseMessage baseMessage = new BaseMessage();
		baseMessage.setType(Constants.TO_ALL);
		baseMessage.setContent(message);
		baseMessage.setSender(principal.getName());
		baseMessage.setSendTime(format.format(new Date()));
		return JSON.toJSONString(baseMessage);
	}
	
	/**
	 * 群发图片
	 * @param principal
	 * @param message
	 * @return
	 */
	@MessageMapping("/pic/all")//接收发送到  /{服务端接收地址的前缀}/all 地址的消息
	@SendTo("/topic/pic/all")//将return的结果发送到  /topic/all 地址
	public String picAll(Principal principal,String message){
		return message;
	}
	
	/**
	 * 群发文件
	 * @param principal
	 * @param message
	 * @return
	 */
	@MessageMapping("/file/all")//接收发送到  /{服务端接收地址的前缀}/all 地址的消息
	@SendTo("/topic/file/all")//将return的结果发送到  /topic/all 地址
	public String fileAll(Principal principal,String message){
		return message;
	}
	
	/**
	 * 私聊
	 * @param principal
	 * @param message
	 */
	@MessageMapping("/chat")//接收发送到  /{服务端接收地址的前缀}/chat 地址的消息
	public void chat(Principal principal,String message){
		ChatMessage chatMessage = JSON.parseObject(message,ChatMessage.class);
		BaseMessage baseMessage = new BaseMessage();
		baseMessage.setType(Constants.TO_ONE);
		baseMessage.setSender(principal.getName());
		System.out.println(principal.getName());
		baseMessage.setContent(chatMessage.getContent());
		baseMessage.setSendTime(format.format(new Date()));
		
		//转发包装后的消息至/{user}/topic/chat地址
		template.convertAndSendToUser(chatMessage.getReceiver(), "/topic/chat", JSON.toJSONString(baseMessage));
	}
}
