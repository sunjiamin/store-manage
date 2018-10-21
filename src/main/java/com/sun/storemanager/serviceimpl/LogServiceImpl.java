package com.sun.storemanager.serviceimpl;

import com.sun.storemanager.dao.LogDao;
import com.sun.storemanager.entity.Log;
import com.sun.storemanager.service.LogService;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志接口实现
 * @author sunjiamin
 */
@Slf4j
@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public LogDao getRepository() {
        return logDao;
    }

    @Override
    public Page<Log> searchLog(String key, Pageable pageable) {

        return logDao.findAll(new Specification<Log>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Log> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> requestUrlField = root.get("requestUrl");
                Path<String> requestTypeField = root.get("requestType");
                Path<String> requestParamField = root.get("requestParam");
                Path<String> usernameField = root.get("username");
                Path<String> ipField = root.get("ip");
                Path<String> ipInfoField = root.get("ipInfo");

                List<Predicate> list = new ArrayList<Predicate>();

                //模糊搜素
                if(StrUtil.isNotBlank(key)){
                    Predicate p1 = cb.like(requestUrlField,'%'+key+'%');
                    Predicate p2 = cb.like(requestTypeField,'%'+key+'%');
                    Predicate p3 = cb.like(requestParamField,'%'+key+'%');
                    Predicate p4 = cb.like(usernameField,'%'+key+'%');
                    Predicate p5 = cb.like(ipField,'%'+key+'%');
                    Predicate p6 = cb.like(ipInfoField,'%'+key+'%');
                    list.add(cb.or(p1,p2,p3,p4,p5,p6));
                }

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }

    @Override
    public void deleteAll() {

        logDao.deleteAll();
    }
}
