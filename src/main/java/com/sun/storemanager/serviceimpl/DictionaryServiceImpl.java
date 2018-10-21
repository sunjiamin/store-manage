package com.sun.storemanager.serviceimpl;

import com.sun.storemanager.dao.DictionaryDao;
import com.sun.storemanager.entity.Dictionary;
import com.sun.storemanager.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典表接口实现
 * @author SunJiamin
 */
@Slf4j
@Service
@Transactional
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryDao dictionaryDao;

    @Override
    public DictionaryDao getRepository() {
        return dictionaryDao;
    }

    @Override
    public List<Dictionary> findByType(String type) {
        return dictionaryDao.findByType(type);
    }
}