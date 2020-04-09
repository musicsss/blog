package com.xidian.blog.controller;

import com.xidian.blog.VO.BlogDetailVO;
import com.xidian.blog.entity.BlogCommentEntity;
import com.xidian.blog.entity.BlogConfigEntity;
import com.xidian.blog.entity.BlogLinkEntity;
import com.xidian.blog.service.*;
import com.xidian.blog.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 米
 * @date 2020/3/31
 */
@Controller
public class BlogController {
    public static String theme = "amaze";

    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogTagService blogTagService;
    @Autowired
    private BlogLinkService blogLinkService;
    @Autowired
    private BlogCommentService blogCommentService;
    @Autowired
    private BlogConfigService blogConfigService;
    @Autowired
    private BlogCategoryService blogCategoryService;

    @Autowired
    private BlogGreetingService blogGreetingService;
    
    /**
     *@Description: 首页显示
     *@Param:
     *@Return:
     */
    @GetMapping({"/", "/index", "index.html"})
    public String index(HttpServletRequest request) {
        return this.page(request, 1);
    }

    /**
     *@Description: 分页显示博文
     *@Param:
     *@Return:
     */
    @GetMapping({"/page/{pageNum}"})
    public String page(HttpServletRequest request, @PathVariable("pageNum") int pageNum){
        PageResult blogPageResult = blogService.getBlogsForIndexPage(pageNum);
        if (blogPageResult == null) {
            return "error/error_404";
        }
        BlogConfigEntity blogConfigEntity ;
        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("newBlogs", blogService.getBlogListForIndexPage(1));
        request.setAttribute("hotBlogs", blogService.getBlogListForIndexPage(0));
        request.setAttribute("hotTags", blogTagService.getBlogTagCountForIndex());
        request.setAttribute("pageName", "首页");
        request.setAttribute("greetingContent",blogGreetingService.selectRandomGreeting().getContent());
        Map<String,String> map = blogConfigService.getAllConfigs();
        for (String key : map.keySet()) {
            System.err.println("key= "+ key + " and value= " + map.get(key));;
        }
        request.setAttribute("configurations",map);
        return "blog/" + theme + "/index";
    }

    /**
     *@Description: Categories页面(包括分类数据和标签数据)
     *@Param: request
     *@Return:
     */
    @GetMapping({"/categories"})
    public String categories(HttpServletRequest request){
        request.setAttribute("hotTags", blogTagService.getBlogTagCountForIndex());
        request.setAttribute("categories", blogCategoryService.getAllCategories());
        request.setAttribute("pageName", "分类页面");
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        request.setAttribute("greetingContent",blogGreetingService.selectRandomGreeting().getContent());
        return "blog/" + theme + "/category";
    }
    /**
     *@Description:详情页
     *@Param:
     *@Return:
     */
    @GetMapping({"/blog/{blogId}","/article/{blogId"})
    public String detail(HttpServletRequest request, @PathVariable("blogId") Long blogId, @RequestParam(value = "commentPage", required = false, defaultValue = "1") Integer commentPage){
        BlogDetailVO blogDetailVO = blogService.getBlogDetail(blogId);
        if (blogDetailVO != null) {
            request.setAttribute("blogDetailVO", blogDetailVO);
            request.setAttribute("commentPageResult", blogCommentService.getCommentPageByBlogIdAndPageNum(blogId, commentPage));
        }
        request.setAttribute("pageName", "详情");
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        return "blog/" + theme + "/detail";
    }

    /**
     *@Description: 标签列表页
     *@Param:
     *@Return:
     */
    @GetMapping({"/tag/{tagName}"})
    public String tag(HttpServletRequest request, @PathVariable("tagName") String tagName) {
        return tag(request, tagName, 1);
    }

    /**
     *@Description:标签列表页
     *@Param:
     *@Return:
     */
    @GetMapping({"/tag/{tagName}/{page}"})
    public String tag(HttpServletRequest request, @PathVariable("tagName") String tagName, @PathVariable("page") Integer page) {
        PageResult blogPageResult = blogService.getBlogsPageByTag(tagName, page);
        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("pageName", "标签");
        request.setAttribute("pageUrl", "tag");
        request.setAttribute("keyword", tagName);
        request.setAttribute("newBlogs", blogService.getBlogListForIndexPage(1));
        request.setAttribute("hotBlogs", blogService.getBlogListForIndexPage(0));
        request.setAttribute("hotTags", blogTagService.getBlogTagCountForIndex());
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        request.setAttribute("greetingContent",blogGreetingService.selectRandomGreeting().getContent());
        return "blog/" + theme + "/list";
    }

    /**
     *@Description: 分类列表页
     *@Param:
     *@Return:
     */
    @GetMapping({"/category/{categoryName}"})
    public String category(HttpServletRequest request, @PathVariable("categoryName") String categoryName) {
        return category(request, categoryName, 1);
    }

