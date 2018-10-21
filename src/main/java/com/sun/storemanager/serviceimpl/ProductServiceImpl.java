package com.sun.storemanager.serviceimpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.dao.ProductDao;
import com.sun.storemanager.entity.Product;
import com.sun.storemanager.entity.User;
import com.sun.storemanager.service.ProductService;
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
 * 商品表接口实现
 * @author sunjiamin
 */
@Slf4j
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public ProductDao getRepository() {
        return productDao;
    }

    @Override
    public Page<Product> findByCondition(Product product, SearchVo searchVo, Pageable initPage) {
        return productDao.findAll(new Specification<Product>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> productName = root.get("productName");
                Path<String> productCode = root.get("productCode");
                Path<String> productSpec = root.get("productSpec");
                Path<Integer> productClass=root.get("productClass");
                Path<Integer> supplierId=root.get("supplierId");
                Path<Integer> brandId=root.get("brandId");
                Path<Date> createTimeField=root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                //模糊搜素
                if(StrUtil.isNotBlank(product.getProductName())){
                    list.add(cb.like(productName,'%'+product.getProductName()+'%'));
                }
                if(StrUtil.isNotBlank(product.getProductCode())){
                    list.add(cb.like(productCode,'%'+product.getProductCode()+'%'));
                }
                if(StrUtil.isNotBlank(product.getProductSpec())){
                    list.add(cb.like(productSpec,'%'+product.getProductSpec()+'%'));
                }

//                if(StrUtil.isNotBlank(product.getProductClass())){
//                    list.add(cb.equal(productClass, product.getProductClass()));
//                }
                //类型
                if(StrUtil.isNotBlank(product.getSupplierId())){
                    list.add(cb.equal(supplierId, product.getSupplierId()));
                }
                //状态
                if(StrUtil.isNotBlank(product.getBrandId())){
                    list.add(cb.equal(brandId, product.getBrandId()));
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