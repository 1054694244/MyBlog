package com.shenzc.artiicleCategory.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.shenzc.artiicleCategory.mapper.BlogMapper;
import com.shenzc.artiicleCategory.vo.BlogParams;
import com.shenzc.artiicleCategory.vo.BlogVo;
import com.shenzc.entity.backendUser.Category;
import com.shenzc.entity.front.Blog;
import com.shenzc.enumeration.BlogSelected;
import com.shenzc.enumeration.BlogStatus;
import com.shenzc.service.IdService;
import com.shenzc.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private IdService idService;
    @Autowired
    private CategoryService categoryService;

    //发布或者保存博客/编辑博客
    public void submitOrSaveBlog(BlogVo blogVo){
        //解析分类
        String categoryIds = blogVo.getCategoryIds();
        String categorys = parseCategory(categoryIds);
        blogVo.setCategoryIds(categorys);
        if (StringUtils.isNotBlank(blogVo.getBlogId())){
            blogVo.setUpdateBy(JwtUtil.username());
            blogMapper.update(blogVo,new UpdateWrapper<Blog>().eq("blog_id",blogVo.getBlogId()));
        }else {
            blogVo.setBlogId(idService.generateIdByRedis("blogId"));
            blogVo.setCreateBy(JwtUtil.username());
            blogMapper.insert(blogVo);
        }
    }

    //跟新博客
    public void updateBlog(BlogVo blogVo){
        blogMapper.update(blogVo,new QueryWrapper<Blog>().eq("blog_id",blogVo.getBlogId()));
    }

    //删除博客
    public void deleteBlog(String blogId){
        blogMapper.delete(new QueryWrapper<Blog>().eq("blog_id",blogId));
    }

    /*public Blog selectBlogById(String blogId){
        Blog blog = blogMapper.selectOne(new QueryWrapper<Blog>().eq("blog_id", blogId));

    }*/

    /**
     * 查询文章
     * @param blogParams
     * @return
     */
    public List<BlogVo> getBlog(BlogParams blogParams){
        Integer index = blogParams.getIndex();
        if (index == null){
            index = 0 ;
        }
        Integer page = blogParams.getPage();
        Integer limit = blogParams.getLimit();
        PageHelper.startPage(page,limit);
        Integer blogStatus = null;
        Integer isDelete = 0;
        Integer isPublic = null;
        if (index == BlogSelected.PUBLIC.getCode()){
            blogStatus = BlogStatus.PASS.getCode();
            isPublic = 1;
            isDelete = 0;
        }else if (index == BlogSelected.PRIVATE.getCode()){
            blogStatus = BlogStatus.PASS.getCode();
            isPublic = 0;
            isDelete = 0;
        }else if (index == BlogSelected.AUDIT.getCode()){
            blogStatus = BlogStatus.SUBMIT.getCode();
            isDelete = 0;
        }else if (index == BlogSelected.SAVE.getCode()){
            blogStatus = BlogStatus.SAVE.getCode();
            isDelete = 0;
        }else if (index == BlogSelected.DELETE.getCode()){
            isDelete = 1;
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("isPublic",isPublic);
        paramMap.put("isDelete",isDelete);
        paramMap.put("status",blogStatus);
        if (blogParams.getYear() == "2"){
            paramMap.put("year","2019");
        }
        paramMap.put("month",blogParams.getMonth());
        paramMap.put("type",blogParams.getType());
        //关键字搜索，现在先模糊查询标题
        paramMap.put("keyword",blogParams.getKeyword());
        List<BlogVo> blogList = blogMapper.selectBlog(paramMap);
        return blogList;
    }

    /**
     * 根据Id获取到博客
     * @param id
     * @return
     */
    public Blog getBlogById(String id) {
        Blog blog = blogMapper.selectById(id);
        String categoryIds = blog.getCategoryIds();
        categoryIds = formatCategory(categoryIds);
        blog.setCategoryIds(categoryIds);
        return blog;
    }

    //解析分类
    public String parseCategory(String categoryIds){
        //解析分类
        String categorys = "";
        String[] categoryArr = categoryIds.split(",");
        for (String category:categoryArr){
            if (category.contains("/")){
                String[] cate = category.split("/");
                categorys  += cate[1] +",";
            }else {
                categorys  += category + ",";
            }
        }
        categorys = categorys.substring(0,categorys.length()-1);
        return categorys;
    }

    //解析分类
    public String formatCategory(String categoryIds){
        //解析分类
        StringBuilder sb = new StringBuilder();
        String[] categories = categoryIds.split(",");
        List<String> categoryIdList = Arrays.asList(categories);
        List<Category> categoryList = categoryService.getCategoryByCategoryIds(categoryIdList);
        for (Category category : categoryList){
            if (!"0".equals(category.getParentCategoryId())){
                sb.append(category.getParentCategoryId()+"/"+category.getCategoryId()+",");
            }else {
                sb.append(category.getCategoryId()+",");
            }
        }
        return sb.substring(0, sb.length()-1);
    }
}
