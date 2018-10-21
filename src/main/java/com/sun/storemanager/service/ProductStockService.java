package com.sun.storemanager.service;

import com.sun.storemanager.base.BaseService;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.entity.ProductStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品库存表接口
 * @author sunjiamin
 */
public interface ProductStockService extends BaseService<ProductStock,String> {


    Page<ProductStock> findByCondition(ProductStock productStock, SearchVo searchVo, Pageable initPage);
}