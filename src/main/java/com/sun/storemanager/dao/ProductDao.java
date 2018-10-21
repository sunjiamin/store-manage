package com.sun.storemanager.dao;

import com.sun.storemanager.base.BaseDao;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 商品表数据处理层
 * @author sunjiamin
 */
public interface ProductDao extends BaseDao<Product,String> {


//    @Query("select t from TestPage t,TestPageRef r where t.id = r.testId and r.enabled = :enabled")
//    Page<Product> findList(Product product, SearchVo searchVo, Pageable pageable);



}