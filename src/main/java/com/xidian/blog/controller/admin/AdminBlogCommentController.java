package com.xidian.blog.controller.admin;

import com.xidian.blog.service.BlogCommentService;
import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.Result;
import com.xidian.blog.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 米
 * @date 2020/4/3
 */
@Controller
@RequestMapping("/admin")
public class AdminBlogCommentController {
    @Autowired
    BlogCommentService blogCommentService;

    @GetMapping("/comments/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(blogCommentService.getCommentsPage(pageUtil));
    }

    @PostMapping("/comments/checkDone")
    @ResponseBody
    public Result checkDone(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (blogCommentService.checkDone(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("审核失败");
        }
    }

    @PostMapping("/comments/reply")
    @ResponseBody
    public Result checkDone(@RequestParam("commentId") Long commentId,
                            @RequestParam("replyBody") String replyBody) {
        if (commentId == null || commentId < 1 || StringUtils.isEmpty(replyBody)) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (blogCommentService.reply(commentId, replyBody)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("回复失败");
        }
    }

    @PostMapping("/comments/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (blogCommentService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("刪除失败");
        }
    }

    @GetMapping("/comments")
    public String list(HttpServletRequest request) {
        request.setAttribute("path", "comments");
        return "admin/comment";
    }
}