    /**
     *@Description: 分类列表页
     *@Param:
     *@Return:
     */
    @GetMapping({"/category/{categoryName}/{page}"})
    public String category(HttpServletRequest request, @PathVariable("categoryName") String categoryName, @PathVariable("page") Integer page) {
        PageResult blogPageResult = blogService.getBlogsPageByCategory(categoryName, page);
        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("pageName", "分类");
        request.setAttribute("pageUrl", "category");
        request.setAttribute("keyword", categoryName);
        request.setAttribute("newBlogs", blogService.getBlogListForIndexPage(1));
        request.setAttribute("hotBlogs", blogService.getBlogListForIndexPage(0));
        request.setAttribute("hotTags", blogTagService.getBlogTagCountForIndex());
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        request.setAttribute("greetingContent",blogGreetingService.selectRandomGreeting().getContent());
        return "blog/" + theme + "/list";
    }

    /**
     *@Description: 搜索列表页
     *@Param:
     *@Return:
     */
    @GetMapping({"/search/{keyword}"})
    public String search(HttpServletRequest request, @PathVariable("keyword") String keyword) {
        return search(request, keyword, 1);
    }

    /**
     *@Description: 搜索列表页
     *@Param:
     *@Return:
     */
    @GetMapping({"/search/{keyword}/{page}"})
    public String search(HttpServletRequest request, @PathVariable("keyword") String keyword, @PathVariable("page") Integer page) {
        PageResult blogPageResult = blogService.getBlogsPageBySearch(keyword, page);
        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("pageName", "搜索");
        request.setAttribute("pageUrl", "search");
        request.setAttribute("keyword", keyword);
        request.setAttribute("newBlogs", blogService.getBlogListForIndexPage(1));
        request.setAttribute("hotBlogs", blogService.getBlogListForIndexPage(0));
        request.setAttribute("hotTags", blogTagService.getBlogTagCountForIndex());
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        request.setAttribute("greetingContent",blogGreetingService.selectRandomGreeting().getContent());
        return "blog/" + theme + "/list";
    }

    @GetMapping({"/link"})
    public String link(HttpServletRequest request) {
        request.setAttribute("pageName", "友情链接");
        Map<Byte, List<BlogLinkEntity>> linkMap = blogLinkService.getLinksForLinkPage();
        if (linkMap != null) {
            //判断友链类别并封装数据 0-友链 1-推荐 2-个人网站
            if (linkMap.containsKey((byte) 0)) {
                request.setAttribute("favoriteLinks", linkMap.get((byte) 0));
            }
            if (linkMap.containsKey((byte) 1)) {
                request.setAttribute("recommendLinks", linkMap.get((byte) 1));
            }
            if (linkMap.containsKey((byte) 2)) {
                request.setAttribute("personalLinks", linkMap.get((byte) 2));
            }
        }
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        return "blog/" + theme + "/link";
    }

    /**
     *@Description: 评论操作
     *@Param:
     *@Return:
     */
    @PostMapping(value = "/blog/comment")
    @ResponseBody
    public Result comment(HttpServletRequest request, HttpSession session,
                          @RequestParam Long blogId, @RequestParam String verifyCode,
                          @RequestParam String commentator, @RequestParam String email,
                          @RequestParam String websiteUrl, @RequestParam String commentBody) {
        if (StringUtils.isEmpty(verifyCode)) {
            return ResultGenerator.genFailResult("验证码不能为空");
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode)) {
            return ResultGenerator.genFailResult("非法请求");
        }
        if (!verifyCode.equals(kaptchaCode)) {
            return ResultGenerator.genFailResult("验证码错误");
        }
        String ref = request.getHeader("Referer");
        if (StringUtils.isEmpty(ref)) {
            return ResultGenerator.genFailResult("非法请求");
        }
        if (null == blogId || blogId < 0) {
            return ResultGenerator.genFailResult("非法请求");
        }
        if (StringUtils.isEmpty(commentator)) {
            return ResultGenerator.genFailResult("请输入称呼");
        }
        if (StringUtils.isEmpty(email)) {
            return ResultGenerator.genFailResult("请输入邮箱地址");
        }
        if (!PatternUtil.isEmail(email)) {
            return ResultGenerator.genFailResult("请输入正确的邮箱地址");
        }
        if (StringUtils.isEmpty(commentBody)) {
            return ResultGenerator.genFailResult("请输入评论内容");
        }
        if (commentBody.trim().length() > 200) {
            return ResultGenerator.genFailResult("评论内容过长");
        }
        BlogCommentEntity comment = new BlogCommentEntity();
        comment.setBlogId(blogId);
        comment.setCommentator(BlogUtils.cleanString(commentator));
        comment.setEmail(email);
        comment.setCommentCreateTime(new Date());
        if (PatternUtil.isURL(websiteUrl)) {
            comment.setWebsiteUrl(websiteUrl);
        }
        comment.setCommentBody(BlogUtils.cleanString(commentBody));
        return ResultGenerator.genSuccessResult(blogCommentService.addComment(comment));
    }

    /**
     *@Description: 关于页面 以及其他配置了subUrl的文章页
     *@Param:
     *@Return:
     */
    @GetMapping({"/{subUrl}"})
    public String detail(HttpServletRequest request, @PathVariable("subUrl") String subUrl) {
        BlogDetailVO blogDetailVO = blogService.getBlogDetailBySubUrl(subUrl);
        if (blogDetailVO != null) {
            request.setAttribute("blogDetailVO", blogDetailVO);
            request.setAttribute("pageName", subUrl);
            request.setAttribute("configurations", blogConfigService.getAllConfigs());
            return "blog/" + theme + "/detail";
        } else {
            return "error/error_400";
        }
    }
}
