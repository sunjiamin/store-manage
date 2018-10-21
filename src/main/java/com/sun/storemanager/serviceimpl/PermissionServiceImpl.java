package com.sun.storemanager.serviceimpl;

import com.sun.storemanager.dao.PermissionDao;
import com.sun.storemanager.entity.Permission;
import com.sun.storemanager.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限接口实现
 * @author Exrick
 */
@Slf4j
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public PermissionDao getRepository() {
        return permissionDao;
    }

    @Override
    public List<Permission> findByLevelOrderBySortOrder(Integer level) {

        return permissionDao.findByLevelOrderBySortOrder(level);
    }

    @Override
    public List<Permission> findByParentIdOrderBySortOrder(String parentId) {

        return permissionDao.findByParentIdOrderBySortOrder(parentId);
    }

    @Override
    public List<Permission> findByLevelAndTypeOrderBySortOrder(Integer level, Integer type) {

        return permissionDao.findByLevelAndTypeOrderBySortOrder(level, type);
    }

    @Override
    public List<Permission> findByTypeAndParentIdOrderBySortOrder(Integer type, String parentId) {

        return permissionDao.findByTypeAndParentIdOrderBySortOrder(type, parentId);
    }
}