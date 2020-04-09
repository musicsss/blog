package com.xidian.blog.controller.admin;

import com.xidian.blog.constant.BlogCategoryLevelEnum;
import com.xidian.blog.entity.ItemsCategory;
import com.xidian.blog.service.BlogCategoryService;
import com.xidian.blog.service.BlogItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * @author 米
 * @date 2020/3/29
 */
@Controller
@RequestMapping("/admin")
public class BlogItemController {

    @Autowired
    BlogItemService blogItemService;
    @Autowired
    BlogCategoryService blogCategoryService;

    @GetMapping("/items")
    public String goodsPage(HttpServletRequest request) {
        request.setAttribute("path", "blog_items");
        return "admin/blog_items";
    }

    @GetMapping("/items/edit")
    public String edit(HttpServletRequest request) {
        request.setAttribute("path", "edit");
        //查询所有的一级分类
        List<ItemsCategory> firstLevelCategories = blogCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), BlogCategoryLevelEnum.LEVEL_ONE.getLevel());
        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
            //查询一级分类列表中第一个实体的所有二级分类
            List<ItemsCategory> secondLevelCategories = blogCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(firstLevelCategories.get(0).getCategoryId()), BlogCategoryLevelEnum.LEVEL_TWO.getLevel());
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                //查询二级分类列表中第一个实体的所有三级分类
                List<ItemsCategory> thirdLevelCategories = blogCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), BlogCategoryLevelEnum.LEVEL_THREE.getLevel());
                request.setAttribute("firstLevelCategories", firstLevelCategories);
                request.setAttribute("secondLevelCategories", secondLevelCategories);
                request.setAttribute("thirdLevelCategories", thirdLevelCategories);
                request.setAttribute("path", "goods-edit");
                return "admin/blog_goods_edit";
            }
        }
        return "error/error_5xx";
    }
}
