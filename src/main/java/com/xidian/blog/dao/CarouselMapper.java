package com.xidian.blog.dao;

import com.xidian.blog.entity.Carousel;
import com.xidian.blog.utils.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 米
 * @date 2020/3/26
 */
public interface CarouselMapper {
    int deleteByPrimaryKey(Integer carouselId);

    int insert(Carousel record);

    int insertSelective(Carousel record);

    Carousel selectByPrimaryKey(Integer carouselId);

    int updateByPrimaryKeySelective(Carousel record);

    int updateByPrimaryKey(Carousel record);

    List<Carousel> findCarouselList(PageQueryUtil pageUtil);

    int getTotalCarousels(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);

    List<Carousel> findCarouselsByNum(@Param("number") int number);
}
