package com.sun.storemanager.dao;

import com.sun.storemanager.base.BaseDao;
import com.sun.storemanager.entity.UserRole;

import java.util.List;

/**
 * 用户角色数据处理层
 * @author sunjiamin
 */
public interface UserRoleDao extends BaseDao<UserRole,String> {

    /**
     * 通过roleId查找
     * @param roleId
     * @return
     */
    List<UserRole> findByRoleId(String roleId);

    /**
     * 删除用户角色
     * @param userId
     */
    void deleteByUserId(String userId);
}
