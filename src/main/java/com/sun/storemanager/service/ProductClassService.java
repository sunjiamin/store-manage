package com.sun.storemanager.service;

import com.sun.storemanager.base.BaseService;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.entity.ProductClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品类别表接口
 * @author sunjiamin
 */
public interface ProductClassService extends BaseService<ProductClass,String> {

    /**
     * 多条件分页获取
     * @param productClass
     * @param searchVo
     * @param initPage
     * @return
     */
    Page<ProductClass> findByCondition(ProductClass productClass, SearchVo searchVo, Pageable initPage);

}