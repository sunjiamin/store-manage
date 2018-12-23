package com.sun.storemanager.serviceimpl;

import com.sun.storemanager.common.utils.SnowFlakeUtil;
import com.sun.storemanager.dao.OutputWarehouseDao;
import com.sun.storemanager.dao.ProductStockDao;
import com.sun.storemanager.entity.*;
import com.sun.storemanager.service.*;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.DocValueFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品出存主表接口实现
 * @author sunjiamin
 */
@Slf4j
@Service
@Transactional
public class OutputWarehouseServiceImpl implements OutputWarehouseService {

    @Autowired
    private OutputWarehouseDao outputWarehouseDao;

    @Autowired
    private ProductStockService productStockService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OutputWarehouseDetailService outputWarehouseDetailService;

    @Override
    public OutputWarehouseDao getRepository() {
        return outputWarehouseDao;
    }

    @Override
    public String insertOutputWareHouse(OutputWarehouse outputWarehouse) {
        try {
            //1.新增主表
            outputWarehouse.setOutputNo( String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId()));
            outputWarehouse.setCreateTime(new Date());
            OutputWarehouse save = this.save(outputWarehouse);
            //2.新增详细表
            for (OutputWarehouseDetail outputWarehouseDetail : outputWarehouse.getOutputWarehouseDetailListObject()) {
                outputWarehouseDetail.setCreateTime(new Date());
                outputWarehouseDetail.setOutputWarehouseId(outputWarehouse.getId());
                outputWarehouseDetail.setProduct(productService.get(outputWarehouseDetail.getProductId()));
                SalePerson salePerson = new SalePerson();
                salePerson.setId(outputWarehouseDetail.getSalePersonId());
                outputWarehouseDetail.setSalePerson(salePerson);
                Integer num = outputWarehouseDetail.getNum();
                String price = outputWarehouseDetail.getPrice();
                String surcharge = outputWarehouseDetail.getSurcharge();
                BigDecimal amount = new BigDecimal(price).multiply(new BigDecimal(num)) ;
                BigDecimal totalAmount = amount .add(new BigDecimal(surcharge));
                outputWarehouseDetail.setAmount(amount.toString());
                outputWarehouseDetail.setTotalAmount(totalAmount.toString());
                //计算利润
                String costPrice = outputWarehouseDetail.getCostPrice();
                //价差
                BigDecimal priceDiff = new BigDecimal(price).subtract(new BigDecimal(costPrice));
                BigDecimal profit = priceDiff.multiply(new BigDecimal(num)).add(new BigDecimal(surcharge));
                outputWarehouseDetail.setProfit(profit.toString());
                OutputWarehouseDetail u = outputWarehouseDetailService.save(outputWarehouseDetail);
            }
            //3.更改配件库存
            for (OutputWarehouseDetail outputWarehouseDetail : outputWarehouse.getOutputWarehouseDetailListObject()) {
                String productId = outputWarehouseDetail.getProductId();
                ProductStock stock = ((ProductStockDao) productStockService.getRepository()).findByProductId(productId);
                if(null==stock){
                    //没有库存

                }else {
                    if(stock.getProductStock()<outputWarehouseDetail.getNum()){
                        //库存不够
                    }else{
                        stock.setUpdateTime(new Date());
                        stock.setProductStock(stock.getProductStock()-outputWarehouseDetail.getNum());
                        productStockService.update(stock);
                    }
                }
            }
            return "yes";

        }catch (Exception e){
            e.printStackTrace();
            return "no";
        }
    }
}