package com.sun.storemanager.controller;

import com.sun.storemanager.common.annotation.RateLimiter;
import com.sun.storemanager.common.lock.Callback;
import com.sun.storemanager.common.lock.RedisDistributedLockTemplate;
import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.vo.Result;
import com.sun.storemanager.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunjiamin
 */
@Slf4j
@RestController
@Api(description = "测试接口")
@RequestMapping(value = "/store")
public class TestController {

    @Autowired
    private RedisDistributedLockTemplate lockTemplate;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @RateLimiter(limit = 1, timeout = 5000)
    @ApiOperation(value = "同步锁限流测试")
    public Result<Object> test(){

        lockTemplate.execute("订单流水号", 5000, new Callback() {
            @Override
            public Object onGetLock() throws InterruptedException {
                //TODO 获得锁后要做的事
                log.info("生成订单流水号");
                return null;
            }

            @Override
            public Object onTimeout() throws InterruptedException {
                //TODO 获得锁超时后要做的事
                return null;
            }
        });
        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/test/user",method = RequestMethod.POST)
    @ApiOperation(value = "同步锁限流测试")
    public Result<Object> testUser(@RequestParam String id){

       List<User> users = new ArrayList<>();
       User u = new User();
       u.setPassword("1");
       u.setNickName("aa");
       u.setUsername("aa");

        User u1 = new User();
        u1.setPassword("2");
        u1.setNickName("bb");
        u1.setUsername("bb");

        users.add(u1);
        users.add(u);

        return new ResultUtil<Object>().setData(users);
    }

}
