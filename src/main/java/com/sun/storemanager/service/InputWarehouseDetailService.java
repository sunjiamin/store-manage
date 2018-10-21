package com.sun.storemanager.service;

import com.sun.storemanager.base.BaseService;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.entity.InputWarehouseDetail;
import com.sun.storemanager.entity.OutputWarehouseDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * 商品入存详细表接口
 * @author sunjiamin
 */
public interface InputWarehouseDetailService extends BaseService<InputWarehouseDetail,String> {

    Page<InputWarehouseDetail> findByCondition(InputWarehouseDetail inputWarehouseDetail, SearchVo searchVo, Pageable initPage);

    List<InputWarehouseDetail>  findByCreateTimeBetween(Date start, Date end);
}