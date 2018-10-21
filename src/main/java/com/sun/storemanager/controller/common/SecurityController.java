package com.sun.storemanager.controller.common;

import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunjiamin
 */
@Slf4j
@RestController
@Api(description = "Security相关接口")
@RequestMapping("/store/common")
public class SecurityController {

    @RequestMapping(value = "/needLogin",method = RequestMethod.GET)
    @ApiOperation(value = "没有登录")
    public Result<Object> needLogin(){

        return new ResultUtil<Object>().setErrorMsg(401, "您还未登录");
    }
}
