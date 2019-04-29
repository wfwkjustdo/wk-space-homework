package com.gupaoedu.service.impl;

import com.gupaoedu.entity.Blog;
import com.gupaoedu.dao.BlogMapper;
import com.gupaoedu.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author wangkai
 * @Create 2019/4/29-15:40.
 */
@Component
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;
    @Override
    public Blog getBlogById(int bid) {
        return blogMapper.selectByPrimaryKey(bid);
    }

    @Override
    public int addBlog(Blog blog) {
        return blogMapper.insert(blog);
    }
}
