package com.example.springjpa.dao;

import com.example.springjpa.compoment.BaseDao;
import com.example.springjpa.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Describe
 * @Author Corey
 * @Date 2018/7/11.
 */
@Repository
public interface UserDao extends BaseDao<User, Integer> {

    User findById(Integer id);

    @Query(value = "select * from user where id=?1", nativeQuery = true)
    User getUser(Integer id);
    @Query(value = "select * from user limit ?1,?2", nativeQuery = true)
    List<User> findPage(Integer pageNum, Integer pageSize);
}
