package com.example.springjpa.server.impl;

import com.example.springjpa.compoment.BaseServiceImpl;
import com.example.springjpa.dao.UserDao;
import com.example.springjpa.entity.User;
import com.example.springjpa.server.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Describe
 * @Author Corey
 * @Date 2018/7/11.
 */
@Transactional(rollbackFor = RuntimeException.class)
@Service
public class UserServiceImpl extends BaseServiceImpl<User,Integer> implements UserService {
    @Resource
    private UserDao userDao;

    public User findById(){
        return userDao.getUser(1);
    }
}
