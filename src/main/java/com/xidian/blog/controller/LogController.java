package com.xidian.blog.controller;

import com.xidian.blog.constant.CodeType;
import com.xidian.blog.utils.IpAddressUtils;
import com.xidian.blog.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author 米
 * @date 2020/1/20
 */
@Controller
public class LogController {
    @RequestMapping("/log/ipAddress")
    @ResponseBody
    public  String getIpAddress(@RequestBody Map<String,Object> request, HttpServletRequest httpServletRequest){
        String urlStr = IpAddressUtils.getIpAddr(httpServletRequest);
        System.out.println(urlStr);
        IpAddressUtils ipAddressUtils = new IpAddressUtils();
        String address = "";
        try{
            address = ipAddressUtils.getAddress("ip="+urlStr,"utf-8");
            return address;
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();;
        }
        return JsonResult.fail(CodeType.ERROR_NOT_FOUND).toJSON();
    }
}
