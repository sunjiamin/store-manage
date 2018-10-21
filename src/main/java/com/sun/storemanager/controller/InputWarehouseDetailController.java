package com.sun.storemanager.controller;

import com.sun.storemanager.base.BaseController;
import com.sun.storemanager.common.utils.PageUtil;
import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.vo.PageVo;
import com.sun.storemanager.common.vo.Result;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.entity.InputWarehouseDetail;
import com.sun.storemanager.entity.Product;
import com.sun.storemanager.service.InputWarehouseDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
@Api(description = "商品入存详细表管理接口")
@RequestMapping("/store/inputWarehouseDetail")
public class InputWarehouseDetailController extends BaseController<InputWarehouseDetail, String> {

    @Autowired
    private InputWarehouseDetailService inputWarehouseDetailService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public InputWarehouseDetailService getService() {
        return inputWarehouseDetailService;
    }


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    @CacheEvict(key = "'productList'")//@CacheEvict标记的方法会在方法执行前或者执行后移除Spring Cache中的某些元素
    public Result<String> add(@ModelAttribute InputWarehouseDetail[] inputWarehouseDetailList){

        for (InputWarehouseDetail inputWarehouseDetail : inputWarehouseDetailList) {
            inputWarehouseDetail.setCreateTime(new Date());
            InputWarehouseDetail u = inputWarehouseDetailService.save(inputWarehouseDetail);
        }

        //手动删除缓存
        redisTemplate.delete("inputWarehouseDetail::allList");
        return new ResultUtil<String>().setData("OK");
    }


    @RequestMapping(value = "/getByCondition",method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取用户列表")
    public Result<Page<InputWarehouseDetail>> getByCondition(@ModelAttribute InputWarehouseDetail inputWarehouseDetail,
                                                @ModelAttribute SearchVo searchVo,
                                                @ModelAttribute PageVo pageVo){

        Page<InputWarehouseDetail> page = inputWarehouseDetailService.findByCondition(inputWarehouseDetail, searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<InputWarehouseDetail>>().setData(page);
    }

}
