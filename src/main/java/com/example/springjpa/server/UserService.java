package com.example.springjpa.server;

import com.example.springjpa.compoment.BaseService;
import com.example.springjpa.entity.User;

import java.util.List;

/**
 * @Describe
 * @Author Corey
 * @Date 2018/7/11.
 */
public interface UserService extends BaseService<User,Integer>{
    User findById();
    List<User> findPage(Integer pageNum,Integer pageSize);
}
