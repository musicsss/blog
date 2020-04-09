package com.xidian.blog.controller.admin;

import com.xidian.blog.entity.BlogGreetingEntity;
import com.xidian.blog.entity.BlogLinkEntity;
import com.xidian.blog.service.BlogGreetingService;
import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.Result;
import com.xidian.blog.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author 米
 * @date 2020/4/3
 */
@Controller
@RequestMapping("/admin")
public class AdminGreetingController {
    @Autowired
    BlogGreetingService blogGreetingService;

    @GetMapping("/greetings")
    public String linkPage(HttpServletRequest request) {
        request.setAttribute("path", "greetings");
        return "admin/greetings";
    }

    @GetMapping("/greetings/list")
    @ResponseBody
    Result greetingsList(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(blogGreetingService.getBlogGreetingPage(pageUtil));
    }

    @Transactional
    @RequestMapping(value = "/greetings/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestParam("greetingType") Integer greetingType,
                       @RequestParam("greetingContent") String greetingContent) {
        if (greetingType == null || greetingType < 0 || StringUtils.isEmpty(greetingContent) ) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        BlogGreetingEntity blogGreetingEntity = new BlogGreetingEntity();
        blogGreetingEntity.setContent(greetingContent);
        blogGreetingEntity.setGreetingType(greetingType);
        blogGreetingEntity.setCreateTime(new Date());
        blogGreetingEntity.setUpdateTime(new Date());
        return ResultGenerator.genSuccessResult(blogGreetingService.saveGreeting(blogGreetingEntity));
    }
    @GetMapping("/greetings/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Integer id){
        BlogGreetingEntity blogGreetingEntity = blogGreetingService.getBlogGreetingEntityById(id);
        return ResultGenerator.genSuccessResult(blogGreetingEntity);
    }

    @PostMapping("/greetings/update")
    @ResponseBody
    public Result update(@RequestParam("greetingId") Integer greetingId,
                         @RequestParam("greetingType") Integer greetingType,
                         @RequestParam("greetingContent") String greetingContent){
        BlogGreetingEntity blogGreetingEntity = new BlogGreetingEntity();
        blogGreetingEntity.setContent(greetingContent);
        blogGreetingEntity.setGreetingType(greetingType);
        blogGreetingEntity.setId(greetingId);
        blogGreetingEntity.setUpdateTime(new Date());
        boolean result = blogGreetingService.update(blogGreetingEntity);
        return ResultGenerator.genSuccessResult(result);
    }

    @RequestMapping(value = "/greetings/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (blogGreetingService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
