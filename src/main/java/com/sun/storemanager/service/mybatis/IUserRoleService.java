package com.sun.storemanager.service.mybatis;

import com.sun.storemanager.entity.Role;
import com.sun.storemanager.entity.UserRole;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author sunjiamin
 */
@CacheConfig(cacheNames = "userRole")
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 通过用户id获取
     * @param userId
     * @return
     */
    @Cacheable(key = "#userId")
    List<Role> findByUserId(@Param("userId") String userId);
}
