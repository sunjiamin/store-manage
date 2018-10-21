package com.sun.storemanager.controller.common;

import com.sun.storemanager.common.limit.RedisRaterLimiter;
import com.sun.storemanager.common.utils.IpInfoUtil;
import com.sun.storemanager.common.utils.QiniuUtil;
import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.vo.Result;
import com.sun.storemanager.exception.BaseException;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;

/**
 * @author sunjiamin
 */
@Slf4j
@RestController
@Api(description = "文件上传接口")
@RequestMapping("/store/upload")
public class UploadController {

    @Autowired
    private RedisRaterLimiter redisRaterLimiter;

    @Autowired
    private QiniuUtil qiniuUtil;

    @RequestMapping(value = "/file",method = RequestMethod.POST)
    @ApiOperation(value = "文件上传")
    public Result<Object> upload(@RequestParam("file") MultipartFile file,
                                 HttpServletRequest request) {

        // IP限流 在线Demo所需 5分钟限1个请求
        String token1 = redisRaterLimiter.acquireTokenFromBucket("upload:"+IpInfoUtil.getIpAddr(request), 1, 300000);
        if (StrUtil.isBlank(token1)) {
            throw new BaseException("上传那么多干嘛，等等再传吧");
        }

        String imagePath = null;
        String fileName = qiniuUtil.renamePic(file.getOriginalFilename());
        try {
            FileInputStream inputStream = (FileInputStream) file.getInputStream();
            //上传七牛云服务器
            imagePath= qiniuUtil.qiniuInputStreamUpload(inputStream,fileName);
            if(StrUtil.isBlank(imagePath)){
                return new ResultUtil<Object>().setErrorMsg("上传失败，请检查七牛云配置");
            }
            if(imagePath.contains("error")){
                return new ResultUtil<Object>().setErrorMsg(imagePath);
            }
        } catch (Exception e) {
            log.error(e.toString());
        }

        return new ResultUtil<Object>().setData(imagePath);
    }
}
