package com.gupaoedu.dao;

import com.gupaoedu.entity.Blog;
import org.springframework.stereotype.Repository;

/**
 * @Author wangkai
 * @Create 2019/4/29-15:57.
 */
@Repository
public interface BlogMapper {
    int deleteByPrimaryKey(Integer bid);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer bid);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);
}
