package com.example.springjpa.dao;

import com.example.springjpa.compoment.BaseDao;
import com.example.springjpa.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @Describe
 * @Author Corey
 * @Date 2018/7/11.
 */
@Repository
public interface UserDao extends BaseDao<User, Integer> {
}
