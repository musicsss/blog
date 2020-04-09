package com.xidian.blog.service.impl;

import com.xidian.blog.VO.BlogDetailVO;
import com.xidian.blog.VO.BlogListVO;
import com.xidian.blog.VO.SimpleBlogListVO;
import com.xidian.blog.constant.Constants;
import com.xidian.blog.dao.*;
import com.xidian.blog.entity.BlogCategoryEntity;
import com.xidian.blog.entity.BlogEntity;
import com.xidian.blog.entity.BlogTagEntity;
import com.xidian.blog.entity.BlogTagRelationEntity;
import com.xidian.blog.service.BlogService;
import com.xidian.blog.utils.MarkDownUtil;
import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.PageResult;
import com.xidian.blog.utils.PatternUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 米
 * @date 2020/3/31
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private BlogCategoryMapper categoryMapper;
    @Autowired
    private BlogTagMapper tagMapper;
    @Autowired
    private BlogTagRelationMapper blogTagRelationMapper;
    @Autowired
    private BlogCommentMapper blogCommentMapper;


    @Override
    public String saveBlog(BlogEntity blogEntity) {
        //1,获取博客分类
        BlogCategoryEntity blogCategoryEntity =
                categoryMapper.selectByPrimaryKey(blogEntity.getBlogCategoryId()) ;
        if(null == blogCategoryEntity){
            blogEntity.setBlogCategoryId(0);
            blogEntity.setBlogCategoryName("默认分类");
        }else{
            //设置博客分类名称
            blogEntity.setBlogCategoryName(blogCategoryEntity.getCategoryName());
            //分类排序值加一
            blogCategoryEntity.setCategoryRank(blogCategoryEntity.getCategoryRank()+1);
        }
        //处理标签
        String tags[] = blogEntity.getBlogTags().split(",");
        if(tags.length>6){
            return "标签最多为6个";
        }

        //保存文章
        if(blogMapper.insertSelective(blogEntity)>0){
            //新增tag对象
            List<BlogTagEntity> tagEntityListToInsert = new ArrayList<>();
            //所有tag对象，用于建立关系数据
            List<BlogTagEntity> tagEntityListAll = new ArrayList<>();
            for (int i = 0; i < tags.length; i++) {
                BlogTagEntity tag = tagMapper.selectByTagName(tags[i]);
                if (tag == null) {
                    //不存在就新增
                    BlogTagEntity tempTag = new BlogTagEntity();
                    tempTag.setTagName(tags[i]);
                    tempTag.setCreateTime(new Date());
                    tagEntityListToInsert.add(tempTag);
                } else {
                    tagEntityListAll.add(tag);
                }
            }
            //新增标签数据并修改分类排序值
            if (!CollectionUtils.isEmpty(tagEntityListToInsert)) {
                tagMapper.batchInsertBlogTag(tagEntityListToInsert);
            }
            categoryMapper.updateByPrimaryKeySelective(blogCategoryEntity);
            List<BlogTagRelationEntity> blogTagRelations = new ArrayList<>();
            //新增关系数据
            tagEntityListAll.addAll(tagEntityListToInsert);
            for (BlogTagEntity tag : tagEntityListAll) {
                BlogTagRelationEntity blogTagRelation = new BlogTagRelationEntity();
                blogTagRelation.setBlogId(blogEntity.getBlogId());
                blogTagRelation.setTagId(tag.getTagId());
                blogTagRelations.add(blogTagRelation);
            }
            if (blogTagRelationMapper.batchInsert(blogTagRelations) > 0) {
                return "success";
            }

        }
        return "保存失败";
    }

    @Override
    public PageResult getBlogPage(PageQueryUtil pageQueryUtil) {
        List<BlogEntity> blogList = blogMapper.findBlogList(pageQueryUtil);
        int total = blogMapper.getTotalBlogs(pageQueryUtil);
        PageResult pageResult = new PageResult(blogList, total, pageQueryUtil.getLimit(), pageQueryUtil.getPage());
        return pageResult;
    }

    @Override
    public PageResult getBlogsForIndexPage(int pageNum) {
        Map params = new HashMap();
        params.put("page",pageNum);
        //每页最多显示八条
        params.put("limit", Constants.PAGE_ARTICLE_LIMIT_NUM);
        //过滤发布状态下的博文
        params.put("blogStatus",1);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        List<BlogEntity> blogEntityList = blogMapper.findBlogList(pageUtil);
        List<BlogListVO> blogListVOS = getBlogListVOsByBlogEntityList(blogEntityList);
        int total = blogMapper.getTotalBlogs(pageUtil);
        PageResult pageResult = new PageResult(blogListVOS, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    private List<BlogListVO> getBlogListVOsByBlogEntityList(List<BlogEntity> blogEntityList) {
        List<BlogListVO> blogListVOList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(blogEntityList)){
            //抽取blogEntityList对象中所有categoryId的集合。
            List<Integer> categoryIdList =
                    blogEntityList.stream().map(BlogEntity::getBlogCategoryId).collect(Collectors.toList());
            Map<Integer,String> blogCategoryMap = new HashMap<>();
            if(!CollectionUtils.isEmpty(categoryIdList)){
                List<BlogCategoryEntity> blogCategoryEntityList = categoryMapper.selectByCategoryIds(categoryIdList);
                if (!CollectionUtils.isEmpty(blogCategoryEntityList)) {
                    blogCategoryMap = blogCategoryEntityList.stream().collect(Collectors.toMap(BlogCategoryEntity::getCategoryId, BlogCategoryEntity::getCategoryIcon, (key1, key2) -> key2));
                }
            }

            for (BlogEntity blogEntity : blogEntityList) {
                BlogListVO blogListVO = new BlogListVO();
                BeanUtils.copyProperties(blogEntity,blogListVO);
                if (blogCategoryMap.containsKey(blogEntity.getBlogCategoryId())) {
                    blogListVO.setBlogCategoryIcon(blogCategoryMap.get(blogEntity.getBlogCategoryId()));
                } else {
                    blogListVO.setBlogCategoryId(0);
                    blogListVO.setBlogCategoryName("默认分类");
                    blogListVO.setBlogCategoryIcon("/admin/dist/img/category/00.png");
                }
                blogListVOList.add(blogListVO);
            }
        }
        return  blogListVOList;
    }

    @Override
    public List<SimpleBlogListVO> getBlogListForIndexPage(int type) {
        List<SimpleBlogListVO> simpleBlogListVOS = new ArrayList<>();
        List<BlogEntity> blogEntityList = blogMapper.findBlogListByType(type,9);
        if(!CollectionUtils.isEmpty(blogEntityList)){
            for(BlogEntity blogEntity:blogEntityList){
                SimpleBlogListVO simpleBlogListVO = new SimpleBlogListVO();
                BeanUtils.copyProperties(blogEntity,simpleBlogListVO);
                simpleBlogListVOS.add(simpleBlogListVO);
            }
        }
        return simpleBlogListVOS;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return blogMapper.deleteBatch(ids)>0;
    }

    @Override
    public int getTotalBlogs() {

        return blogMapper.getTotalBlogs(null);
    }

    @Override
    public BlogEntity getBlogEntityById(Long blogId) {
        return blogMapper.selectByPrimaryKey(blogId);
    }

    @Override
    @Transactional
    public String updateBlog(BlogEntity blogEntity) {
        BlogEntity blogEntityToUpdate = blogMapper.selectByPrimaryKey(blogEntity.getBlogId());
        if(null == blogEntityToUpdate){
            return "数据不存在";
        }
        blogEntityToUpdate.setBlogTitle(blogEntity.getBlogTitle());
        blogEntityToUpdate.setBlogSubUrl(blogEntity.getBlogSubUrl());
        blogEntityToUpdate.setBlogContent(blogEntity.getBlogContent());
        blogEntityToUpdate.setBlogCoverImage(blogEntity.getBlogCoverImage());
        blogEntityToUpdate.setBlogStatus(blogEntity.getBlogStatus());
        blogEntityToUpdate.setEnableComment(blogEntity.getEnableComment());
        BlogCategoryEntity blogCategoryEntity = categoryMapper.selectByPrimaryKey(blogEntity.getBlogCategoryId());
        if(null == blogCategoryEntity){
            blogCategoryEntity.setCategoryId(0);
            blogCategoryEntity.setCategoryName("默认分类");
        }else{
            //设置博客分类名称
            blogEntityToUpdate.setBlogCategoryName(blogCategoryEntity.getCategoryName());
            blogEntityToUpdate.setBlogCategoryId(blogCategoryEntity.getCategoryId());
            //分类排序值加一
            blogCategoryEntity.setCategoryRank(blogCategoryEntity.getCategoryRank()+1);
        }
        //处理标签数据
        String[] tags = blogEntity.getBlogTags().split(",");
        if(tags.length>6){
            return "标签最多为6个";
        }
        blogEntityToUpdate.setBlogTags(blogEntity.getBlogTags());
        //新增tag对象
        List<BlogTagEntity> blogTagEntityListToInsert = new ArrayList<>();
        //所有的tag对象，用于建立关系数据
        List<BlogTagEntity> blogTagEntityListAll = new ArrayList<>();

        for(int i =0;i<tags.length;i++){
            BlogTagEntity blogTagEntity = tagMapper.selectByTagName(tags[i]);
            if(null == tags[i]){
                //不存在就进行新增操作
                BlogTagEntity tempTagEntity = new BlogTagEntity();
                tempTagEntity.setTagName(tags[i]);
                blogTagEntityListToInsert.add(tempTagEntity);
            }else{
                blogTagEntityListAll.add(blogTagEntity);
            }


        }

        //新增标签列表不为空则进行新增操作
        if(!CollectionUtils.isEmpty(blogTagEntityListToInsert)){
            tagMapper.batchInsertBlogTag(blogTagEntityListToInsert);
        }

        List<BlogTagRelationEntity> blogTagRelationEntityList = new ArrayList<>();
        //新增关系数据
        blogTagEntityListAll.addAll(blogTagEntityListToInsert);
        for(BlogTagEntity tag : blogTagEntityListAll){
            BlogTagRelationEntity blogTagRelationEntity = new BlogTagRelationEntity();
            blogTagRelationEntity.setBlogId(blogEntity.getBlogId());
            blogTagRelationEntity.setTagId(tag.getTagId());
            blogTagRelationEntityList.add(blogTagRelationEntity);
        }
        //修改blog信息-》修改分类排序值-》删除原关系数据-》保存新的关系数据
        categoryMapper.updateByPrimaryKeySelective(blogCategoryEntity);
        blogTagRelationMapper.deleteByBlogId(blogEntity.getBlogId());
        blogTagRelationMapper.batchInsert(blogTagRelationEntityList);
        if(blogMapper.updateByPrimaryKeySelective(blogEntityToUpdate)>0){
            return "success";
        }
        return null;
    }

    @Override
    public BlogDetailVO getBlogDetail(Long blogId) {
        BlogEntity blogEntity = blogMapper.selectByPrimaryKey(blogId);
        //不为空且状态为已发布
        BlogDetailVO blogDetailVO = getBlogDetailVO(blogEntity);
        if(blogDetailVO != null){
            return blogDetailVO;
        }
        return null;
    }

    private BlogDetailVO getBlogDetailVO(BlogEntity blogEntity) {
        if(blogEntity != null && blogEntity.getBlogStatus() == 1){
            //增加浏览量
            blogEntity.setBlogViews(blogEntity.getBlogViews()+1);
            blogMapper.updateByPrimaryKey(blogEntity);

            BlogDetailVO blogDetailVO = new BlogDetailVO();
            BeanUtils.copyProperties(blogEntity,blogDetailVO);
            blogDetailVO.setBlogContent(MarkDownUtil.mdToHtml(blogDetailVO.getBlogContent()));
            BlogCategoryEntity blogCategoryEntity = categoryMapper.selectByPrimaryKey(blogEntity.getBlogCategoryId());
            if(blogCategoryEntity == null){
                blogCategoryEntity = new BlogCategoryEntity();
                blogCategoryEntity.setCategoryId(0);
                blogCategoryEntity.setCategoryName("默认分类");
                blogCategoryEntity.setCategoryIcon("/admin/dist/img/category/00.png");
            }

            //分类信息
            blogDetailVO.setBlogCategoryIcon(blogCategoryEntity.getCategoryIcon());
            if(!StringUtils.isEmpty(blogEntity.getBlogTags())){
                //标签设置
                List<String> tags = Arrays.asList(blogEntity.getBlogTags().split(","));
                blogDetailVO.setBlogTags(tags);
            }

            //设置评论数
            Map parms = new HashMap();
            parms.put("blogId",blogEntity.getBlogId());
            //过滤审核通过的数据
            parms.put("commentStatus",1);
            blogDetailVO.setCommentCount(blogCommentMapper.getTotalBlogComments(parms));
            return blogDetailVO;
        }
        return null;
    }

    @Override
    public PageResult getBlogsPageByTag(String tagName, int page) {
        if(PatternUtil.validKeyword(tagName)){
            BlogTagEntity blogTagEntity = tagMapper.selectByTagName(tagName);
            if(blogTagEntity != null && page >0){
                Map param = new HashMap();
                param.put("page",page);
                param.put("limit",9);
                param.put("tagId",blogTagEntity.getTagId());
                PageQueryUtil pageQueryUtil = new PageQueryUtil(param);
                List<BlogEntity> blogEntityList = blogMapper.getBlogsPageByTagId(pageQueryUtil);
                List<BlogListVO> blogListVOList = getBlogListVOsByBlogEntityList(blogEntityList);
                int total = blogMapper.getTotalBlogsByTagId(pageQueryUtil);
                PageResult pageResult = new PageResult(blogListVOList,total,pageQueryUtil.getLimit(),pageQueryUtil.getPage());
                return pageResult;
            }
        }
        return null;
    }

    @Override
    public PageResult getBlogsPageByCategory(String categoryName, int page) {
        if(PatternUtil.validKeyword(categoryName)){
            BlogCategoryEntity blogCategoryEntity = categoryMapper.selectByCategoryName(categoryName);
            if("默认分类".equals(categoryName) && blogCategoryEntity == null){
                blogCategoryEntity = new BlogCategoryEntity();
                blogCategoryEntity.setCategoryId(0);
            }
            if(blogCategoryEntity !=null && page>0){
                Map param = new HashMap();
                param.put("page",page);
                param.put("limit",9);
                param.put("blogCategoryId",blogCategoryEntity.getCategoryId());
                //过滤发布状态下的数据
                param.put("blogStatus", 1);

                PageQueryUtil pageUtil = new PageQueryUtil(param);
                List<BlogEntity> blogEntityList = blogMapper.findBlogList(pageUtil);
                List<BlogListVO> blogListVOList = getBlogListVOsByBlogEntityList(blogEntityList);
                int total = blogMapper.getTotalBlogs(pageUtil);
                PageResult pageResult = new PageResult(blogListVOList,total,pageUtil.getLimit(),pageUtil.getPage());
                return pageResult;
            }
        }
        return null;
    }

    @Override
    public PageResult getBlogsPageBySearch(String keyword, int page) {
        if (page > 0 && PatternUtil.validKeyword(keyword)) {
            Map param = new HashMap();
            param.put("page", page);
            param.put("limit", 9);
            param.put("keyword", keyword);
            param.put("blogStatus", 1);//过滤发布状态下的数据
            PageQueryUtil pageUtil = new PageQueryUtil(param);
            List<BlogEntity> blogEntityList = blogMapper.findBlogList(pageUtil);
            List<BlogListVO> blogListVOS = getBlogListVOsByBlogEntityList(blogEntityList);
            int total = blogMapper.getTotalBlogs(pageUtil);
            PageResult pageResult = new PageResult(blogListVOS, total, pageUtil.getLimit(), pageUtil.getPage());
            return pageResult;
        }
        return null;
    }

    @Override
    public BlogDetailVO getBlogDetailBySubUrl(String subUrl) {
        BlogEntity blogEntity = blogMapper.selectBySubUrl(subUrl);
        //不为空且状态为已发布
        BlogDetailVO blogDetailVO = getBlogDetailVO(blogEntity);
        if (blogDetailVO != null){
            return blogDetailVO;
        }
        return null;
    }
}
