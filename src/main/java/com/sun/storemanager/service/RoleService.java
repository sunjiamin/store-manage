package com.sun.storemanager.service;


import com.sun.storemanager.base.BaseService;
import com.sun.storemanager.entity.Role;

import java.util.List;

/**
 * 角色接口
 * @author sunjiamin
 */
public interface RoleService extends BaseService<Role,String> {

    /**
     * 获取默认角色
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);
}
