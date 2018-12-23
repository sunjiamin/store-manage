package com.sun.storemanager.dao.mapper;

import com.sun.storemanager.entity.Product;
import com.sun.storemanager.entity.ProductExample;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author sunjiamin
 * @date 2018-11-11 10:21
 */
public interface ProductMapper {


    List<Product> selectByExample(ProductExample example);

    List<Product> selectByCond(Map<String, Object> paramMap);
}
