package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	public boolean save(User user){
		userMapper.insert(user);
		return true;
	}
	
	public User selectByUsername(String username){
		return userMapper.selectByUsername(username);
	}
	
	
}
