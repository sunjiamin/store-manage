package com.sun.storemanager.service;


import com.sun.storemanager.base.BaseService;
import com.sun.storemanager.entity.UserRole;

import java.util.List;

/**
 * 用户角色接口
 * @author sunjiamin
 */
public interface UserRoleService extends BaseService<UserRole,String> {

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
