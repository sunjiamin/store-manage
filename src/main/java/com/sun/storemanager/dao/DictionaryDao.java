package com.sun.storemanager.dao;

import com.sun.storemanager.base.BaseDao;
import com.sun.storemanager.entity.Dictionary;

import java.util.List;

/**
 * 字典表数据处理层
 * @author SunJiamin
 */
public interface DictionaryDao extends BaseDao<Dictionary,Integer> {


    List<Dictionary> findByType(String type);
}