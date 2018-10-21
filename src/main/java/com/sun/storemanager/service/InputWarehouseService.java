package com.sun.storemanager.service;

import com.sun.storemanager.base.BaseService;
import com.sun.storemanager.entity.InputWarehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品入存主表接口
 * @author sunjiamin
 */
public interface InputWarehouseService extends BaseService<InputWarehouse,Integer> {


    public String insertInputWareHouse(InputWarehouse inputWarehouse);


}