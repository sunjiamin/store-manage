package com.sun.storemanager.dao;

import com.sun.storemanager.base.BaseDao;
import com.sun.storemanager.entity.InputWarehouseDetail;
import com.sun.storemanager.entity.OutputWarehouseDetail;

import java.util.Date;
import java.util.List;

/**
 * 商品出存详细表数据处理层
 * @author sunjiamin
 */
public interface OutputWarehouseDetailDao extends BaseDao<OutputWarehouseDetail,Integer> {

    List<OutputWarehouseDetail>  findByCreateTimeBetween(Date start, Date end);

}