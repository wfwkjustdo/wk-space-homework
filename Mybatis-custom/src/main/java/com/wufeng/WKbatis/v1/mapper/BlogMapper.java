package com.wufeng.WKbatis.v1.mapper;

/**
 * @Author wangkai
 * @CreateTime 2019/5/9 22:44
 **/
public interface BlogMapper {
    /**
     * 根据主键查询文章
     * @param bid
     * @return
     */
    public Blog selectBlogById(Integer bid);
}
