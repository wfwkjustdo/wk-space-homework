package com.gupaoedu.controller;

import com.gupaoedu.entity.Blog;
import com.gupaoedu.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author wangkai
 * @Create 2019/4/29-15:27.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("blog")
public class Controller {
    @Autowired
    BlogService blogService;

    @RequestMapping("list")
    public Blog getBlog(){
        Blog blog= new Blog();
        blog.setName("如何进入BAT");
        return blog;
    }

    @RequestMapping("/dblist")
    @ResponseBody
    public Blog toIndex(HttpServletRequest request){
        int bid = Integer.parseInt(request.getParameter("bid"));
        Blog blog = this.blogService.getBlogById(bid);
        return blog;
    }
}
