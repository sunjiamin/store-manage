package com.sun.storemanager.dao;

import com.sun.storemanager.base.BaseDao;
import com.sun.storemanager.entity.InputWarehouseDetail;

import java.util.Date;
import java.util.List;

/**
 * 商品入存详细表数据处理层
 * @author sunjiamin
 */
public interface InputWarehouseDetailDao extends BaseDao<InputWarehouseDetail,String> {

    List<InputWarehouseDetail>  findByCreateTimeBetween(Date start,Date end);
}