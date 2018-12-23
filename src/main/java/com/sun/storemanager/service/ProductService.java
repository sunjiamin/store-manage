package com.sun.storemanager.service;

import com.sun.storemanager.base.BaseService;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品表接口
 * @author sunjiamin
 */
public interface ProductService extends BaseService<Product,String> {

    /**
     * 多条件分页获取
     * @param product
     * @param searchVo
     * @param initPage
     * @return
     */
    Page<Product> findByCondition(Product product, SearchVo searchVo, Pageable initPage);

    Product findById(String id);
}