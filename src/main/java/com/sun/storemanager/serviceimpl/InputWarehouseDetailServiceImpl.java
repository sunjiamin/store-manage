package com.sun.storemanager.serviceimpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.dao.InputWarehouseDetailDao;
import com.sun.storemanager.entity.InputWarehouseDetail;
import com.sun.storemanager.entity.Product;
import com.sun.storemanager.service.InputWarehouseDetailService;
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
 * 商品入存详细表接口实现
 * @author sunjiamin
 */
@Slf4j
@Service
@Transactional
public class InputWarehouseDetailServiceImpl implements InputWarehouseDetailService {

    @Autowired
    private InputWarehouseDetailDao inputWarehouseDetailDao;

    @Override
    public InputWarehouseDetailDao getRepository() {
        return inputWarehouseDetailDao;
    }

    @Override
    public Page<InputWarehouseDetail> findByCondition(InputWarehouseDetail inputWarehouseDetail, SearchVo searchVo, Pageable initPage) {
        return inputWarehouseDetailDao.findAll(new Specification<InputWarehouseDetail>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<InputWarehouseDetail> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

               // Path<String> productId = root.get("productId");

                Path<Date> createTimeField=root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                //模糊搜素
//                if(StrUtil.isNotBlank(inputWarehouseDetail.get())){
//                    list.add(cb.like(productName,'%'+product.getProductName()+'%'));
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
    public List<InputWarehouseDetail> findByCreateTimeBetween(Date start, Date end) {
        return inputWarehouseDetailDao.findByCreateTimeBetween(start,end);
    }
}