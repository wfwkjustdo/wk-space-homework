package com.wufeng.WKbatis.v2.executor;

/**
 * @Author wangkai
 * @CreateTime 2019/5/11 18:43
 * @Description
 **/
public class SimpleExecutor implements Executor {
    @Override
    public <T> T query(String sql, Object[] parameter, Class pojo) {
        StatementHandler statementHandler = new StatementHandler();
        return null;
    }
}
