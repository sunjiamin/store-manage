package com.sun.storemanager.serviceimpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.storemanager.common.utils.StringUtil;
import com.sun.storemanager.common.vo.PageVo;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.dao.OutputWarehouseDetailDao;
import com.sun.storemanager.dao.mapper.OutputWarehouseDetailMapper;
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
import java.util.*;

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


    @Autowired
    private OutputWarehouseDetailMapper outputWarehouseDetailMapper;

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

               // Path<String > customerIdField=root.get("customersalePerson");
               // Path<String > salePersonIdField=root.get("salePerson");

                List<Predicate> list = new ArrayList<Predicate>();


                if(outputWarehouseDetail.getPayStatus()!=null){
                    list.add(cb.equal(payStatusField, outputWarehouseDetail.getPayStatus()));
                }

//                if(StrUtil.isNotBlank(outputWarehouseDetail.getSalePersonId())){
//                    list.add(cb.equal(salePersonIdField,outputWarehouseDetail.getSalePersonId()));
//                }
//                if(StrUtil.isNotBlank(outputWarehouseDetail.getCustomerId())){
//                    list.add(cb.equal(customerIdField,outputWarehouseDetail.getCustomerId()));
//                }

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

    @Override
    public  com.baomidou.mybatisplus.plugins.Page<OutputWarehouseDetail>
                   findByPage(OutputWarehouseDetail outputWarehouseDetail, SearchVo searchVo, PageVo pageVo) {

        int pageNumber=pageVo.getPageNumber();
        int pageSize=pageVo.getPageSize();
        String sort=pageVo.getSort();
        String order=pageVo.getOrder();

        Map<String, Object> paramMap = new HashMap<>();
        if(outputWarehouseDetail.getPayStatus() !=null ){
            paramMap.put("payStatus", outputWarehouseDetail.getPayStatus());
        }
        if(!StringUtil.isBlank(outputWarehouseDetail.getCustomerId())){
            paramMap.put("customerId", outputWarehouseDetail.getCustomerId());
        }
        if(!StringUtil.isBlank(outputWarehouseDetail.getSalePersonId())){
            paramMap.put("salePersonId", outputWarehouseDetail.getSalePersonId());
        }

        if(!StringUtil.isBlank(outputWarehouseDetail.getProductCode())){
            paramMap.put("productCode", outputWarehouseDetail.getProductCode());
        }
        if(!StringUtil.isBlank(outputWarehouseDetail.getProductName())){
            paramMap.put("productName", outputWarehouseDetail.getProductName());
        }

        String startDate = searchVo.getStartDate();
        String endDate = searchVo.getEndDate();
        if(!StringUtil.isBlank(startDate)){
            paramMap.put("startDate", startDate);
        }
        if(!StringUtil.isBlank(endDate)){
            paramMap.put("endDate", endDate);
        }
        com.baomidou.mybatisplus.plugins.Page<OutputWarehouseDetail> p =
                new com.baomidou.mybatisplus.plugins.Page<>(pageNumber, pageSize);
        p.setRecords(outputWarehouseDetailMapper.selectByCond(p, paramMap));
        return p;
    }
}