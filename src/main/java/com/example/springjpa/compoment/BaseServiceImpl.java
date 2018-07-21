package com.example.springjpa.compoment;


import org.assertj.core.util.Lists;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Describe
 * @Author Corey
 * @Date 2018/7/11.
 */
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {
    @Resource
    private BaseDao<T, ID> baseDao;


    @Override
    public void save(T var1) {
        baseDao.save(var1);
    }

    @Override
    public void update(T var1) {
        baseDao.save(var1);
    }

    @Override
    public T getById(ID var1) {
        return baseDao.findOne(var1);
    }

    @Override
    public void delete(ID var1) {
        baseDao.delete(var1);
    }

    @Override
    public List<T> findAll() {
//        Iterable<T> geted = baseDao.findAll();
//        List<T> list = new ArrayList<T>();
//        1、方法一，采用迭代器实现遍历，放回list
//        Iterator<T> iterator = geted.iterator();
//        while (iterator.hasNext()){
//            list.add(iterator.next());
//        }
//        2、方法二，采用Lambda 实现遍历，返回list
//        geted.forEach(single ->{list.add(single);});
//        3、方法三，采用工具类Lists，返回list
//        List<T> list = Lists.newArrayList(geted);

        return Lists.newArrayList(baseDao.findAll());
    }


}
