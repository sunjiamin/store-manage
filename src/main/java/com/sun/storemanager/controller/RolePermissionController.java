package com.sun.storemanager.controller;

import com.sun.storemanager.base.BaseController;
import com.sun.storemanager.entity.RolePermission;
import com.sun.storemanager.service.RolePermissionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Exrick
 */
@Slf4j
@RestController
@Api(description = "角色权限管理接口")
@RequestMapping("/store/rolePermission")
public class RolePermissionController extends BaseController<RolePermission, String> {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public RolePermissionService getService() {
        return rolePermissionService;
    }

}
