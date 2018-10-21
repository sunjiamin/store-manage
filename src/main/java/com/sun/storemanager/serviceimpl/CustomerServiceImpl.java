package com.sun.storemanager.serviceimpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.dao.CustomerDao;
import com.sun.storemanager.entity.Customer;
import com.sun.storemanager.entity.SalePerson;
import com.sun.storemanager.service.CustomerService;
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
 * 客户接口实现
 * @author sunjiamin
 */
@Slf4j
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public CustomerDao getRepository() {
        return customerDao;
    }

    @Override
    public Page<Customer> findByCondition(Customer customer, SearchVo searchVo, Pageable initPage) {
        return customerDao.findAll(new Specification<Customer>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> userName = root.get("name");
                Path<String> mobile = root.get("mobile");
                Path<Integer> sex = root.get("sex");

                Path<Date> createTimeField=root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                //模糊搜素
                if(StrUtil.isNotBlank(customer.getName())){
                    list.add(cb.like(userName,'%'+customer.getName()+'%'));
                }
                if(StrUtil.isNotBlank(customer.getMobile())){
                    list.add(cb.like(mobile,'%'+customer.getMobile()+'%'));
                }

                if(customer.getSex()!=null){
                    list.add(cb.equal(sex, customer.getSex()));
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