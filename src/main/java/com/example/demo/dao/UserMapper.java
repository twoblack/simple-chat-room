package com.example.demo.dao;

import com.example.demo.model.User;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UserMapper extends Mapper<User>, MySqlMapper<User>{

    int insert(User record);

	User selectByUsername(String username);
}