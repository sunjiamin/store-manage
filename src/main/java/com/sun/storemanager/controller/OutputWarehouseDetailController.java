package com.sun.storemanager.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.sun.storemanager.base.BaseController;
import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.vo.PageVo;
import com.sun.storemanager.common.vo.Result;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.entity.*;
import com.sun.storemanager.service.OutputWarehouseDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author sunjiamin
 */
@Slf4j
@RestController
@Api(description = "商品出存详细表管理接口")
@RequestMapping("/store/outputWarehouseDetail")
public class OutputWarehouseDetailController extends BaseController<OutputWarehouseDetail, Integer>{

    @Autowired
    private OutputWarehouseDetailService outputWarehouseDetailService;

    @Override
    public OutputWarehouseDetailService getService() {
        return outputWarehouseDetailService;
    }



    @RequestMapping(value = "/getByCondition",method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取出库记录")
    public Result<Page<OutputWarehouseDetail>> getByCondition(@ModelAttribute OutputWarehouseDetail outputWarehouseDetail,
                                                             @ModelAttribute SearchVo searchVo,
                                                             @ModelAttribute PageVo pageVo){

        Page<OutputWarehouseDetail> page = outputWarehouseDetailService.findByPage(outputWarehouseDetail, searchVo, pageVo);

        return new ResultUtil<Page<OutputWarehouseDetail>>().setData(page);

    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ApiOperation(value = "修改用户自己资料",notes = "用户名密码不会修改 需要通过id获取原用户信息 需要username更新缓存")
    public Result<Object> edit(@ModelAttribute OutputWarehouseDetail u){

        u.setUpdateTime(new Date());
        Product  p = new Product();
        p.setId(u.getProductId());
        u.setProduct(p);

        SalePerson salePerson = new SalePerson();
        salePerson.setId(u.getSalePersonId());
        u.setSalePerson(salePerson);

        Customer customer = new Customer();
        customer.setId(u.getCustomerId());
        u.setCustomer(customer);
        BigDecimal amount = new BigDecimal(u.getAmount());
        BigDecimal surcharge = new BigDecimal(u.getSurcharge());

        BigDecimal totalAmount = amount .add(surcharge);
        u.setTotalAmount(totalAmount.toString());

        OutputWarehouseDetail user=outputWarehouseDetailService.update(u);
        if(user==null){
            return new ResultUtil<Object>().setErrorMsg("修改失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("修改成功");
    }
}
