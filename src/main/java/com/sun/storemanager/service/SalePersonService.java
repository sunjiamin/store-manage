package com.sun.storemanager.service;

import com.sun.storemanager.base.BaseService;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.entity.SalePerson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 销售员接口
 * @author sunjiamin
 */
public interface SalePersonService extends BaseService<SalePerson,String> {

    Page<SalePerson> findByCondition(SalePerson salePerson, SearchVo searchVo, Pageable initPage);
}