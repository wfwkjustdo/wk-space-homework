package com.wufeng.WKbatis.v2.executor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangkai
 * @Create 2019/5/23-16:20.
 * @Description 带缓存的执行器，用于装饰基本执行器
 */
public class CachingExecutor implements Executor {
    private Executor delegate;
    private  static final Map<Integer,Object> cache = new HashMap<>();

    public CachingExecutor(Executor delegate) {
    }

    @Override
    public <T> T query(String sql, Object[] parameter, Class pojo) {
        return null;
    }
}
