package com.sun.storemanager.serviceimpl.mybatis;

import com.sun.storemanager.dao.mapper.UserRoleMapper;
import com.sun.storemanager.entity.Role;
import com.sun.storemanager.entity.UserRole;
import com.sun.storemanager.service.mybatis.IUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunjiamin
 */
@Service
public class IUserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> findByUserId(String userId) {

        return userRoleMapper.findByUserId(userId);
    }
}
