package com.shenzc.artiicleCategory.controller;

import com.shenzc.artiicleCategory.service.BlogService;
import com.shenzc.artiicleCategory.vo.BlogParams;
import com.shenzc.artiicleCategory.vo.BlogVo;
import com.shenzc.entity.front.Blog;
import com.shenzc.resutl.CommonPage;
import com.shenzc.resutl.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/selfArticle/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;


    /**
     * 新增博客
     * @param blogVo
     * @return
     */
    @PostMapping("/submitBlog")
    public ResultBody submitBlog(@RequestBody BlogVo blogVo){
        blogService.submitOrSaveBlog(blogVo);
        return ResultBody.success("");
    }

    /**
     * 跟新博客
     * @param blogVo
     * @return
     */
    @PostMapping("/updateBlog")
    public ResultBody updateBlog(@RequestBody BlogVo blogVo){
        blogService.updateBlog(blogVo);
        return ResultBody.success("");
    }

    /**
     * 根据ID删除博客
     * @param blogId
     * @return
     */
    @GetMapping("/deleteBlog")
    public ResultBody deleteBlog(@RequestParam(value = "blogId")String blogId){
        blogService.deleteBlog(blogId);
        return ResultBody.success("");
    }

    /**
     * 博客查询
     * @param blogParams
     * @return
     */
    @PostMapping("/getBlog")
    public ResultBody getBlog(@RequestBody BlogParams blogParams){
        List<BlogVo> blogList = blogService.getBlog(blogParams);
        return ResultBody.success(CommonPage.restPage(blogList));
    }

    /**
     * 通过ID获取博客
     * @param id
     * @return
     */
    @GetMapping("/getBlogById")
    public ResultBody getBlogById(@RequestParam(value = "id")String id){
        Blog blog = blogService.getBlogById(id);
        return ResultBody.success(blog);
    }
}
