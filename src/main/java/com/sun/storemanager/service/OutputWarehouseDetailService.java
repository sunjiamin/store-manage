package com.sun.storemanager.service;

import com.github.pagehelper.IPage;
import com.sun.storemanager.base.BaseService;
import com.sun.storemanager.common.vo.PageVo;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.entity.InputWarehouseDetail;
import com.sun.storemanager.entity.OutputWarehouseDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * 商品出存详细表接口
 * @author sunjiamin
 */
public interface OutputWarehouseDetailService extends BaseService<OutputWarehouseDetail,Integer> {

    Page<OutputWarehouseDetail> findByCondition(OutputWarehouseDetail outputWarehouseDetail, SearchVo searchVo, Pageable initPage);

    List<OutputWarehouseDetail>  findByCreateTimeBetween(Date start, Date end);

    com.baomidou.mybatisplus.plugins.Page<OutputWarehouseDetail> findByPage(OutputWarehouseDetail outputWarehouseDetail, SearchVo searchVo, PageVo pageVo);


}