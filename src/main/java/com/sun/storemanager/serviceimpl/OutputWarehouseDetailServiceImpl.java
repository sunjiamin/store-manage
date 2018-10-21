package com.sun.storemanager.serviceimpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.dao.OutputWarehouseDetailDao;
import com.sun.storemanager.entity.InputWarehouseDetail;
import com.sun.storemanager.entity.OutputWarehouseDetail;
import com.sun.storemanager.service.OutputWarehouseDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品出存详细表接口实现
 * @author sunjiamin
 */
@Slf4j
@Service
@Transactional
public class OutputWarehouseDetailServiceImpl implements OutputWarehouseDetailService {

    @Autowired
    private OutputWarehouseDetailDao outputWarehouseDetailDao;

    @Override
    public OutputWarehouseDetailDao getRepository() {
        return outputWarehouseDetailDao;
    }

    @Override
    public Page<OutputWarehouseDetail> findByCondition(OutputWarehouseDetail outputWarehouseDetail, SearchVo searchVo, Pageable initPage) {
        return outputWarehouseDetailDao.findAll(new Specification<OutputWarehouseDetail>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<OutputWarehouseDetail> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<Date> createTimeField=root.get("createTime");

                Path<String > payStatusField=root.get("payStatus");

                List<Predicate> list = new ArrayList<Predicate>();


                if(outputWarehouseDetail.getPayStatus()!=null){
                    list.add(cb.equal(payStatusField, outputWarehouseDetail.getPayStatus()));
                }

                //创建时间
                if(StrUtil.isNotBlank(searchVo.getStartDate())&&StrUtil.isNotBlank(searchVo.getEndDate())){
                    Date start = DateUtil.parse(searchVo.getStartDate());
                    Date end = DateUtil.parse(searchVo.getEndDate());
                    list.add(cb.between(createTimeField, start, DateUtil.endOfDay(end)));
                }

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, initPage);
    }

    @Override
    public List<OutputWarehouseDetail> findByCreateTimeBetween(Date start, Date end) {
        return outputWarehouseDetailDao.findByCreateTimeBetween(start,end);
    }
}