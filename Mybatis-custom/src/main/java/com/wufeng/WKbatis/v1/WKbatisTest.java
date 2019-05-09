package com.wufeng.WKbatis.v1;

import com.wufeng.WKbatis.v1.mapper.Blog;
import com.wufeng.WKbatis.v1.mapper.BlogMapper;

/**
 * @Author wangkai
 * @CreateTime 2019/5/9 22:54
 **/
public class WKbatisTest {
    public static void main(String[] args) {
        WKSqlSession sqlSession = new WKSqlSession(new WKConfiguration(),new WKExecutor());
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = blogMapper.selectBlogById(1);
        System.out.println(blog);

    }
}
