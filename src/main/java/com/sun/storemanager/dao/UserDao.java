package com.sun.storemanager.dao;

import com.sun.storemanager.base.BaseDao;
import com.sun.storemanager.entity.User;

import java.util.List;

/**
 * 用户数据处理层
 * @author sunjiamin
 */
public interface UserDao extends BaseDao<User,String> {

    /**
     * 通过用户名和状态获取用户
     * @param username
     * @param status
     * @return
     */
    List<User> findByUsernameAndStatus(String username, Integer status);

    /**
     * 通过状态和类型获取用户
     * @param status
     * @param type
     * @return
     */
    List<User> findByStatusAndType(Integer status, Integer type);
}
