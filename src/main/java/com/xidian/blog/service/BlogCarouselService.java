package com.xidian.blog.service;

import com.xidian.blog.entity.Carousel;
import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.PageResult;

/**
 * @author 米
 * @date 2020/3/26
 */
public interface BlogCarouselService {
    /**
     *@Description: 获取分页轮播
     *@Param: pageUtil
     *@Return: PageResult
     */
    PageResult getCarouselPage(PageQueryUtil pageUtil);

    String saveCarousel(Carousel carousel);

    boolean deleteBatch(Integer[] ids);

    String updateCarousel(Carousel carousel);
}
