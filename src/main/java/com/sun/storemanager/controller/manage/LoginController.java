package com.sun.storemanager.controller.manage;

import com.sun.storemanager.common.utils.PageUtil;
import com.sun.storemanager.common.utils.ResponseUtil;
import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.vo.PageVo;
import com.sun.storemanager.common.vo.Result;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * description:
 *
 * @author sunjiamin
 * @date 2018-06-25 13:44
 */
@Slf4j
@RestController
@Api(description = "登陆接口")
@RequestMapping("/xbootxxx")
public class LoginController {

    @RequestMapping("/login")
    public  Map<String, Object>  login(HttpServletRequest request, Map<String, Object> map) throws Exception{
        System.out.println("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
                return  ResponseUtil.resultMap(true,500,"用户名错误");
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
                return  ResponseUtil.resultMap(true,500,"密码错误");
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
                return  ResponseUtil.resultMap(true,500,"用户名错误");
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
                return  ResponseUtil.resultMap(true,500,"用户名错误");
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return  ResponseUtil.resultMap(true,200,"登录成功");
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }

    @RequestMapping(value = "/login1", method = RequestMethod.POST)
    public Map<String, Object>  login1(String userName, String passWord) {
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return ResponseUtil.resultMap(true,500,"用户名错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();

            return ResponseUtil.resultMap(true,500,"密码错误");
        }
        return    ResponseUtil.resultMap(true,200,"登录成功");
    }
}
