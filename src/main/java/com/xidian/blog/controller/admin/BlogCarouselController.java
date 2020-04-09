package com.xidian.blog.controller.admin;

import com.xidian.blog.constant.ServiceResultEnum;
import com.xidian.blog.entity.Carousel;
import com.xidian.blog.service.BlogCarouselService;
import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.Result;
import com.xidian.blog.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

/**
 * @author 米
 * @date 2020/3/26
 */
@Controller
public class BlogCarouselController {
    @Autowired
    BlogCarouselService blogCarouselService;

    @GetMapping("/admin/carousels")
    public String carouselPage(HttpServletRequest request){
        request.setAttribute("path", "blog_carousel");
        return "admin/blog_carousel";
    }


    @RequestMapping(value = "/admin/carousels/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(blogCarouselService.getCarouselPage(pageUtil));
    }

    @RequestMapping(value = "/admin/carousels/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody Carousel carousel, HttpSession session) {
        if (StringUtils.isEmpty(carousel.getCarouselUrl())
                || Objects.isNull(carousel.getCarouselRank())) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        carousel.setCarouselUrl(session.getAttribute("url").toString());
        String result = blogCarouselService.saveCarousel(carousel);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }
    @RequestMapping(value = "/admin/carousels/update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody Carousel carousel,HttpSession session){
        if (Objects.isNull(carousel.getCarouselId())
                || StringUtils.isEmpty(carousel.getCarouselUrl())
                || Objects.isNull(carousel.getCarouselRank())) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        carousel.setCarouselUrl(session.getAttribute("url").toString());
        String result = blogCarouselService.updateCarousel(carousel);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    @RequestMapping(value = "/admin/carousels/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (blogCarouselService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
