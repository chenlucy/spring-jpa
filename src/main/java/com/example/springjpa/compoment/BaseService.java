package com.example.springjpa.compoment;

import java.io.Serializable;
import java.util.List;

/**
 * @Describe
 * @Author Corey
 * @Date 2018/7/11.
 */
public interface BaseService<T, ID extends Serializable> {

    void save(T var1);

    void update(T var1);

    T getById(ID var1);

    void delete(ID var1);

    List<T> findAll();
}
