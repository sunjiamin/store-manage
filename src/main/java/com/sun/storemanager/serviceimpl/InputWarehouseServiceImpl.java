package com.sun.storemanager.serviceimpl;

import com.sun.storemanager.common.utils.SnowFlakeUtil;
import com.sun.storemanager.dao.InputWarehouseDao;
import com.sun.storemanager.dao.ProductStockDao;
import com.sun.storemanager.entity.InputWarehouse;
import com.sun.storemanager.entity.InputWarehouseDetail;
import com.sun.storemanager.entity.Product;
import com.sun.storemanager.entity.ProductStock;
import com.sun.storemanager.service.InputWarehouseDetailService;
import com.sun.storemanager.service.InputWarehouseService;
import com.sun.storemanager.service.ProductStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品入存主表接口实现
 * @author sunjiamin
 */
@Slf4j
@Service
@Transactional
public class InputWarehouseServiceImpl implements InputWarehouseService {

    @Autowired
    private InputWarehouseDao inputWarehouseDao;

    @Override
    public InputWarehouseDao getRepository() {
        return inputWarehouseDao;
    }

    @Autowired
    private InputWarehouseDetailService inputWarehouseDetailService;

    @Autowired
    private ProductStockService productStockService;



    @Override
    public String insertInputWareHouse(InputWarehouse inputWarehouse) {
       try {
           //1.新增主表
           inputWarehouse.setInputNo( String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId()));
           inputWarehouse.setCreateTime(new Date());
           InputWarehouse save = this.save(inputWarehouse);
           //2.新增详细表
           for (InputWarehouseDetail inputWarehouseDetail : inputWarehouse.getInputWarehouseDetailListObject()) {
               inputWarehouseDetail.setCreateTime(new Date());
               inputWarehouseDetail.setInputWarehouseId(inputWarehouse.getId());
               InputWarehouseDetail u = inputWarehouseDetailService.save(inputWarehouseDetail);
           }
           //3.更改配件库存
           for (InputWarehouseDetail inputWarehouseDetail : inputWarehouse.getInputWarehouseDetailListObject()) {
               Product product = inputWarehouseDetail.getProduct();
               ProductStock stock = ((ProductStockDao) productStockService.getRepository()).findByProductId(product.getId());
               if(null==stock){
                   ProductStock productStock = new ProductStock();
                   productStock.setProduct(product);
                   productStock.setCreateTime(new Date());
                   productStock.setProductStock(inputWarehouseDetail.getNum());
                   productStockService.save(productStock);
               }else {
                   stock.setUpdateTime(new Date());
                   stock.setProductStock(stock.getProductStock()+inputWarehouseDetail.getNum());
                   productStockService.update(stock);
               }
           }
           return "yes";

       }catch (Exception e){
           e.printStackTrace();
           return "no";
       }
    }
}