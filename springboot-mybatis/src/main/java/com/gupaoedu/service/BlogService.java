package com.gupaoedu.service;

import com.gupaoedu.entity.Blog;

/**
 * @Author wangkai
 * @Create 2019/4/29-15:39.
 */
public interface BlogService {
    public Blog getBlogById(int bid);

    int addBlog(Blog blog);
}
