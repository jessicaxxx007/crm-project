package com.yjx.dao;

import com.yjx.base.BaseMapper;
import com.yjx.vo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User,Integer> {
    //通过用户名查找对应的用户记录  返回用户对象
    User queryUserByUserName(String userName);
    //查询所有有效用户
    List<User> queryAllUsers();
}
