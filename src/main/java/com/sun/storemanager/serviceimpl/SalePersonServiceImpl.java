package com.sun.storemanager.serviceimpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.dao.SalePersonDao;
import com.sun.storemanager.entity.ProductClass;
import com.sun.storemanager.entity.SalePerson;
import com.sun.storemanager.service.SalePersonService;
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
 * 销售员接口实现
 * @author sunjiamin
 */
@Slf4j
@Service
@Transactional
public class SalePersonServiceImpl implements SalePersonService {

    @Autowired
    private SalePersonDao salePersonDao;

    @Override
    public SalePersonDao getRepository() {
        return salePersonDao;
    }

    @Override
    public Page<SalePerson> findByCondition(SalePerson salePerson, SearchVo searchVo, Pageable initPage) {
        return salePersonDao.findAll(new Specification<SalePerson>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<SalePerson> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> userName = root.get("userName");
                Path<String> mobile = root.get("mobile");
                Path<Integer> sex = root.get("sex");

                Path<Date> createTimeField=root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                //模糊搜素
                if(StrUtil.isNotBlank(salePerson.getUserName())){
                    list.add(cb.like(userName,'%'+salePerson.getUserName()+'%'));
                }
                if(StrUtil.isNotBlank(salePerson.getMobile())){
                    list.add(cb.like(mobile,'%'+salePerson.getMobile()+'%'));
                }

                if(salePerson.getSex()!=null){
                    list.add(cb.equal(sex, salePerson.getSex()));
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