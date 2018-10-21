package com.sun.storemanager.controller;

import com.sun.storemanager.base.BaseController;
import com.sun.storemanager.common.annotation.SystemLog;
import com.sun.storemanager.common.utils.PageUtil;
import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.vo.PageVo;
import com.sun.storemanager.common.vo.Result;
import com.sun.storemanager.common.vo.SearchVo;
import com.sun.storemanager.entity.Customer;
import com.sun.storemanager.entity.ProductClass;
import com.sun.storemanager.entity.SalePerson;
import com.sun.storemanager.service.SalePersonService;
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
@Api(description = "销售员管理接口")
@RequestMapping("/store/salePerson")
@CacheConfig(cacheNames = "salePerson")
public class SalePersonController extends BaseController<SalePerson, String>{

    @Autowired
    private SalePersonService salePersonService;

    @Override
    public SalePersonService getService() {
        return salePersonService;
    }



    @Autowired
    private StringRedisTemplate redisTemplate;


    @RequestMapping(value = "/getByCondition",method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取用户列表")
    public Result<Page<SalePerson>> getByCondition(@ModelAttribute SalePerson salePerson,
                                                     @ModelAttribute SearchVo searchVo,
                                                     @ModelAttribute PageVo pageVo){

        Page<SalePerson> page = salePersonService.findByCondition(salePerson, searchVo, PageUtil.initPage(pageVo));

        return new ResultUtil<Page<SalePerson>>().setData(page);
    }

    @Override
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取全部数据")
    @Cacheable(key = "'allList'")
    public Result<List<SalePerson>> getAll(){

        List<SalePerson> list = getService().getAll();
        return new ResultUtil<List<SalePerson>>().setData(list);
    }




    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    @SystemLog(description="添加出库员")
    public Result<SalePerson> add(@ModelAttribute SalePerson salePerson){

        salePerson.setCreateTime(new Date());
        SalePerson u = salePersonService.save(salePerson);
        //手动删除缓存
        redisTemplate.delete("salePerson::allList");
        return new ResultUtil<SalePerson>().setData(u);
    }

    @Override
    @RequestMapping(value = "/delByIds",method = RequestMethod.DELETE)
    @ApiOperation(value = "批量通过ids删除")
    @SystemLog(description="删除出库员")
    public Result<Object> delAllByIds(@RequestParam String[] ids){

        for(String id:ids){
            salePersonService.delete(id);
        }
        //手动删除缓存
        redisTemplate.delete("salePerson::allList");
        return new ResultUtil<Object>().setSuccessMsg("批量通过id删除数据成功");
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ApiOperation(value = "修改用户自己资料",notes = "")
    @SystemLog(description="编辑出库员")
    public Result<Object> edit(@ModelAttribute SalePerson u){

        u.setUpdateTime(new Date());
        SalePerson p=salePersonService.update(u);
        if(p==null){
            return new ResultUtil<Object>().setErrorMsg("修改失败");
        }
        //手动删除缓存
        redisTemplate.delete("salePerson::allList");
        return new ResultUtil<Object>().setSuccessMsg("修改成功");
    }


}
