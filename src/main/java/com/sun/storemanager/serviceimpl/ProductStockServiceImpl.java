package com.sun.storemanager.serviceimpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.dao.ProductStockDao;
import com.sun.storemanager.entity.Product;
import com.sun.storemanager.entity.ProductStock;
import com.sun.storemanager.service.ProductStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品库存表接口实现
 * @author sunjiamin
 */
@Slf4j
@Service
@Transactional
public class ProductStockServiceImpl implements ProductStockService {

    @Autowired
    private ProductStockDao productStockDao;

    @Override
    public ProductStockDao getRepository() {
        return productStockDao;
    }

    @Override
    public Page<ProductStock> findByCondition(ProductStock productStock, SearchVo searchVo, Pageable initPage) {
        return productStockDao.findAll(new Specification<ProductStock>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<ProductStock> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> productName = root.get("productName");
                Path<Date> createTimeField=root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                //模糊搜素
                if(StrUtil.isNotBlank(productStock.getProductName())){
                    list.add(cb.like(productName,'%'+productStock.getProductName()+'%'));
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