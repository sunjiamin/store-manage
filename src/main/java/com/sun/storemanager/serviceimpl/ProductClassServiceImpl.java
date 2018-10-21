package com.sun.storemanager.serviceimpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.dao.ProductClassDao;
import com.sun.storemanager.entity.Product;
import com.sun.storemanager.entity.ProductClass;
import com.sun.storemanager.service.ProductClassService;
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
 * 商品类别表接口实现
 * @author sunjiamin
 */
@Slf4j
@Service
@Transactional
public class ProductClassServiceImpl implements ProductClassService {

    @Autowired
    private ProductClassDao productClassDao;

    @Override
    public ProductClassDao getRepository() {
        return productClassDao;
    }

    @Override
    public Page<ProductClass> findByCondition(ProductClass productClass, SearchVo searchVo, Pageable initPage) {
        return productClassDao.findAll(new Specification<ProductClass>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<ProductClass> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> className = root.get("className");
                Path<String> classCode = root.get("classCode");

                Path<Date> createTimeField=root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                //模糊搜素
                if(StrUtil.isNotBlank(productClass.getClassName())){
                    list.add(cb.like(className,'%'+productClass.getClassName()+'%'));
                }
                if(StrUtil.isNotBlank(productClass.getClassCode())){
                    list.add(cb.like(classCode,'%'+productClass.getClassCode()+'%'));
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
}