package com.sun.storemanager.controller.manage;

import com.sun.storemanager.common.utils.PageUtil;
import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.vo.PageVo;
import com.sun.storemanager.common.vo.Result;
import com.sun.storemanager.entity.Log;
import com.sun.storemanager.entity.elasticsearch.EsLog;
import com.sun.storemanager.service.LogService;
import com.sun.storemanager.service.elasticsearch.EsLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


/**
 * 拥有ROLE_ADMIN角色的用户可以访问
 * @author sunjiamin
 */
@Slf4j
@RestController
@Api(description = "日志管理接口")
@RequestMapping("/store/log")
public class LogController{

    @Value("${xboot.logRecord.es}")
    private Boolean esRecord;

    //@Autowired
    private EsLogService esLogService;

    @Autowired
    private LogService logService;

    @RequestMapping(value = "/getAllByPage",method = RequestMethod.GET)
    @ApiOperation(value = "分页获取全部")
    public Result<Object> getAllByPage(@ModelAttribute PageVo pageVo){

        if(esRecord){
            Page<EsLog> logList = esLogService.getLogList(PageUtil.initPage(pageVo));
            Page<EsLog> es = logList;
            return new ResultUtil<Object>().setData(es);
        }else{
            Page<Log> log = logService.findAll(PageUtil.initPage(pageVo));
            return new ResultUtil<Object>().setData(log);
        }
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ApiOperation(value = "分页搜索")
    public Result<Object> search(@RequestParam String key, @ModelAttribute PageVo pageVo){

        if(esRecord){
            Page<EsLog> es = esLogService.searchLog(key, PageUtil.initPage(pageVo));
            return new ResultUtil<Object>().setData(es);
        }else{
            Page<Log> log = logService.searchLog(key, PageUtil.initPage(pageVo));
            return new ResultUtil<Object>().setData(log);
        }
    }

    @RequestMapping(value = "/delByIds",method = RequestMethod.DELETE)
    @ApiOperation(value = "批量删除")
    public Result<Object> delByIds(@RequestParam String[] ids){

        for(String id : ids){
            if(esRecord){
                esLogService.deleteLog(id);
            }else{
                logService.delete(id);
            }
        }
        return new ResultUtil<Object>().setSuccessMsg("删除成功");
    }

    @RequestMapping(value = "/delAll",method = RequestMethod.DELETE)
    @ApiOperation(value = "全部删除")
    public Result<Object> delAll(){

        if(esRecord){
            esLogService.deleteAll();
        }else{
            logService.deleteAll();
        }
        return new ResultUtil<Object>().setSuccessMsg("删除成功");
    }
}
