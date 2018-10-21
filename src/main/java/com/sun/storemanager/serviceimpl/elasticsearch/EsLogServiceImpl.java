package com.sun.storemanager.serviceimpl.elasticsearch;

//import com.sun.storemanager.dao.elasticsearch.EsLogDao;
import com.sun.storemanager.entity.elasticsearch.EsLog;
import com.sun.storemanager.service.elasticsearch.EsLogService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sunjiamin
 */
//@Service
@Transactional
public class EsLogServiceImpl implements EsLogService {

    //@Autowired
    //private EsLogDao logDao;

    @Override
    public EsLog saveLog(EsLog esLog) {

        //return logDao.save(esLog);
        return null;
    }

    @Override
    public void deleteLog(String id) {

        //logDao.deleteById(id);
    }

    @Override
    public void deleteAll() {

        //logDao.deleteAll();
    }

    @Override
    public Page<EsLog> getLogList(Pageable pageable) {

        //return logDao.findAll(pageable);
        return null;
    }

    @Override
    public Page<EsLog> searchLog(String key, Pageable pageable) {

        //多字段搜索
        //QueryBuilder qb = QueryBuilders.multiMatchQuery(key, "requestUrl", "requestType","requestParam","username","ip","ipInfo");
        //return logDao.search(qb, pageable);
        return null;
    }
}
