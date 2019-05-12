package com.wufeng.WKbatis.v2.executor;

/**
 * @Author wangkai
 * @CreateTime 2019/5/11 16:01
 * @Description
 **/
public interface Executor {
    public <T> T query(String sql, Object[] parameter, Class pojo);
}
