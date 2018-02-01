package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.security.UserPrincipal;
import com.example.demo.service.RelationService;
import com.example.demo.vo.BaseUser;

@Controller
public class PageController {
	
	@Autowired
	private RelationService relationService;
	
	@GetMapping(value="/login")
	public String login(){
		return "login";
	}
	@GetMapping(value="/register")
	public String register(){
		return "register";
	}
	
	@GetMapping(value="/chat")
	public String chat(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model){
		model.addAttribute("user", userPrincipal);
		List<BaseUser> friends = relationService.getFriendsByUsername(userPrincipal.getUsername());
		model.addAttribute("friends", friends);
		return "chat";
	}
}
