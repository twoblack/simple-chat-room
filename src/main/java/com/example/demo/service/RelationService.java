package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RelationMapper;
import com.example.demo.model.User;
import com.example.demo.vo.BaseUser;

@Service
public class RelationService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RelationMapper relationMapper;
	
	public List<BaseUser> getFriendsByUsername(String username){
		List<String> list = relationMapper.getFriendsByUsername(username);
		List<BaseUser> friends = new ArrayList<>();
		for(String l:list){
			BaseUser bu = new BaseUser(l);
			User user = userService.selectByUsername(l);
			bu.setNickName(user.getNickName());
			friends.add(bu);
		}
		return friends;
	}
	
	public String addFriend(String username, String friend){
		if(null == userService.selectByUsername(friend)){
			System.out.println("用户不存在");
			return "用户不存在！";
		}
		if(username.equals(friend)){
			System.out.println("不能添加本人!");
			return "不能添加本人！";
		}
		if(relationMapper.getFriendsByUsername(username)!=null&&relationMapper.getFriendsByUsername(username).contains(friend)){
			System.out.println("已是好友");
			return "已是好友！";
		}
		relationMapper.addFriend(username,friend);
		relationMapper.addFriend(friend, username);
		return "添加成功！";
	}
	
}
