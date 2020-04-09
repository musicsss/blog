package com.xidian.blog.controller;

import com.xidian.blog.constant.Constants;
import com.xidian.blog.utils.Result;
import com.xidian.blog.utils.ResultGenerator;
import com.xidian.blog.utils.BlogUtils;
import com.xidian.blog.utils.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * @author 米
 * @date 2020/3/26
 */

@Controller
public class UpLoadController {
    // 文件保存路径为 D 盘下的 upload 文件夹，可以按照自己的习惯来修改
    @RequestMapping(value = "/admin/upload/file", method = RequestMethod.POST)
    @ResponseBody
    public Result upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file, HttpSession session) throws URISyntaxException {
        String url = UploadUtil.uploadImage(file);
        Result resultSuccess = ResultGenerator.genSuccessResult();
        resultSuccess.setData(url);
        return resultSuccess;
    }
}
