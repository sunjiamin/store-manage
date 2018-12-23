package com.sun.storemanager.controller;

import com.sun.storemanager.base.BaseController;
import com.sun.storemanager.common.annotation.SystemLog;
import com.sun.storemanager.common.constant.CommonConstant;
import com.sun.storemanager.common.utils.PageUtil;
import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.vo.PageVo;
import com.sun.storemanager.common.vo.Result;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.entity.*;
import com.sun.storemanager.service.ProductClassService;
import com.sun.storemanager.service.ProductService;
import com.sun.storemanager.service.UserService;
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
@Api(description = "商品表管理接口")
@RequestMapping("/store/product")
@CacheConfig(cacheNames = "product")
public class ProductController extends BaseController<Product, String>{

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductClassService productClassService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public ProductService getService() {
        return productService;
    }


    @RequestMapping(value = "/getByCondition",method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取用户列表")
    public Result<Page<Product>> getByCondition(@ModelAttribute Product product,
                                             @ModelAttribute SearchVo searchVo,
                                             @ModelAttribute PageVo pageVo){

        Page<Product> page = productService.findByCondition(product, searchVo, PageUtil.initPage(pageVo));

        page.map(entity ->{
            ProductClass productClass = entity.getProductClass();
            if(null!=productClass){
                entity.setProductClassName(productClass.getClassName());
            }
            Supplier supplier = entity.getSupplier();
            if(null!=supplier ){
                entity.setSupplierName(supplier.getName());
            }
            return entity;
        });
        return new ResultUtil<Page<Product>>().setData(page);
    }


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    @CacheEvict(key = "'productList'")//@CacheEvict标记的方法会在方法执行前或者执行后移除Spring Cache中的某些元素
    @SystemLog(description="新增配件")
    public Result<Product> add(@ModelAttribute Product product){

        product.setCreateTime(new Date());
        Product u = productService.save(product);
        //手动删除缓存
        redisTemplate.delete("product::allList");
        return new ResultUtil<Product>().setData(u);
    }

    @Override
    @RequestMapping(value = "/delByIds",method = RequestMethod.DELETE)
    @ApiOperation(value = "批量通过ids删除")
    @SystemLog(description="删除配件")
    public Result<Object> delAllByIds(@RequestParam String[] ids){

        for(String id:ids){
            productService.delete(id);
        }
        return new ResultUtil<Object>().setSuccessMsg("批量通过id删除数据成功");
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ApiOperation(value = "修改用户自己资料",notes = "用户名密码不会修改 需要通过id获取原用户信息 需要username更新缓存")
    @CacheEvict(key = "#u.id")
    @SystemLog(description="修改配件")
    public Result<Object> edit(@ModelAttribute Product u){

        u.setUpdateTime(new Date());
        Product user=productService.update(u);
        if(user==null){
            return new ResultUtil<Object>().setErrorMsg("修改失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("修改成功");
    }

}
