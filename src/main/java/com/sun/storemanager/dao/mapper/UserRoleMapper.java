package com.sun.storemanager.dao.mapper;

import com.sun.storemanager.entity.Role;
import com.sun.storemanager.entity.UserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sunjiamin
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 通过用户id获取
     * @param userId
     * @return
     */
    List<Role> findByUserId(@Param("userId") String userId);
}
