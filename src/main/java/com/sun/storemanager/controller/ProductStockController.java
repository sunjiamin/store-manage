package com.sun.storemanager.controller;

import com.sun.storemanager.base.BaseController;
import com.sun.storemanager.common.annotation.SystemLog;
import com.sun.storemanager.common.utils.PageUtil;
import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.utils.StringUtil;
import com.sun.storemanager.common.vo.PageVo;
import com.sun.storemanager.common.vo.Result;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.dao.ProductDao;
import com.sun.storemanager.dao.mapper.ProductMapper;
import com.sun.storemanager.entity.Product;
import com.sun.storemanager.entity.ProductExample;
import com.sun.storemanager.entity.ProductStock;
import com.sun.storemanager.service.ProductService;
import com.sun.storemanager.service.ProductStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductMapper productMapper;


    @Override
    public ProductStockService getService() {
        return productStockService;
    }






    @RequestMapping(value = "/getByCondition",method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取用户列表")
    public Result<Page<ProductStock>> getByCondition(@ModelAttribute ProductStock productStock,
                                                @ModelAttribute SearchVo searchVo,
                                                @ModelAttribute PageVo pageVo){

        List<Product> productList = null;
//        if(!StringUtil.isBlank(productStock.getProductName()) &&
//                !StringUtil.isBlank(productStock.getProductCode()) &&
//                !StringUtil.isBlank(productStock.getProductSpec())){
//            productList = productDao.findByProductNameContainingAndProductCodeContainingAndProductSpecContaining(
//                    productStock.getProductName(),productStock.getProductCode(),productStock.getProductSpec());
//
//            if(null == productList || productList.size()<=0){
//                return new ResultUtil<Page<ProductStock>>().setData(null);
//            }
//
//        }else if(!StringUtil.isBlank(productStock.getProductName()) &&
//                !StringUtil.isBlank(productStock.getProductCode()) ){
//            productList = productDao.findByProductNameContainingAndProductCodeContaining(
//                    productStock.getProductName(),productStock.getProductCode());
//
//            if(null == productList || productList.size()<=0){
//                return new ResultUtil<Page<ProductStock>>().setData(null);
//            }
//
//        }else if(!StringUtil.isBlank(productStock.getProductName()) &&
//                !StringUtil.isBlank(productStock.getProductSpec()) ){
//            productList = productDao.findByProductNameContainingAndProductSpecContaining(
//                    productStock.getProductName(),productStock.getProductSpec());
//
//            if(null == productList || productList.size()<=0){
//                return new ResultUtil<Page<ProductStock>>().setData(null);
//            }
//
//        }else if(!StringUtil.isBlank(productStock.getProductCode()) &&
//                !StringUtil.isBlank(productStock.getProductSpec()) ){
//            productList = productDao.findByProductCodeContainingAndProductSpecContaining(
//                    productStock.getProductCode(),productStock.getProductSpec());
//
//            if(null == productList || productList.size()<=0){
//                return new ResultUtil<Page<ProductStock>>().setData(null);
//            }
//
//        }else if(!StringUtil.isBlank(productStock.getProductName())){
//            productList = productDao.findByProductNameContaining(
//                    productStock.getProductName());
//            if(null == productList || productList.size()<=0){
//                return new ResultUtil<Page<ProductStock>>().setData(null);
//            }
//
//        }else if(!StringUtil.isBlank(productStock.getProductCode())){
//            productList = productDao.findByProductCodeContaining(
//                    productStock.getProductCode());
//            if(null == productList || productList.size()<=0){
//                return new ResultUtil<Page<ProductStock>>().setData(null);
//            }
//
//        }else if(!StringUtil.isBlank(productStock.getProductSpec())){
//            productList = productDao.findByProductSpecContaining(
//                    productStock.getProductSpec());
//            if(null == productList || productList.size()<=0){
//                return new ResultUtil<Page<ProductStock>>().setData(null);
//            }
//
//        }
//
//
//        ProductExample example = new ProductExample();
//        ProductExample.Criteria criteria = example.createCriteria();
//        if(!StringUtil.isBlank(productStock.getProductName())){
//            criteria.andProductNameLike(productStock.getProductName());
//        }
//        if(!StringUtil.isBlank(productStock.getProductCode())){
//            criteria.andProductCodeLike(productStock.getProductCode());
//        }
//        if(!StringUtil.isBlank(productStock.getProductSpec())){
//            criteria.andProductSpecLike(productStock.getProductSpec());
//        }

        //productList= productMapper.selectByExample(example);

        Product  p = new Product();
        p.setProductName(productStock.getProductName());
        Map<String, Object> paramMap = new HashMap<>();
        if(!StringUtil.isBlank(productStock.getProductName())){
            paramMap.put("productName", productStock.getProductName());
        }
        if(!StringUtil.isBlank(productStock.getProductCode())){
            paramMap.put("productCode", productStock.getProductCode());
        }
        if(!StringUtil.isBlank(productStock.getProductSpec())){
            paramMap.put("productSpec", productStock.getProductSpec());
        }

        if(!paramMap.isEmpty()){
            productList=  productMapper.selectByCond(paramMap);
            if(null == productList || productList.size()<=0){
                return new ResultUtil<Page<ProductStock>>().setData(null);
            }
        }



        Page<ProductStock> page = productStockService.findByCondition(productStock, productList,searchVo, PageUtil.initPage(pageVo));

        page.map(entity->{
            try {
                Product  product = productService.findById(entity.getProductId());
                entity.setProductCode(product.getProductCode());
                entity.setProductName(product.getProductName());
                entity.setProductSpec(product.getProductSpec());
                if(entity.getProductStock() <=entity.getStockThreshold()){
                    entity.setStockStatus("库存紧张");
                }else{
                    entity.setStockStatus("库存充足");
                }
                entity.setProduct(product);
            }catch (Exception e){
                e.printStackTrace();
                log.error(e.getMessage());
            }

            return entity;
        });

        return new ResultUtil<Page<ProductStock>>().setData(page);
    }

    @Override
    @RequestMapping(value = "/delByIds",method = RequestMethod.DELETE)
    @ApiOperation(value = "批量通过ids删除")
    @SystemLog(description="删除库存")
    public Result<Object> delAllByIds(@RequestParam String[] ids){

        for(String id:ids){
            productStockService.delete(id);
        }
        return new ResultUtil<Object>().setSuccessMsg("批量通过id删除数据成功");
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ApiOperation(value = "修改用户自己资料",notes = "用户名密码不会修改 需要通过id获取原用户信息 需要username更新缓存")

    @SystemLog(description="修改配件")
    public Result<Object> edit(@ModelAttribute ProductStock u){

        u.setUpdateTime(new Date());
        ProductStock user=productStockService.update(u);
        if(user==null){
            return new ResultUtil<Object>().setErrorMsg("修改失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("修改成功");
    }

}
