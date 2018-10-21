package com.sun.storemanager.service;

import com.sun.storemanager.base.BaseService;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 客户接口
 * @author sunjiamin
 */
public interface CustomerService extends BaseService<Customer,String> {

    Page<Customer> findByCondition(Customer customer, SearchVo searchVo, Pageable initPage);

}