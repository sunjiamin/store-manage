package com.sun.storemanager.controller.manage;

import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.vo.EsCount;
import com.sun.storemanager.common.vo.EsInfo;
import com.sun.storemanager.common.vo.PageVo;
import com.sun.storemanager.common.vo.Result;
import com.sun.storemanager.exception.BaseException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


/**
 * 拥有ROLE_ADMIN角色的用户可以访问
 * @author sunjiamin
 */
@Slf4j
@RestController
@Api(description = "Elasticsearch信息接口")
@RequestMapping("/store/es")
public class EsController {

    @Value("${xboot.elasticsearch.nodeClient}")
    private String ES_NODE_CLIENT;

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ApiOperation(value = "分页获取全部")
    public Result<EsInfo> getAllByPage(@ModelAttribute PageVo pageVo){

        String healthUrl="http://"+ES_NODE_CLIENT+"/_cluster/health";
        String countUrl="http://"+ES_NODE_CLIENT+"/_count";
        String healthResult= HttpUtil.get(healthUrl);
        String countResult=HttpUtil.get(countUrl);
        if(StrUtil.isBlank(healthResult)||StrUtil.isBlank(countResult)){
            throw new BaseException("连接ES失败，请检查ES运行状态");
        }
        EsInfo esInfo=new EsInfo();
        EsCount esCount=new EsCount();
        try {
            esInfo=new Gson().fromJson(healthResult,EsInfo.class);
            esCount=new Gson().fromJson(countResult,EsCount.class);
            esInfo.setCount(esCount.getCount());
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("获取ES信息出错");
        }
        return new ResultUtil<EsInfo>().setData(esInfo);
    }
}
