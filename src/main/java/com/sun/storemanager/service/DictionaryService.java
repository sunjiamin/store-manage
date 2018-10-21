package com.sun.storemanager.service;

import com.sun.storemanager.base.BaseService;
import com.sun.storemanager.entity.Dictionary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 字典表接口
 * @author SunJiamin
 */
public interface DictionaryService extends BaseService<Dictionary,Integer> {

    List<Dictionary> findByType(String type);

}