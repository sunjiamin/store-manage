package com.sun.storemanager.dao;

import com.sun.storemanager.base.BaseDao;
import com.sun.storemanager.entity.Role;

import java.util.List;

/**
 * 角色数据处理层
 * @author sunjiamin
 */
public interface RoleDao extends BaseDao<Role,String> {

    /**
     * 获取默认角色
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);
}
