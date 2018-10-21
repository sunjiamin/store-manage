package com.sun.storemanager.controller;

import com.sun.storemanager.base.BaseController;
import com.sun.storemanager.common.utils.PageUtil;
import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.vo.PageVo;
import com.sun.storemanager.common.vo.Result;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.entity.Product;
import com.sun.storemanager.entity.ProductClass;
import com.sun.storemanager.service.ProductClassService;
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
@Api(description = "商品类别表管理接口")
@RequestMapping("/store/productClass")
@CacheConfig(cacheNames = "productClass")
public class ProductClassController extends BaseController<ProductClass, String>{

    @Autowired
    private ProductClassService productClassService;

    @Override
    public ProductClassService getService() {
        return productClassService;
    }


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取全部数据")
    @Cacheable(key = "'allList'")
    public Result<List<ProductClass>> getAll(){

        List<ProductClass> list = getService().getAll();
        return new ResultUtil<List<ProductClass>>().setData(list);
    }


    @RequestMapping(value = "/getByCondition",method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取用户列表")
    public Result<Page<ProductClass>> getByCondition(@ModelAttribute ProductClass productClass,
                                                @ModelAttribute SearchVo searchVo,
                                                @ModelAttribute PageVo pageVo){

        Page<ProductClass> page = productClassService.findByCondition(productClass, searchVo, PageUtil.initPage(pageVo));

        return new ResultUtil<Page<ProductClass>>().setData(page);
    }




    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    public Result<ProductClass> add(@ModelAttribute ProductClass productClass){

        productClass.setCreateTime(new Date());
        ProductClass u = productClassService.save(productClass);
        //手动删除缓存
        redisTemplate.delete("productClass::allList");
        return new ResultUtil<ProductClass>().setData(u);
    }

    @Override
    @RequestMapping(value = "/delByIds",method = RequestMethod.DELETE)
    @ApiOperation(value = "批量通过ids删除")
    public Result<Object> delAllByIds(@RequestParam String[] ids){

        for(String id:ids){
            productClassService.delete(id);
        }
        //手动删除缓存
        redisTemplate.delete("productClass::allList");
        return new ResultUtil<Object>().setSuccessMsg("批量通过id删除数据成功");
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ApiOperation(value = "修改用户自己资料",notes = "用户名密码不会修改 需要通过id获取原用户信息 需要username更新缓存")
    @CacheEvict(key = "#u.id")
    public Result<Object> edit(@ModelAttribute ProductClass u){

        u.setUpdateTime(new Date());
        ProductClass p=productClassService.update(u);
        if(p==null){
            return new ResultUtil<Object>().setErrorMsg("修改失败");
        }
        redisTemplate.delete("productClass::allList");
        return new ResultUtil<Object>().setSuccessMsg("修改成功");
    }



}
