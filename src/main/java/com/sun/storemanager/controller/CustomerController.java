package com.sun.storemanager.controller;

import com.sun.storemanager.base.BaseController;
import com.sun.storemanager.common.annotation.SystemLog;
import com.sun.storemanager.common.utils.PageUtil;
import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.vo.PageVo;
import com.sun.storemanager.common.vo.Result;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.entity.Customer;
import com.sun.storemanager.entity.SalePerson;
import com.sun.storemanager.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * @author sunjiamin
 */
@Slf4j
@RestController
@Api(description = "客户管理接口")
@RequestMapping("/store/customer")
@CacheConfig(cacheNames = "customer")
public class CustomerController extends BaseController<Customer, String>{

    @Autowired
    private CustomerService customerService;

    @Override
    public CustomerService getService() {
        return customerService;
    }


    @Autowired
    private StringRedisTemplate redisTemplate;


    @RequestMapping(value = "/getByCondition",method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取用户列表")
    public Result<Page<Customer>> getByCondition(@ModelAttribute Customer customer,
                                                   @ModelAttribute SearchVo searchVo,
                                                   @ModelAttribute PageVo pageVo){

        Page<Customer> page = customerService.findByCondition(customer, searchVo, PageUtil.initPage(pageVo));

        return new ResultUtil<Page<Customer>>().setData(page);
    }


    @Override
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取全部数据")
    @Cacheable(key = "'allList'")
    public Result<List<Customer>> getAll(){

        List<Customer> list = getService().getAll();
        return new ResultUtil<List<Customer>>().setData(list);
    }



    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    @SystemLog(description="添加客户")
    public Result<Customer> add(@ModelAttribute Customer customer){
        customer.setCreateTime(new Date());
        Customer u = customerService.save(customer);
        //手动删除缓存
        redisTemplate.delete("customer::allList");
        return new ResultUtil<Customer>().setData(u);
    }

    @Override
    @RequestMapping(value = "/delByIds",method = RequestMethod.DELETE)
    @ApiOperation(value = "批量通过ids删除")
    @SystemLog(description="删除客户")
    public Result<Object> delAllByIds(@RequestParam String[] ids){

        for(String id:ids){
            customerService.delete(id);
        }
        //手动删除缓存
        redisTemplate.delete("customer::allList");
        return new ResultUtil<Object>().setSuccessMsg("批量通过id删除数据成功");
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ApiOperation(value = "修改用户自己资料",notes = "用户名密码不会修改 需要通过id获取原用户信息 需要username更新缓存")
    @SystemLog(description="修改客户")
    public Result<Object> edit(@ModelAttribute Customer u){

        u.setUpdateTime(new Date());
        Customer p=customerService.update(u);
        if(p==null){
            return new ResultUtil<Object>().setErrorMsg("修改失败");
        }
        //手动删除缓存
        redisTemplate.delete("customer::allList");
        return new ResultUtil<Object>().setSuccessMsg("修改成功");
    }
}
