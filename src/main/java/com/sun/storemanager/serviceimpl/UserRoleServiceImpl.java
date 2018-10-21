package com.sun.storemanager.serviceimpl;

import com.sun.storemanager.dao.UserRoleDao;
import com.sun.storemanager.entity.UserRole;
import com.sun.storemanager.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户角色接口实现
 * @author sunjiamin
 */
@Slf4j
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public UserRoleDao getRepository() {
        return userRoleDao;
    }

    @Override
    public List<UserRole> findByRoleId(String roleId) {
        return userRoleDao.findByRoleId(roleId);
    }

    @Override
    public void deleteByUserId(String userId) {
        userRoleDao.deleteByUserId(userId);
    }
}
