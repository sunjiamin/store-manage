package com.sun.storemanager.dao;

import com.sun.storemanager.base.BaseDao;
import com.sun.storemanager.entity.ProductStock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 商品库存表数据处理层
 * @author sunjiamin
 */
public interface ProductStockDao extends BaseDao<ProductStock,String> {

    ProductStock  findByProductId(String productId);


//    @Query(value = "select t1  from t_product_stock t1 left join t_product t2 on t1.product_id=t2.id")
//    List<ProductStock> findAllStock();

}