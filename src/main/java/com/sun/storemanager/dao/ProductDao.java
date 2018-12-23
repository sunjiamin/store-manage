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

    /**
     * where x.productName like ?1
     * @param productName
     * @return
     */
    List<Product> findByProductNameContaining(String productName);

    List<Product> findByProductCodeContaining(String productCode);

    List<Product> findByProductSpecContaining(String productSpec);

    List<Product> findByProductNameContainingAndProductCodeContaining(String productName,String productCode);

    List<Product> findByProductNameContainingAndProductSpecContaining(String productName,String productSpec);

    List<Product> findByProductCodeContainingAndProductSpecContaining(String productCode,String productSpec);

    List<Product> findByProductNameContainingAndProductCodeContainingAndProductSpecContaining(String productName,String productCode,String productSpec);







}