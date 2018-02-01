package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.model.User;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface RelationMapper extends Mapper<User>, MySqlMapper<User>{
	
	List<String> getFriendsByUsername(String username);
	
	void addFriend(@Param("username")String username,@Param("friend")String friend);
	
}
