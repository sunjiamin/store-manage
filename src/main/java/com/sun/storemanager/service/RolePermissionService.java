package com.sun.storemanager.service;

import com.sun.storemanager.base.BaseService;
import com.sun.storemanager.entity.RolePermission;

import java.util.List;

/**
 * 角色权限接口
 * @author Exrick
 */
public interface RolePermissionService extends BaseService<RolePermission,String> {

    /**
     * 通过permissionId获取
     * @param permissionId
     * @return
     */
    List<RolePermission> findByPermissionId(String permissionId);

    /**
     * 通过roleId删除
     * @param roleId
     */
    void deleteByRoleId(String roleId);
}