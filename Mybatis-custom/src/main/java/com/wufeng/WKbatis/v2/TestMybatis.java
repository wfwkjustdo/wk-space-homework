package com.wufeng.WKbatis.v2;

import com.wufeng.WKbatis.v2.executor.ResultSetHandler;
import com.wufeng.WKbatis.v2.mapper.Blog;
import com.wufeng.WKbatis.v2.mapper.BlogMapper;
import com.wufeng.WKbatis.v2.session.DefaultSqlSession;
import com.wufeng.WKbatis.v2.session.SqlSessionFactory;

/**
 * @Author wangkai
 * @CreateTime 2019/5/11 17:33
 * @Description
 **/
public class TestMybatis {

    public static void main(String[] args) {
        SqlSessionFactory factory = new SqlSessionFactory();
        DefaultSqlSession sqlSession = factory.build().openSqlSession();

        //获取MapperProxy代理
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlogById(1);
        System.out.println("第一次查询："+blog);
        System.out.println();
        blog = mapper.selectBlogById(1);
        System.out.println("第二次查询: " + blog);
    }
}
