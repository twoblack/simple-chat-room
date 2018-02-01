package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.User;
import com.example.demo.security.UserPrincipal;
import com.example.demo.service.RelationService;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	RelationService relationService;
	
	@PostMapping(value="/register")
	@ResponseBody
	public boolean register(HttpServletRequest request){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		User user = new User(username,password,nickname);
		if(null != userService.selectByUsername(user.getUsername())){
			return false;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setRegisterData(dateFormat.format(new Date()));
		return userService.save(user);
	}
	
	@PostMapping(value="/addfriend")
	@ResponseBody
	public String addfriend(@AuthenticationPrincipal UserPrincipal userPrincipal,@RequestParam String friend){
		return relationService.addFriend(userPrincipal.getUsername(),friend);
	}	
	
}
