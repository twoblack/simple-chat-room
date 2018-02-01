package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.model.User;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import com.example.demo.model.Relation;

public interface RelationMapper extends Mapper<User>, MySqlMapper<User>{
	int deleteByPrimaryKey(Integer id);
	int insert(Relation record);
	int insertSelective(Relation record);
	Relation selectByPrimaryKey(Integer id);
	int updateByPrimaryKeySelective(Relation record);
	int updateByPrimaryKey(Relation record);
	List<String> getFriendsByUsername(String username);
	void addFriend(@Param("username")String username,@Param("friend")String friend);
}
