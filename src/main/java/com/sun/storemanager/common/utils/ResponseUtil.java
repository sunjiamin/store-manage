package com.sun.storemanager.common.utils;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sunjiamin
 */
@Slf4j
public class ResponseUtil {

    /**
     *  使用response输出JSON
     * @param response
     * @param resultMap
     */
    public static void out(HttpServletResponse response, Map<String, Object> resultMap){

        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");
            response.setHeader("Access-Control-Max-Age", "3628800");

            out = response.getWriter();
            out.println(new Gson().toJson(resultMap));
        } catch (Exception e) {
            log.error(e + "输出JSON出错");
        }finally{
            if(out!=null){
                out.flush();
                out.close();
            }
        }
    }

    public static Map<String, Object> resultMap(boolean flag, Integer code, String msg){

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("success", flag);
        resultMap.put("message", msg);
        resultMap.put("code", code);
        resultMap.put("timestamp", System.currentTimeMillis());
        return resultMap;
    }

    public static Map<String, Object> resultMap(boolean flag, Integer code, String msg, Object data){

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("success", flag);
        resultMap.put("message", msg);
        resultMap.put("code", code);
        resultMap.put("timestamp", System.currentTimeMillis());
        resultMap.put("result", data);
        return resultMap;
    }
}
