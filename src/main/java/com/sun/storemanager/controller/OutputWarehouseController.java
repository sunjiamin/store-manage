package com.sun.storemanager.controller;

import com.sun.storemanager.base.BaseController;
import com.sun.storemanager.common.annotation.SystemLog;
import com.sun.storemanager.common.utils.GsonUtil;
import com.sun.storemanager.common.utils.PageUtil;
import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.vo.PageVo;
import com.sun.storemanager.common.vo.Result;
import com.sun.storemanager.entity.InputWarehouse;
import com.sun.storemanager.entity.InputWarehouseDetail;
import com.sun.storemanager.entity.OutputWarehouse;
import com.sun.storemanager.entity.OutputWarehouseDetail;
import com.sun.storemanager.service.OutputWarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


/**
 * @author sunjiamin
 */
@Slf4j
@RestController
@Api(description = "商品出存主表管理接口")
@RequestMapping("/store/outputWarehouse")
public class OutputWarehouseController extends BaseController<OutputWarehouse, Integer>{

    @Autowired
    private OutputWarehouseService outputWarehouseService;

    @Override
    public OutputWarehouseService getService() {
        return outputWarehouseService;
    }


    @Transactional(rollbackFor=Exception.class)
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    @SystemLog(description="配件出库")
    public Result<String> add(@ModelAttribute OutputWarehouse outputWarehouse){
        try {
            ArrayList<OutputWarehouseDetail> outputWarehouseDetails = GsonUtil.GsonToArrayList(outputWarehouse.getOutputWarehouseDetailList(), OutputWarehouseDetail.class);
            outputWarehouse.setOutputWarehouseDetailListObject(outputWarehouseDetails);
            String s = outputWarehouseService.insertOutputWareHouse(outputWarehouse);
            if("yes".equals(s)){
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
