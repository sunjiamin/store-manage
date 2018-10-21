package com.sun.storemanager.controller;

import com.sun.storemanager.base.BaseController;
import com.sun.storemanager.common.utils.PageUtil;
import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.vo.PageVo;
import com.sun.storemanager.common.vo.Result;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.entity.Product;
import com.sun.storemanager.entity.ProductStock;
import com.sun.storemanager.service.ProductStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


/**
 * @author sunjiamin
 */
@Slf4j
@RestController
@Api(description = "商品库存表管理接口")
@RequestMapping("/store/productStock")
public class ProductStockController extends BaseController<ProductStock, String>{

    @Autowired
    private ProductStockService productStockService;

    @Override
    public ProductStockService getService() {
        return productStockService;
    }


    @RequestMapping(value = "/getByCondition",method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取用户列表")
    public Result<Page<ProductStock>> getByCondition(@ModelAttribute ProductStock productStock,
                                                @ModelAttribute SearchVo searchVo,
                                                @ModelAttribute PageVo pageVo){

        Page<ProductStock> page = productStockService.findByCondition(productStock, searchVo, PageUtil.initPage(pageVo));

        page.map(entity->{
          //  entity.setProductClassName(productClassService.get(entity.getProductClass()).getClassName());
            return entity;
        });
        return new ResultUtil<Page<ProductStock>>().setData(page);
    }

}
