package com.example.demo.dao;

import com.example.demo.model.User;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UserMapper extends Mapper<User>, MySqlMapper<User>{






    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);


    User selectByPrimaryKey(Integer id);



    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User selectByUsername(String username);
}