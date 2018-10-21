package com.sun.storemanager.controller;

import com.sun.storemanager.base.BaseController;
import com.sun.storemanager.common.annotation.SystemLog;
import com.sun.storemanager.common.utils.GsonUtil;
import com.sun.storemanager.common.utils.PageUtil;
import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.utils.SnowFlakeUtil;
import com.sun.storemanager.common.vo.PageVo;
import com.sun.storemanager.common.vo.Result;
import com.sun.storemanager.entity.InputWarehouse;
import com.sun.storemanager.entity.InputWarehouseDetail;
import com.sun.storemanager.service.InputWarehouseDetailService;
import com.sun.storemanager.service.InputWarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;


/**
 * @author sunjiamin
 */
@Slf4j
@RestController
@Api(description = "商品入存主表管理接口")
@RequestMapping("/store/inputWarehouse")
public class InputWarehouseController extends BaseController<InputWarehouse, Integer>{


    @Autowired
    private InputWarehouseService inputWarehouseService;

    @Override
    public InputWarehouseService getService() {
        return inputWarehouseService;
    }



    @Autowired
    private StringRedisTemplate redisTemplate;



    @Transactional(rollbackFor=Exception.class)
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    @SystemLog(description="配件入库")
    public Result<String> add(@ModelAttribute InputWarehouse inputWarehouse){
        try {
            ArrayList<InputWarehouseDetail> inputWarehouseDetails = GsonUtil.GsonToArrayList(inputWarehouse.getInputWarehouseDetailList(), InputWarehouseDetail.class);
            inputWarehouse.setInputWarehouseDetailListObject(inputWarehouseDetails);
            String s = inputWarehouseService.insertInputWareHouse(inputWarehouse);
            if(s.equals("yes")){
                return new ResultUtil<String>().setData("OK");
            }else {
                return new ResultUtil<String>().setErrorMsg("新增失败，请稍后重试！！！");
            }

        }catch (Exception e){
            e.printStackTrace();
            return new ResultUtil<String>().setErrorMsg(e.getMessage());
        }
    }
}
