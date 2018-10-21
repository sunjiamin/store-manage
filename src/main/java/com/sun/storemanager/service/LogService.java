package com.sun.storemanager.service;


import com.sun.storemanager.base.BaseService;
import com.sun.storemanager.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 日志接口
 * @author sunjiamin
 */
public interface LogService extends BaseService<Log,String> {

    /**
     * 日志搜索
     * @param key
     * @param pageable
     * @return
     */
    Page<Log> searchLog(String key, Pageable pageable);

    /**
     * 删除所有
     */
    void deleteAll();
}
