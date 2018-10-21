package com.sun.storemanager.controller;

import com.sun.storemanager.base.BaseController;
import com.sun.storemanager.common.utils.PageUtil;
import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.vo.PageVo;
import com.sun.storemanager.common.vo.Result;
import com.sun.storemanager.entity.Dictionary;
import com.sun.storemanager.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author SunJiamin
 */
@Slf4j
@RestController
@Api(description = "字典表管理接口")
@RequestMapping("/store/dictionary")
public class DictionaryController extends BaseController<Dictionary, Integer>{

    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public DictionaryService getService() {
        return dictionaryService;
    }

    @RequestMapping(value = "/getByType",method = RequestMethod.GET)
    @ApiOperation(value = " ")
    public Result<List<Dictionary>> getbyType(@RequestParam String type){

        List<Dictionary> result = dictionaryService.findByType(type);
        return new ResultUtil<List<Dictionary>>().setData( result);
    }

}
