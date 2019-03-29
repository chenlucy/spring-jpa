package com.example.springjpa.server.impl;

import com.example.springjpa.compoment.BaseServiceImpl;
import com.example.springjpa.dao.UserDao;
import com.example.springjpa.entity.User;
import com.example.springjpa.server.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Override
    public List<User> findPage(Integer pageNum, Integer pageSize) {

        List<User> list = new ArrayList<>();

        return findList(list,pageNum,pageSize);
    }
    public List<User> findList(List<User> list,Integer pageNum, Integer pageSize) {
        List<User> list1 = userDao.findPage(pageNum,pageSize);
        list.addAll(list1);
        if(list1.size()==pageSize){
            this.findList(list,pageNum+pageSize,pageSize);
        }
        return list;
    }
}
