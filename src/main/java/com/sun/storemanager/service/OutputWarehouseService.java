package com.sun.storemanager.service;

import com.sun.storemanager.base.BaseService;
import com.sun.storemanager.entity.OutputWarehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品出存主表接口
 * @author sunjiamin
 */
public interface OutputWarehouseService extends BaseService<OutputWarehouse,Integer> {

    String insertOutputWareHouse(OutputWarehouse outputWarehouse);
}